import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * This is the meat of the game. This class is responsible for creating every
 * game object and is responsible for removing and adding objects to the drawn
 * frame. Additionally it is responsbile for keeping track of the hero's lives
 * and the level the game is currently on. It handles rendering the level as
 * well. Numerous other minor functionalities as well.
 *
 * @author taylorz1. Created Oct 27, 2016.
 */

public class GameWorld implements GameEnvironment, Drawable, Temporal {
	private static final int HERO_LIFE_MAX = 4;
	private static final long UPDATE_INTERVAL_MS = 10;
	private final int width;
	private final int height;
	private final Color color;

	private final Shape background;
	private ArrayList<GameObject> gameObj = new ArrayList<>();
	private ArrayList<GameObject> gameObjToAdd = new ArrayList<>();
	private ArrayList<GameObject> gameObjToRemove = new ArrayList<>();

	private int heroLives = HERO_LIFE_MAX;
	private Level level;
	private Hero hero;
	private int currentLevel = 1;

	private ArrayList<Bomb> bombs;
	private ArrayList<Monster> monsters;

	private boolean isPaused = false;

	private HeartObject[] heartObject;
	private boolean alreadyPlaying = false;
	private HeartObject[] bossHeartObject;

	public GameWorld(int width, int height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;

		this.monsters = new ArrayList<>();

		this.level = new Level(this.currentLevel);
		this.bombs = new ArrayList<>();
		this.heartObject = new HeartObject[HERO_LIFE_MAX - 1];
		this.bossHeartObject = new HeartObject[6];
		initialize();

		this.background = new Rectangle2D.Double(0, 0, this.width, this.height);
		Runnable tickTock = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(UPDATE_INTERVAL_MS);
						timePassed();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
					// GameWorld should throw, but we'd have to carry it through
					// ~5 additional classes.
					exception.printStackTrace();
				}
			}
		};
		new Thread(tickTock).start();

	}

	/**
	 * 
	 * This method is actually responsible for drawing everything. It takes the
	 * input from the Level class and generates the objects we need, as well as
	 * clearing any old objects (if any).
	 *
	 */

	public void initialize() {
		ArrayList<Point2D.Double> wallPositions = this.level.getWalls();
		ArrayList<Point2D.Double> monster1Positions = this.level.getMonsters1();
		ArrayList<Point2D.Double> monster2Positions = this.level.getMonsters2();
		ArrayList<Point2D.Double> brickPositions = this.level.getBrick();
		ArrayList<Point2D.Double> remotePositions = this.level.getremote();
		ArrayList<Point2D.Double> bombSPositions = this.level.getbombSize();
		ArrayList<Point2D.Double> bombNPositions = this.level.getbombNumber();
		ArrayList<Point2D.Double> invincibilityPositions = this.level.getinvin();

		this.startMusic();
		
		Point2D.Double heropos = this.level.heroStart;
		this.monsters.clear();
		for (Point2D.Double wall : wallPositions) {
			Wall rect = new Wall(this, wall);
			this.gameObjToAdd.add(rect);
		}

		for (Point2D.Double monster : monster1Positions) {
			Monster rect = new EasyMonster(this, monster);
			this.monsters.add(rect);
			this.gameObjToAdd.add(rect);
		}
		for (Point2D.Double monster : monster2Positions) {
			Monster rect = new HardMonster(this, monster);
			this.gameObjToAdd.add(rect);
			this.monsters.add(rect);
		}
		for (Point2D.Double bombS : bombSPositions) {
			PowerUp bomb = new BombSizePowerUp(this, bombS);
			this.gameObjToAdd.add(bomb);
		}
		for (Point2D.Double bombN : bombNPositions) {
			PowerUp bomb = new BombNumberPowerUp(this, bombN);
			this.gameObjToAdd.add(bomb);
		}
		for (Point2D.Double remote : remotePositions) {
			PowerUp bomb = new RemoteDetonatorPowerUp(this, remote);
			this.gameObjToAdd.add(bomb);
		}
		for (Point2D.Double invin : invincibilityPositions) {
			SuperMarioPowerUp power = new SuperMarioPowerUp(this, invin);
			this.gameObjToAdd.add(power);
		}
		for (Point2D.Double brickwall : brickPositions) {
			BrickWall bwall = new BrickWall(this, brickwall);
			this.gameObjToAdd.add(bwall);
		}
		
		//System.out.println(this.level.getSpawnBoss());
		if(this.level.getSpawnBoss())
		{
			Monster boss = new BossMonster(this);
			this.gameObjToAdd.add(boss);
			this.monsters.add(boss);
			bossHeartObjectMake();
			
		}
		
		this.hero = new Hero(this, heropos);
		this.gameObjToAdd.add(this.hero);

		heartObjectMake();
	}

	private void heartObjectMake() {
		for (int i = 0; i < this.heroLives - 1; i++) {
			Point2D.Double point = new Point2D.Double(45 + 35 * i, 40);
			this.heartObject[i] = new HeartObject(this, point);
			this.gameObjToAdd.add(this.heartObject[i]);
		}
	}
	
	private void bossHeartObjectMake() {
		for (int i = 0; i < 6; i++) {
			Point2D.Double point = new Point2D.Double(250 + 35 * i, 40);
			this.bossHeartObject[i] = new HeartObject(this, point, Color.BLUE);
			this.gameObjToAdd.add(this.bossHeartObject[i]);
		}
	}

	public void removeMonster(Monster m) {
		this.monsters.remove(this.monsters.indexOf(m));
	}

	public Hero getHero() {
		return this.hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public ArrayList<GameObject> getRemove() {
		return this.gameObjToRemove;
	}

	public void incLevel() {
		
			this.currentLevel++;

		if (this.currentLevel > 6) {
			this.currentLevel = 999;
		}
		for (GameObject e : this.gameObj) {
			this.gameObjToRemove.add(e);
		}
		this.level = new Level(this.currentLevel);
		initialize();
	}

	public void decLevel() {
		this.currentLevel--;
		if (this.currentLevel == 0) {
			this.currentLevel = 1;
		}
		for (GameObject e : this.gameObj) {
			this.gameObjToRemove.add(e);
		}
		this.level = new Level(this.currentLevel);
		initialize();
	}

	@Override
	public boolean isInsideWorldX(Point2D point) {
		return point.getX() >= 0 && point.getX() <= this.width;
	}

	@Override
	public boolean isInsideWorldY(Point2D point) {
		return point.getY() >= 0 && point.getY() <= this.height;
	}

	@Override
	public boolean isInsideWorld(Point2D point) {
		return isInsideWorldX(point) && isInsideWorldY(point);
	}

	@Override
	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	@Override
	public void removeObj(GameObject gameObject) {
		this.gameObjToRemove.add(gameObject);

	}

	@Override
	public void addObj(GameObject gameObject) {
		this.gameObjToAdd.add(gameObject);

	}

	@Override
	public synchronized ArrayList<Drawable> getDrawableParts() {
		return new ArrayList<>(this.gameObj);
	}

	@Override
	public Point2D getCenterPoint() {
		double x = this.width / 2;
		double y = this.height / 2;
		return new Point2D.Double(x, y);
	}

	// Drawable

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Shape getShape() {
		return this.background;
	}

	// Time
	@Override
	public void die() {
		// You never want to kill the entire world do you?
		return;
	}

	public void startMusic() {
		if (this.alreadyPlaying) {
			return;
		}
		this.alreadyPlaying = true;
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(".//src//thememusic.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-20.0f); 
			clip.loop(clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
			exception.printStackTrace();
		}
		
	}

	@Override
	public void timePassed() {
		if (this.hero == null) {
			for (GameObject e : this.gameObj) {
				this.gameObjToRemove.add(e);
			}
			this.heroLives--;
			if (this.heroLives != 0) {
				this.gameObjToRemove.add(this.heartObject[this.heroLives - 1]);
			}
			if (this.heroLives < 1) {
				this.heroLives = HERO_LIFE_MAX;
				this.currentLevel = 1;
			}
			this.level = new Level(this.currentLevel);
			initialize();
			return;
		}
		for (Temporal t : this.gameObj) {
			if (!this.isPaused()) {
				t.timePassed();
			}
		}
		for (GameObject e : this.gameObj) {
			for (GameObject f : this.gameObj) {
				if (e == f) {
					continue;
				}
				e.collide(f);
			}
			// Below handles bombs exploding

			for (Bomb b : this.bombs) {
				if (b != null) {
					if (b.getExploded()) {
						Rectangle2D bounder = b.getbombExplode().getBounds2D();
						Rectangle2D bounder2 = b.getbombExplode2().getBounds2D();
						if (bounder.intersects(e.getShape().getBounds2D())) {
							e.onBombDamage();
							b.die();

						}
						if (bounder2.intersects(e.getShape().getBounds2D())) {
							e.onBombDamage();
						}

					}
				}

			}
		}
		for (Bomb b : this.bombs) {
			if (b != null) {
				if (b.getExploded())
					this.bombs.set(this.bombs.indexOf(b), null);
			}
		}

		this.gameObj.removeAll(this.gameObjToRemove);
		this.gameObjToRemove.clear();
		this.gameObj.addAll(this.gameObjToAdd);
		this.gameObjToAdd.clear();

		if (this.monsters.isEmpty()) {
			this.incLevel();
		}

	}
	public void addMonster(Monster m){
		this.monsters.add(m);
	}

	public void addBomb(Bomb bomb) {
		this.bombs.add(bomb);
	}

	public void detonateBombs() {
		for (Bomb b : this.bombs)
			if (b != null)
				b.detonate();

	}

	public boolean isPaused() {
		return this.isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void setCurrentLevel(int i) {
		this.currentLevel = i;
		initialize();
	}
	public void removeBossHeart(int life){
		this.gameObjToRemove.add(this.bossHeartObject[life-1]);
	}

	// You made it to the bottom!

}
