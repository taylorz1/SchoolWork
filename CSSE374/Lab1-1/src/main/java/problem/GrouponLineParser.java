package problem;

public class GrouponLineParser implements ILineParser{

	@Override
	public String parse(String line) {
		return line.replaceAll("\\s+", " : ");
	}

}
