import javax.swing.JFrame;
public class MyViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("A frame");
		frame.setSize(300,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Add stuff
		MyComponent component = new MyComponent();
		frame.add(component);
		
		
		frame.setVisible(true);

	}

}
