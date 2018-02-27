package chessPieces;

public class Knight extends ChessPiece{
	public Knight(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	@Override
	public boolean checkMove(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);
		return (((dx + dy) == 3) && (dy == 1 || dx == 1));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub.
		return "knight";
	}
	
	@Override
	public boolean canJumpPieces() {
		return true;
	}
}
