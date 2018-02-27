package problem;

public class GoogleLineParser implements ILineParser{

	StringBuffer buffer = new StringBuffer();
	
	@Override
	public String parse(String line) {
		return line.replace('-', ':');
	}

}
