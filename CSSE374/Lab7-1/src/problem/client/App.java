package problem.client;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import problem.lib.LinearTransformer;

public class App {
	public static void main(String[] args) throws Exception {
		// TODO: We want to use ArrayList instead of Vector here, i.e.,
		// ArrayList<String> vect = new ArrayList<String>();
		ArrayList<String> vect = new ArrayList<String>();
		for(int i = 1; i <= 3; ++i) {
			vect.add("Tick Tick " + i);
		}
		Enumeration<String> etion = new ArrayListIteratorEnumerationAdapter<>(vect.iterator());
		LinearTransformer<String> lT = new LinearTransformer<String>(etion);
		lT.transform(System.out);
	}
}
