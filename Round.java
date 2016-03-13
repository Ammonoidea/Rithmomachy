package rithmomachy;

import java.lang.Math;

public class Round extends Piece 
{
	public Round(int v, boolean s) {
		super(v, s);
	}

	// Checks if two given Spaces are a valid move
	// Rounds can move diagonally n any direction
	@Override
	boolean isValidMove(Board board, Space start, Space end) {
		// Rounds always move diagonally can't be blocked
		return (Math.abs(start.x - end.x) == 1 && Math.abs(start.y - end.y) == 1) 
			? true : false;
	}

	public String toString() {
		return "(" + value + ")";
	}
}