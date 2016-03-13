package rithmomachy;

public abstract class Piece 
{
	int value;
	boolean inStack;

	public Piece(int v, boolean s) {
		this.value = v;
		this.inStack = s;
	}

	public boolean isInStack()
	{
		return this.inStack;
	}

	public int getValue() 
	{
		return this.value;
	}

	protected boolean piecesBetween(Board board, Space start, Space end) {
		// There's nothing in the way of your own piece
		// Also an invalid move, maybe fix this?
		// This method should only be called when you know there's a straight
		// line.
		if (start.x == end.x && start.y == end.y) {
			return false;
		}
		// Diagonal moves always act as if there's nothing in the way
		if (start.x != end.x && start.y != end.y) {
			return false;
		}
		if (start.x == end.x) {
			if (end.y > start.y) {
				for (int i = 1; i < end.y - start.y; i++) {
					if (!board.tiles[start.x][start.y + i].isEmpty()) {
						return true;
					}
				}
			} else if (end.y < start.y) {
				for (int i = 1; i < start.y - end.y; i++) {
					if (!board.tiles[start.x][start.y - i].isEmpty()) {
						return true;
					}
				}
			}
		} else if (start.y == end.y) {
			if (end.x > start.x) {
				for (int i = 1; i < end.x - start.x; i++) {
					if (!board.tiles[start.x + i][start.y].isEmpty()) {
						return true;
					}
				}
			} else if (end.x < start.x) {
				for (int i = 1; i < start.x - end.x; i++) {
					if (!board.tiles[start.x - i][start.y].isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// Checks if two given Spaces are a valid move
	// Might want to refactor assuming that
	abstract boolean isValidMove(Board board, Space start, Space end);
}

