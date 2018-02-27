package part2;

import edu.rosehulman.csse374.cheesecake.CheeseCakeFactory;
import edu.rosehulman.csse374.cheesecake.CheeseCakeUI;
import part1.CheeseCakeApp;
import part1.ChocolateCheeseCakeFactory;
import part1.StrawberryCheeseCakeFactory;

public class CheeseCakeClientForPt1 {
	public static void main(String[] args) {
		CheeseCakeFactory factory = new CheeseCakeFactory();
		CheeseCakeFactory chocolate = new ChocolateCheeseCakeFactory();
		CheeseCakeFactory strawberry = new StrawberryCheeseCakeFactory();

		CheeseCakeApp app = new CheeseCakeApp();
		app.addFactory("plain", factory);
		app.addFactory("chocolate", chocolate);
		app.addFactory("strawberry", strawberry);

		CheeseCakeUI ui = new CheeseCakeUI(app);
		ui.show();
	}
}
