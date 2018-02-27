package threading;

/**
 * 
 * A class designed to compute:
 * 
 * (product of number list) - (sum of number list)
 * 
 * You may modify any of the functions of this class, including adding fields or
 * inner classes, except for main.
 *
 * @author hewner. Created Nov 14, 2016.
 */
public class SumAndProduct {

	double[] data;

	SumAndProduct(double[] input) {
		this.data = input;
	}

	private double computeSum(double[] input) {
		System.out.println("Starting computing sum");
		double sum = 0;
		for (double n : input) {
			sum = sum + n;
		}
		System.out.println("Finished computing sum");
		System.out.println("Calculated sum is: " + sum);
		return sum;
	}

	private double computeProduct(double[] input) {
		System.out.println("Starting computing product");
		double product = 1;
		for (double n : input) {
			product = product * n;
		}
		System.out.println("Finished computing product");
		System.out.println("Calculated product is: " + product);
		return product;
	}

	public double computeSumAndProductSerially() {
		double sum = computeSum(this.data);
		double product = computeProduct(this.data);
		return product - sum;
	}

	public double computeSumAndProductInParallel() {
		SPThread add = new SPThread(this.data, true, this);
		SPThread product = new SPThread(this.data, false, this);
		Thread adder = new Thread(add);
		Thread proder = new Thread(product);
		proder.start();
		adder.start();
		try {
			adder.join();
			proder.join();
		} catch (InterruptedException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}

		return product.getReturn() - add.getReturn();
	}

	class SPThread implements Runnable {
		double[] data;
		boolean nature;
		double toReturn;
		SumAndProduct j;
		
		public SPThread(double[] input, boolean add, SumAndProduct j) {
				this.data = input;
				this.nature = add;
				this.toReturn = 0;
				this.j = j;
		}
		
		@Override
		public void run() {
			if (this.nature) {
				this.toReturn = this.j.computeSum(this.data);
			} else {
				this.toReturn = this.j.computeProduct(this.data);
			}
		}

		public double getReturn() {
			return this.toReturn;
		}
		
	}

	/**
	 * 
	 * You should not need to modify this function
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		double testData[] = new double[10000];
		for (int i = 0; i < testData.length; i++) {
			// pick a random number between 0.5 and 1.5
			testData[i] = Math.random() + 0.5;
		}
		SumAndProduct computer = new SumAndProduct(testData);
		System.out.println("Starting serial test...");
		double result = computer.computeSumAndProductSerially();
		System.out.println("Finished serial test");
		System.out.println("Serial result is = " + result);

		System.out.println("Starting paralell test...");
		result = computer.computeSumAndProductInParallel();
		System.out.println("Finished paralell test");
		System.out.println("Paralell result is = " + result);
	}
}
