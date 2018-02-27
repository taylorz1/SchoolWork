
public class ObjectConstructorPractice {
	private int num1;
	private double num2;
	private String str;
	
	
	
	public ObjectConstructorPractice(int num1, double num2, String str) {
		this.num1 = num1;
		this.num2 = num2;
		this.str = str;
	}
	
	public ObjectConstructorPractice() {
		num1 = 0;
		num2 = 0.0;
		str = "This is cool";
	}
	
	public ObjectConstructorPractice(String str) {
		this.str = str;
		num1 = 0;
		num2 = 0.0;
	}
	
	public ObjectConstructorPractice(int num1, int num2, String str, String str2) {
		this.num1 = num1+num2;
		this.num2 = (double) num1+num2/10;
		this.str = str.concat(str2);
	}
	
	//TODO 1: Insert a constructor that takes all three variables as 
	//		  arguments and sets the respective class variables.
	//  NOTE: When you finish this, does it cause errors from todo 0? 
	//		  WHY/WHY NOT?
	
	
	//TODO 3: Insert a constructor that takes no arguments,
	//		  initialize num1 and num2 to 0 and str to "Placeholder"
	
	//TODO 5: Insert a constructor that sets ONLY the str value, 
	//		  for the other values, set them to the default from todo 1.
	
	//TODO 7: Insert a constructor that takes values for num1 and num2
	
	public int getNum1(){
		return num1;
	}
	
	public double getNum2() {
		return num2;
	}
	
	public String getStr() {
		return str;
	}
	
	public void prettyPrintMe() {
		System.out.println("\n###########################################");
		System.out.println("num1 is: " + num1 + "\nnum2 is: " + num2 + "\nstr is: " + str);
		System.out.println("###########################################\n");
	}
	
	public static void main(String[] args) {
		System.out.println("We are working with object constructors!!!\n");
		
		ObjectConstructorPractice test1 = new ObjectConstructorPractice();
		test1.prettyPrintMe();
		
		ObjectConstructorPractice test2 = new ObjectConstructorPractice(0, 0.0, "String");
		test2.prettyPrintMe();
		
		ObjectConstructorPractice test3 = new ObjectConstructorPractice("Memetics");
		test3.prettyPrintMe();
		
		ObjectConstructorPractice test4 = new ObjectConstructorPractice(100,1000,"strings","string2");
		test4.prettyPrintMe();
		
		//TODO 0: Can we construct an object of type ObjectConstructorPratice?
		//		  If so, do that and call the method prettyPrintMe on the object.
		
		
		//TODO 2: Write code that will utilize the constructor from todo 1
		
		//TODO 4: Write code that will utilize the constructor from todo 3
		//		  Note, if there are any errors, what are they? Why? How do
		//		  we go about fixing any errors
		
		//TODO 6: Write code that will utilize the constructor from todo 5
		
		//TODO 8: Write code that will utilize the constructor from todo 7
	}
}
