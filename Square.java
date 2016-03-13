package rithmomachy;

import java.lang.Math;

public class Square extends Piece 
{
	public Square(int v, boolean s) {
		super(v, s);
	}

	/* Checks if two given Spaces are a valid move
	 ** Squares can move like this:
	 ** . .	@ @ @ . . 
	 **	@ . . . . . @		     
	 ** @ . . []. . @
	 ** @ . . . . . @
	 ** . .	@ @ @ . . 
	 ** Where @ are valid moves and . are empty spaces.
	 */
	@Override
	boolean isValidMove(Board board, Space start, Space end) {
		if (piecesBetween(board, start, end)) {
			return false;
		}
		if (Math.abs(end.x - start.x) == 3 
			&& Math.abs(end.y - start.x) <= 1) {

			return true;
		} else if (Math.abs(end.y - start.y) == 3 
			&& Math.abs(end.x - start.x) <= 1) {

			return true;
		} else {
			return false;
		}
		
	}

	public String toString() {
		return "[" + value + "]";
	}
}