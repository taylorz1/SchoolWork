package stringTransforms;

public class RemoveLettersFromText implements TransformInterface {
	String remove;
		
	public RemoveLettersFromText(String toRemove) {
		this.remove = toRemove;
	}
	
	@Override
	public String transform(String text) {
		return text.replace(this.remove, "");
	}

}
