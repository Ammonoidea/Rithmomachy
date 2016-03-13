package rithmomachy;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Board {
	private static final int[] WHITE_ROUNDS = {64,36,16,8,6,4,4,2};
	private static final int[] WHITE_TRIANGLES = {6,9,20,25,42,49,72,81};
	private static final int[] WHITE_SQUARES = {15,25,45,81,169,153,289};
	private static final int[] WHITE_PYRAMID = {1, 4, 9, 16, 25, 36};

	private static final int[] BLACK_ROUNDS = {81,29,25,9,9,7,5,3};
	private static final int[] BLACK_TRIANGLES = {12,16,30,36,56,64,90,100};
	private static final int[] BLACK_SQUARES = {28,49,66,120,121,225,361};
	private static final int[] BLACK_PYRAMID = {1, 25, 36, 49, 25, 64};
	public Space[][] tiles = new Space[8][16];
	public ArrayList<Piece> whitePieces = new ArrayList<Piece>();
	public ArrayList<Piece> blackPieces = new ArrayList<Piece>();

	public Board() {
		for (int i = 0; i < tiles.length; i++)  {
			for (int y = 0; y < tiles[0].length; y++) {
				tiles[i][y] = new Space(i, y);
			}
		}
	}

	public boolean addPiece(Piece p, int x, int y, boolean white) {
		if (x < 0 || x > tiles.length || y < 0 || y > tiles[0].length) {
			return false;
		}

		if (tiles[x][y].canStack()) {
			tiles[x][y].addPiece(p);
			if (white) {
				whitePieces.add(p);
			} else {
				blackPieces.add(p);
			}
			return true;
		} else {
			return false;
		}
	}

	public void setUp() {

		addRounds();
		addTriangles();
		addSquares();
		addPyramids();

	}

	public void addRounds() {
		// Add white rounds
		int x = 2;
		int y = 4;
		for (int c = 0; c < 8; c++) {
			addPiece(new Round(WHITE_ROUNDS[c], false), x, y, true);
			addPiece(new Round(BLACK_ROUNDS[c], false), 7 - x, 15 - y, false);
			if (x  == 5) {
				x = 2;
				y = 5;
			} else {
				x++;
			}

		}
	}

	public void addTriangles() {
		addPiece(new Triangle(WHITE_TRIANGLES[0], false), 6, 4, true);
		addPiece(new Triangle(WHITE_TRIANGLES[1], false), 7, 4, true);
		addPiece(new Triangle(WHITE_TRIANGLES[2], false), 4, 3, true);
		addPiece(new Triangle(WHITE_TRIANGLES[3], false), 5, 3, true);
		addPiece(new Triangle(WHITE_TRIANGLES[4], false), 3, 3, true);
		addPiece(new Triangle(WHITE_TRIANGLES[5], false), 2, 3, true);
		addPiece(new Triangle(WHITE_TRIANGLES[6], false), 1, 4, true);
		addPiece(new Triangle(WHITE_TRIANGLES[7], false), 0, 4, true);


		addPiece(new Triangle(BLACK_TRIANGLES[7], false), 6, 11, false);
		addPiece(new Triangle(BLACK_TRIANGLES[6], false), 7, 11, false);
		addPiece(new Triangle(BLACK_TRIANGLES[5], false), 4, 12, false);
		addPiece(new Triangle(BLACK_TRIANGLES[4], false), 5, 12, false);
		addPiece(new Triangle(BLACK_TRIANGLES[3], false), 3, 12, false);
		addPiece(new Triangle(BLACK_TRIANGLES[2], false), 2, 12, false);
		addPiece(new Triangle(BLACK_TRIANGLES[1], false), 1, 11, false);
		addPiece(new Triangle(BLACK_TRIANGLES[0], false), 0, 11, false);
	}

	public void addSquares() {
		addPiece(new Square(WHITE_SQUARES[0], false), 7, 3, true);
		addPiece(new Square(WHITE_SQUARES[1], false), 7, 2, true);
		addPiece(new Square(WHITE_SQUARES[2], false), 6, 3, true);
		addPiece(new Square(WHITE_SQUARES[3], false), 6, 2, true);
		addPiece(new Square(WHITE_SQUARES[4], false), 1, 2, true);
		addPiece(new Square(WHITE_SQUARES[5], false), 0, 3, true);
		addPiece(new Square(WHITE_SQUARES[6], false), 0, 2, true);

		addPiece(new Square(BLACK_SQUARES[6], false), 7, 13, false);
		addPiece(new Square(BLACK_SQUARES[5], false), 6, 12, false);
		addPiece(new Square(BLACK_SQUARES[3], false), 6, 13, false);
		addPiece(new Square(BLACK_SQUARES[4], false), 1, 13, false);
		addPiece(new Square(BLACK_SQUARES[2], false), 1, 12, false);
		addPiece(new Square(BLACK_SQUARES[1], false), 0, 12, false);
		addPiece(new Square(BLACK_SQUARES[0], false), 0, 13, false);
	}

	public void addPyramids() {
		addPiece(new Round(WHITE_PYRAMID[0], true), 1, 3, true);
		addPiece(new Round(WHITE_PYRAMID[1], true), 1, 3, true);
		addPiece(new Triangle(WHITE_PYRAMID[2], true), 1, 3, true);
		addPiece(new Triangle(WHITE_PYRAMID[3], true), 1, 3, true);
		addPiece(new Square(WHITE_PYRAMID[4], true), 1, 3, true);
		addPiece(new Square(WHITE_PYRAMID[5], true), 1, 3, true);

		addPiece(new Round(BLACK_PYRAMID[0], true), 7, 12, false);
		addPiece(new Triangle(BLACK_PYRAMID[1], true), 7, 12, false);
		addPiece(new Triangle(BLACK_PYRAMID[2], true), 7, 12, false);
		addPiece(new Square(BLACK_PYRAMID[3], true), 7, 12, false);
		addPiece(new Square(BLACK_PYRAMID[4], true), 7, 12, false);
	}

	public boolean movePiece(int startX, int startY, int endX, int endY) {
		Space startSpace = tiles[startX][startY];
		Space endSpace = tiles[endX][endY];
		if (!endSpace.getPieces().isEmpty()) {
			System.out.println("Illegal Move: Target Space: (" + endX 
				+ ", " + endY + ") is occupied.");
			return false;
		}
		if (startSpace.isEmpty()) {
			System.out.println("Illegal Move: Start Space: (" + startX 
				+ ", " + startY + ") is empty.");
			return false;

		}

		ArrayList<Piece> startPieces = startSpace.getPieces();
		for (Piece start : startPieces) {
				if (start.isValidMove(this, startSpace, endSpace)) { 
					endSpace.setPieces(startPieces);
					startSpace.clearPieces();
					return true;
				}
		}
		System.out.println("Illegal Move: (" + startX 
				+ ", " + startY +  ") can't move this way to " + "(" 
				+ endX + ", " + endY +  ")");
		return false;
	}

	public boolean capture(int x, int y) {
		Space changed = tiles[x][y];
		ArrayList<Pieces> otherSide;
		if (whitePieces.contains(changed.getPieces().get(0))) {
			otherSide = whitePieces;
		} else {
			otherSide = blackPieces;
		}
		for (Pieces p : otherSide) {

		}

	}

	// Maybe I want an enum for sides?
	public boolean captureBySiege(int x, int y, Piece p,
			ArrayList<Piece> otherside) {
		boolean north = false;
		boolean south = false;
		boolean west = false;
		boolean east = false;

		boolean ne = false;
		boolean nw = false;
		boolean se = false;
		boolean sw = false;
		if (x == 0) {
			north = true;
			ne = true;
			nw = true;
			south = (board.tiles[x + 1][y].isEmpty()) ? false : true;
			if (y == 0) {
				west = true;
				sw = true;
				se = (board.tiles[x + 1][y + 1].isEmpty()) ? false : true;
				east = (board.tiles[x][y + 1].isEmpty()) ? false : true;
			} else if (y == tiles[0].length - 1) {
				east = true;
				se = true;
				west = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
				sw = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
			}	else {
				west = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
				sw = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
				se = (tiles[x + 1][y + 1].isEmpty()) ? false : true;
				east = (tiles[x][y + 1].isEmpty()) ? false : true;
			} 
		} else if (x == tiles.length - 1) {
			south = true;
			sw = true;
			se = true;
			north = (board.tiles[x - 1][y].isEmpty()) ? false : true;
			if (y == 0) {
				west = true;
				nw = true;
				ne = (tiles[x - 1][y + 1].isEmpty()) ? false : true;
				east = (tiles[x][y + 1].isEmpty()) ? false : true;
			} else if (y == tiles[0].length - 1) {
				east = true;
				ne = true;
				nw = (tiles[x - 1][y - 1].isEmpty()) ? false : true;
				west = (tiles[x][y - 1].isEmpty()) ? false : true;
			}	else {
				west = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
				sw = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
				se = (tiles[x + 1][y + 1].isEmpty()) ? false : true;
				east = (tiles[x][y + 1].isEmpty()) ? false : true;
			} 
		} else {
			south = (board.tiles[x + 1][y].isEmpty()) ? false : true;
			north = (board.tiles[x - 1][y].isEmpty()) ? false : true;
			if (y == 0) {
				west = true;
				nw = true;
				ne = (tiles[x - 1][y + 1].isEmpty()) ? false : true;
				east = (tiles[x][y + 1].isEmpty()) ? false : true;
				se = (tiles[x + 1][y + 1].isEmpty()) ? false : true;
				sw = true;
			} else if (y == tiles[0].length - 1) {
				west = (tiles[x][y - 1].isEmpty()) ? false : true;
				nw = (tiles[x - 1][y - 1].isEmpty()) ? false : true;
				ne = true
				east = true;
				se = true
				sw = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
			}	else {
				west = (tiles[x][y - 1].isEmpty()) ? false : true;
				nw = (tiles[x - 1][y - 1].isEmpty()) ? false : true;
				ne = (tiles[x - 1][y + 1].isEmpty()) ? false : true;
				east = (tiles[x][y + 1].isEmpty()) ? false : true;
				se = (tiles[x + 1][y + 1].isEmpty()) ? false : true;
				sw = (tiles[x + 1][y - 1].isEmpty()) ? false : true;
			}
		} 

		if ((north && south && east && west) || (nw && ne && se && sw) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < tiles.length; i++) {
			stringBuilder.append(i);
			stringBuilder.append("║");
			for (int y = 0; y < tiles[0].length; y++) {
				if (!tiles[i][y].isEmpty()) {
					String v = tiles[i][y].getPieces().get(0).toString();
					int l = v.length();
					if (l < 5) {
						int padding = (5 - l)/2;
						for (int z = 0; z < padding; z++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append(v);
						for (int z = 0; z < padding; z++) {
							stringBuilder.append(" ");
						}
						if ((5 - l) % 2 == 1) {
							stringBuilder.append(" ");
						}
					} else {
						stringBuilder.append(v);
					}
				} else {
					stringBuilder.append(" ……… ");
				}
				stringBuilder.append("|");
			}

			stringBuilder.append("\n");
		}
		stringBuilder.append(" ╚");
		for (int y = 0; y < tiles[0].length; y++) {
			stringBuilder.append("======");
		} 
		stringBuilder.append("\n  ");
		for (int y = 0; y < tiles[0].length; y++) {
			if (y < 10) {
				stringBuilder.append("  " + y + "  |");
			} else {
				stringBuilder.append(" " + y + "  |");			
			}
		} 

		return stringBuilder.toString();
	}

}