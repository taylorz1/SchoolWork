package chessPieces;

public class Bishop extends ChessPiece{
	public Bishop(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	@Override
	public boolean checkMove(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);
		return (dx == dy);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub.
		return "bishop";
	}
	
}
