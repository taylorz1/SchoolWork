package chessPieces;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ChessPiece  {
	protected boolean isWhite;
	
	abstract public boolean checkMove(int x, int y);
	public void draw(Graphics2D graphics2, int x, int y, int squareSize) {
		String fileName = "images/"+getName() + "-";
		fileName += this.isWhite() ? "white" : "black";
		fileName += ".png";
		BufferedImage img;
		try {
			img = ImageIO.read(new File(fileName));
			graphics2.drawImage(img, x, y, squareSize, squareSize, null);
		} catch (IOException e) {}
	}

	
	public boolean checkAttack(int dx, int dy, ChessPiece piece){
		if (this.isWhite() == piece.isWhite()) {
			//don't attack your own pieces
			return false;
		}
		return checkMove(dx, dy);
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}

	public boolean canJumpPieces() {
		return false;
	}
	
	public abstract String getName();
}
