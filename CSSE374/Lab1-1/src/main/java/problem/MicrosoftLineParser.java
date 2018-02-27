package problem;

public class MicrosoftLineParser implements ILineParser{

	@Override
	public String parse(String line) {
		String[] fields = line.split(",");
		StringBuffer buffer = new StringBuffer();
		buffer.append(fields[0].trim());
		buffer.append(" : ");
		buffer.append(fields[1].trim());
		return buffer.toString();
	}

}
