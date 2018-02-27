package problem;

import java.util.HashMap;

public class DataStandardizingApp {
	
	public static void main(String[] args) {
		DataStandardizer unifier = new DataStandardizer();
		DataStandardizerUI ui = new DataStandardizerUI(unifier);
		ui.execute();
	}

}
