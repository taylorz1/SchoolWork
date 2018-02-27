package chessPieces;

public class Rook extends ChessPiece{

	public Rook(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	@Override
	public boolean checkMove(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);
		return (dx == 0 || dy == 0);
	}	
	
	@Override
	public boolean checkAttack(int dx, int dy, ChessPiece piece) {
		if (this.isWhite() == piece.isWhite()) {
			//don't attack your own pieces
			return false;
		}
		return checkMove(dx, dy);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub.
		return "rook";
	}
	
}
