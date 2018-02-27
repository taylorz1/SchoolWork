package part1;

import java.util.Scanner;

public class ChocolateDrizzle implements Topping{

	public ChocolateDrizzle() {
		System.out.println("Remember to melt and drizzle the chocolate yourself!");
	}

	private boolean melted = false;

	@Override
	public String toString() {
		if (melted) {
			return "Hot Chocolate Drizzle";
		} else {
			return "Not melted drizzle";
		}
	}

	public void meltme() {
		this.melted = true;
	}

	public boolean melted() {
		return this.melted;
	}

}
