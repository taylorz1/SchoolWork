package stringTransforms;

public class CapitalizeText implements TransformInterface {

	@Override
	public String transform(String text) {
		return text.toUpperCase();
	}

}
