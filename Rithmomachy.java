package rithmomachy;

public class Rithmomachy { 
	Board board;

	public Rithmomachy () {
		board = new Board();
		board.setUp();
	}



	public boolean move(int startX, int startY, int endX, int endY) {
		if (startX < 0 || endX < 0 || startY < 0 || endY < 0 
				|| startX >= board.tiles.length 
				|| startY >=board.tiles[0].length
				|| endX >= board.tiles.length 
				|| endY >=board.tiles[0].length) {

			System.out.println("Illegal Move: Out of bounds");
			return false;
		} 

			if (board.movePiece(startX, startY, endX, endY)) {
				System.out.println(board);
				return true;
			}

		return false;


	}

	public static void main(String [] args) {
		Rithmomachy r = new Rithmomachy();
		System.out.println(r.board);
		r.move(2,5,1,6);
		r = new Rithmomachy();
		r.move(-1,5,1,6);
		r = new Rithmomachy();
		r.move(2,5,1,5);
		r = new Rithmomachy();
		r.move(2,5,3,5);

	}


}