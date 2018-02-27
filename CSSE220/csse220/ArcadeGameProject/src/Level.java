import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	private ArrayList<Point2D.Double> wallPositions = new ArrayList<>();
	private ArrayList<Point2D.Double> monster1Positions = new ArrayList<>();
	Point2D.Double heroStart;
	private ArrayList<Point2D.Double> brickWallPositions = new ArrayList<>();
	private ArrayList<Point2D.Double> monster2Positions = new ArrayList<>();
	private ArrayList<Point2D.Double> bombNumberPositions = new ArrayList<>();
	private ArrayList<Point2D.Double> bombSizePositions = new ArrayList<>();
	private ArrayList<Point2D.Double> remoteDetonatorPositions = new ArrayList<>();
	private ArrayList<Point2D.Double> invincibilityPositions = new ArrayList<>();
	private boolean spawnBoss = false;

	public Level() {
		createLevel(1);
	}

	public Level(int LevelToLoad) {
		createLevel(LevelToLoad);
	}

	public void createLevel(int LevelToLoad) {
		File inputFile = new File(".//src//Level" + LevelToLoad + ".txt");
		try {
			Scanner s = new Scanner(inputFile);
			int i = s.nextInt();
			for (int j = 0; j < i; j++) {
				double x = s.nextDouble();
				double y = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x, y);
				this.wallPositions.add(pointer);
			}
			double x = s.nextDouble();
			double y = s.nextDouble();
			this.heroStart = new Point2D.Double(x, y);
			int q = s.nextInt();
			for (i = 0; i < q; i++) {
				// handle brick wall placement
				double x3 = s.nextDouble();
				double y3 = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x3, y3);
				this.brickWallPositions.add(pointer);
			}
			// generate the next k monster1
			int k = s.nextInt();
			for (i = 0; i < k; i++) {
				double x2 = s.nextDouble();
				double y2 = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x2, y2);
				this.monster1Positions.add(pointer);
			}
			k = s.nextInt();
			for (i = 0; i < k; i++) {
				double x2 = s.nextDouble();
				double y2 = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x2, y2);
				this.monster2Positions.add(pointer);
			}

			// Some powerups
			k = s.nextInt();
			for (i = 0; i < k; i++) {
				double x2 = s.nextDouble();
				double y2 = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x2, y2);
				this.bombNumberPositions.add(pointer);
			}
			k = s.nextInt();
			for (i = 0; i < k; i++) {
				double x2 = s.nextDouble();
				double y2 = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x2, y2);
				this.bombSizePositions.add(pointer);
			}
			k = s.nextInt();
			for (i = 0; i < k; i++) {
				double x2 = s.nextDouble();
				double y2 = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x2, y2);
				this.remoteDetonatorPositions.add(pointer);
			}
			k = s.nextInt();
			for (i = 0; i < k; i++) {
				double x3 = s.nextDouble();
				double y3 = s.nextDouble();
				Point2D.Double pointer = new Point2D.Double(x3, y3);
				this.invincibilityPositions.add(pointer);
			}
			this.spawnBoss=false;
			if(s.nextInt()==42)
				this.spawnBoss =true;
			s.close();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	public ArrayList<Point2D.Double> getWalls() {
		return this.wallPositions;
	}

	public ArrayList<Point2D.Double> getMonsters1() {
		return this.monster1Positions;
	}

	public ArrayList<Point2D.Double> getMonsters2() {
		return this.monster2Positions;
	}

	public ArrayList<Point2D.Double> getbombNumber() {
		return this.bombNumberPositions;
	}

	public ArrayList<Point2D.Double> getbombSize() {
		return this.bombSizePositions;
	}

	public ArrayList<Point2D.Double> getremote() {
		return this.remoteDetonatorPositions;
	}

	public Point2D.Double getHero() {
		return this.heroStart;
	}

	public ArrayList<Point2D.Double> getBrick() {
		return this.brickWallPositions;
	}

	public ArrayList<Point2D.Double> getinvin() {
		return this.invincibilityPositions;
	}

	public boolean getSpawnBoss() {
		return this.spawnBoss;
	}

}
