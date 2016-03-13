package rithmomachy;

import java.lang.Math;

public class Triangle extends Piece 
{
	public Triangle(int v, boolean s) {
		super(v, s);
	}

	// Checks if two given Spaces are a valid move
	// Rounds can move diagonally n any direction
	@Override
	boolean isValidMove(Board board, Space start, Space end) {
		if (piecesBetween(board, start, end)) {
			return false;
		}
		if (Math.abs(start.x - end.x) == 2 && Math.abs(start.y - end.y) < 2) {
			return true;
		} else if (Math.abs(start.y - end.y) == 2 
			&& Math.abs(start.x - end.x) < 2) {
			return true;
		} else {
			return false;
		}
		
	}

	public String toString() {
		return "<" + value + ">";
	}
}