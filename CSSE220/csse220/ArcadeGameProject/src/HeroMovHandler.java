import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * 
 * This handles all key listening. It being called HeroMovHandler is a bit of a misnomer
 * but we all believe that player is the true hero so we find this to be a nonissue.
 *
 * @author taylorz1.
 *         Created Nov 9, 2016.
 */
public class HeroMovHandler implements KeyListener {

	private GameWindowComponent g;
	char lastPressed = 'n';
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;

	public HeroMovHandler(GameWindowComponent g) {
		this.g = g;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			this.upPressed = true;
			this.lastPressed = 'u';
		}
		if (key == KeyEvent.VK_DOWN) {
			this.downPressed = true;
			this.lastPressed = 'd';
		}
		if (key == KeyEvent.VK_LEFT) {
			this.leftPressed = true;
			this.lastPressed = 'l';
		}
		if (key == KeyEvent.VK_RIGHT) {
			this.rightPressed = true;
			this.lastPressed = 'r';
		}
		if (key == KeyEvent.VK_U) {
			this.g.getWorld().incLevel();
		}
		if (key == KeyEvent.VK_D) {
			this.g.getWorld().decLevel();
		}

		if (key == KeyEvent.VK_X) {
			this.g.getWorld().getHero().dropBomb();
		}
		if(key == KeyEvent.VK_SPACE){
			this.g.getWorld().detonateBombs();
		}
		heroDirectionHandler();

	}

	private void heroDirectionHandler() {
		Hero hero = this.g.getWorld().getHero();
		if(hero!=null){
		if (this.lastPressed == 'u' && this.upPressed) {
			hero.setDyDx(0, -4);
		}
		else if (this.lastPressed == 'd' && this.downPressed) {
			hero.setDyDx(0, 4);
		}
		else if (this.lastPressed == 'l' && this.leftPressed) {
			hero.setDyDx(-4, 0);
		}
		else if (this.lastPressed == 'r' && this.rightPressed) {
			hero.setDyDx(4, 0);
		}
		else if (this.upPressed) {
			hero.setDyDx(0, -4);
		}
		else if (this.downPressed) {
			hero.setDyDx(0, 4);
		}
		else if (this.leftPressed) {
			hero.setDyDx(-4, 0);
		}
		else if (this.rightPressed) {
			hero.setDyDx(4, 0);
		}
		else{
			hero.setDyDx(0, 0);
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getExtendedKeyCode();
		if (key == KeyEvent.VK_P){
			this.g.getWorld().setPaused(!this.g.getWorld().isPaused());
		}
		if (key == KeyEvent.VK_UP) {
			this.upPressed = false;
		}
		else if (key == KeyEvent.VK_DOWN) {
			this.downPressed = false;
		}
		else if (key == KeyEvent.VK_LEFT) {
			this.leftPressed = false;
		}
		else if (key == KeyEvent.VK_RIGHT) {
			this.rightPressed = false;
		}
		heroDirectionHandler();

	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

}