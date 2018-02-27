package chessPieces;

public class Pawn extends ChessPiece{
	private boolean first = true;
	
	public Pawn(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	@Override
	public boolean checkMove(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);
		if (first) {
			first = false;
			return (dy <= 2 && dx == 0);
		}
		return (dy == 1);
	}
	
	public boolean checkMove2(int dx, int dy) {
		return (dy==1 && dx == 1);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub.
		return "pawn";
	}
	
	@Override
	public boolean checkAttack(int dx, int dy, ChessPiece piece){
		if (this.isWhite() == piece.isWhite()) {
			//don't attack your own pieces
			return false;
		}
		return checkMove2(dx, dy);
	}
	
}
