package evaluator;

import java.util.EmptyStackException;
import java.util.Stack;

public class PostfixEvaluator extends Evaluator {

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		String[] tokens = expression.split(" ");
		Stack<Integer> operand = new Stack<>();
		int val = 0;
		int first = 0;
		int second = 0;
		int oran_c = 0;
		int op_c = 0;
		for (int i = 0; i < tokens.length; i++) {
			String tok = tokens[i];
			try { if (isOperator(tok)) {
					first = operand.pop();
					second = operand.pop();
					op_c++;
					val = doOp(tok, first, second);
					operand.push(val);
			} else {
				try {
				operand.push(Integer.valueOf(tok));
				oran_c++;
				} catch (NumberFormatException e) { 
					throw(new ArithmeticException("Invalid character"));
				}
			}
			} catch (EmptyStackException e) {
				throw(new ArithmeticException("Invalid string"));
			}
		}
		if (oran_c - op_c != 1) {
			throw(new ArithmeticException("Invalid number of operands and operators"));
		}
		return operand.pop();
	}

	private int doOp(String tok, int first, int second) {
		int sum = 0;
		switch (tok) {
		case "+":
			sum = first + second;
			break;
		case "-":
			sum = second - first;
			break;
		case "*":
			sum = first * second;
			break;
		case "^":
			sum = (int) Math.pow(second, first);
			break;
		case "/":
			sum = second / first;
			break;
		default:
			throw (new ArithmeticException("Invalid operator"));
		}
		return sum;
	}
}
