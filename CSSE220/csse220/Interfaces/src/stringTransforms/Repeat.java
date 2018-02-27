package stringTransforms;

public class Repeat implements TransformInterface {

	
	@Override
	public String transform(String text) {
		return text + text;
	}

}
