package stringTransforms;

public class CapitalizeTextRemove implements TransformInterface {
	String remove;
	
	public CapitalizeTextRemove (String toremove) {
		this.remove = toremove;
	}
	
	@Override
	public String transform(String text) {
		String upper = text.toUpperCase();
		return upper.replace(this.remove.toUpperCase(), "");
	}

}
