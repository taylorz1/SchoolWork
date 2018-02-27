package stringTransforms;

public class Interfaces {


	public static void main(String[] args) {
		String myString = "buffalo";
		myString = transformText(myString, new Repeat());
		myString = transformText(myString, new CapitalizeText());
		myString = transformText(myString, new RemoveLettersFromText("F"));
		AggregateTransform t = new AggregateTransform(new Repeat(), new Repeat());
		myString = transformText(myString, t);
		
	}
	
	public static String transformText(String text, TransformInterface transform) {
		System.out.println("Text before transform: " + text);
		String output = transform.transform(text);
		System.out.println("Text after transform: " + output);
		return output;
	}
	/*
	 * SCROLL DOWN FOR A HINT - only if you need it!
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * so in the end, you will replace all 3 duplicated functions with a
	 * function that looks like this:
	 * 
	 * public static String transformText(String text, TransformInterface transform) 
	 * { 
	 * 		System.out.println("Text before transform: " + text); 
	 * 		String output = transform.transform(text); 
	 * 		System.out.println("Text after transform: " + output); return output; 
	 * }
	 * 
	 */

}
