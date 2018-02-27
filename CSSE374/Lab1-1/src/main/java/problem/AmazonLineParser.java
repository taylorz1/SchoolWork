package problem;

public class AmazonLineParser implements ILineParser{

	@Override
	public String parse(String line) {
		String[] fields = line.split(", ");
		StringBuffer buffer = new StringBuffer();
		buffer.append(fields[0].replace("ttl", ":"));
		buffer.append("\n");
		buffer.append(fields[1].replace("ttl", ":"));
		return buffer.toString();
	}

}
