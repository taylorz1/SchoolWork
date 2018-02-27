package evaluator;

import java.util.Stack;

public class InfixEvaluator extends Evaluator {

     private int operatorcount = 0;

     @Override
     public int evaluate(String expression) throws ArithmeticException {
           // DONE Auto-generated method stub.
           String j = convertToPostfix(expression);
           PostfixEvaluator eval = new PostfixEvaluator();
           System.out.println(eval.evaluate(j));
           return eval.evaluate(j);
     }

     public String convertToPostfix(String exp) throws ArithmeticException {
           // DONE Auto-generated method stub.
           StringBuilder s = new StringBuilder();
           // Divide up all the tokens
           String[] toks = exp.split(" ");
           Stack<String> operators = new Stack<>();
           String op = new String();
           for (int i = 0; i < toks.length; i++) {
                String tok = toks[i];
                if (isOperator(tok)) {
                      if (tok.equals(")")) { //right paren
                           Boolean right = true; //yes there a right paren
                           while ((!operators.isEmpty())) {
                                 if (operators.peek().equals("(")) {
                                      operators.pop();
                                      right = false; // yes there is left paren to match
                                      break;
                                 }
                                 op = operators.pop(); // Get the operator
                                 s.append(op + " ");
                           }
                           if (right) { //checks for proper paren
                                 throw new ArithmeticException();
                           }
                      } else if (tok.equals("(")) {
                           operators.push(tok);
                      } else if (tok.equals("^")) {//its exponent just do it
                           operators.push(tok);
                      } else if ("*/".contains(tok)) { // multiply
                           if (!operators.isEmpty()) {
                                 if (operators.peek().equals("^")) {//if exponent do that first
                                      while (!operators.isEmpty()) {
                                            if (operators.peek().equals("(")) {
                                                 break;
                                            }
                                            op = operators.pop(); // Get the operator
                                            s.append(op + " ");
                                      }
                                 }
                           }
                           operators.push(tok);
                      } else if ("+-".contains(tok)) {//addition or subtraction
                           if ((!operators.isEmpty())) {
                                 if ("+-".contains(operators.peek())) {//if last op is ad or sub do that
                                      if ("+-".contains(operators.peek())) {
                                            op = operators.pop(); // Get the operator
                                            s.append(op + " ");
                                      }
                                 } else {//if op not add or sub do that first
                                      while (!operators.isEmpty()) {
                                            if (operators.peek().equals("(")) {
                                                 break;
                                            }
                                            op = operators.pop(); // Get the operator
                                            s.append(op + " ");
                                      }
                                 }
                           }
                           operators.push(tok);
                      }
                } else {//add numbers to equation
                      s.append(tok + " ");
                }
           }
           while (!operators.isEmpty()) {
                if (operators.peek().equals("(")) { // theres too many left parens!!!
                      throw new ArithmeticException();
                }
                op = operators.pop(); // Get the operator
                s.append(op + " ");
           }
           return s.toString().substring(0, s.length() - 1);
     }

}
