package rithmomachy;

import java.util.ArrayList;

public class Space {
	ArrayList<Piece> pieces;
	int x;
	int y;


	public Space(int x, int y) {
		this.x = x;
		this.y = y;
		this.pieces = new ArrayList<Piece>();
	}

	public Space(ArrayList<Piece> p) {
		this.x = x;
		this.y = y;
		this.pieces = p;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void addPiece(Piece p) {
		pieces.add(p);
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

	public void clearPieces() {
		this.pieces = new ArrayList<Piece>();
	}

	public boolean isEmpty() {
		return pieces.isEmpty();
	}

	public boolean hasPyramid() {
		return (this.pieces.size() > 1);
	}

	public boolean canStack() {
		// Do we need this code?
		if (isEmpty()) {
			return true;
		}
		for (Piece p : pieces) {
			if (p.inStack == false) {
				return false;
			}
		}
		return true;
	}

}