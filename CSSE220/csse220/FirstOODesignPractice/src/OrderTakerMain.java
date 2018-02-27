import java.util.Scanner;
/**
 * 
 * A sample class that parses a simple order framework
 * 
 * @author hewner
 *
 */
public class OrderTakerMain {

	private String handleCreateInvoice() {
		return null;
	}
	
	private String handleAddItem(int invoiceNumber, int itemNumber, double itemPrice, String description) {
		return null;
	}
	
	private String handleGetTotal(int invoiceNumber) {
		return null;
	}
	
	/**
	 * Parse a complete command string and call the right function
	 * 
	 * @param command
	 * @return output of command
	 */
	public String handleCommand(String command) {
		Scanner input = new Scanner(command);
		String commandType = input.next();

		try {

			if (commandType.equals("create-invoice")) {
				return handleCreateInvoice();
			}

			if (commandType.equals("add-item")) {
				int invoiceNumber = input.nextInt();
				int itemNumber = input.nextInt();
				double itemPrice = input.nextDouble();
				String description = input.nextLine();
				return handleAddItem(invoiceNumber, itemNumber, itemPrice,
						description);
			}

			if (commandType.equals("get-total")) {
				int invoiceNumber = input.nextInt();
				return handleGetTotal(invoiceNumber);
			}

			if (commandType.equals("exit")) {
				System.exit(0);
			}
		} finally {
			input.close();
		}

		return "Unknown command " + commandType;
	}

	/**
	 * 
	 * Run the OrderTaker in console mode (that is, input is to come from
	 * the console).
	 * 
	 * 
	 * @param args Command line arguments (ignored)
	 */
	public static void main(String[] args) {
		OrderTakerMain orders = new OrderTakerMain();
		System.out.println("Welcome to Order Taker.  Enter commands.  Type 'exit' to end.");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String commandLine = scanner.nextLine();
			String result = orders.handleCommand(commandLine);
			System.out.println(result);
		}
		
	}
	
}
