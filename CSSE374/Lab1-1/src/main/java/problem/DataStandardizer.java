package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * The DataStandardizer class standardizes the Business Intelligence data
 * provided by Google and Microsoft to a common format.
 * 
 * @author Chandan R. Rupakheti
 * @author Mark Hays
 */
public class DataStandardizer {
	protected String company;
	protected String data;
	private HashMap<String, ILineParser> parsers;
	
	DataStandardizer() {
			parsers = new HashMap<String, ILineParser>();
			ILineParser microsoftLineParser = new MicrosoftLineParser();
			ILineParser googleLineParser = new GoogleLineParser();
			addParser("microsoft", microsoftLineParser);
			addParser("google", googleLineParser);
	}
	
	public void addParser(String company, ILineParser parser)
	{
		this.parsers.put(company, parser);
	}

	public void parse(String path) {
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), charset)) {

			// First line represents the name of a company
			this.company = reader.readLine();
			ILineParser parser = this.parsers.get(this.company);

			// The rest is the data
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				// DONE: convince yourself that this is TERRIBLE code, then
				// refactor.
				buffer.append(parser.parse(line));
				buffer.append("\n");
			}

			// Done unifying the data
			this.data = buffer.toString();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	public String getCompany() {
		return this.company;
	}

	public String getData() {
		return this.data;
	}
}
