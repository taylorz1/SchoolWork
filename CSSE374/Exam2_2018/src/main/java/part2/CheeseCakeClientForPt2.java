package part2;

import edu.rosehulman.csse374.cheesecake.CheeseCakeFactory;
import edu.rosehulman.csse374.cheesecake.CheeseCakeUI;
import part1.CheeseCakeApp;
import part1.ChocolateCheeseCakeFactory;
import part1.StrawberryCheeseCakeFactory;

public class CheeseCakeClientForPt2 {
	public static void main(String[] args) {
		CheeseCakeApp app = new CandyCapableApp();

		CheeseCakeUI ui = new CheeseCakeUI(app);
		ui.show();
	}
}
