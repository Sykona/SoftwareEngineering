
public class Game2048Board {
	
	private Cell [][] board;
	
	public Game2048Board (Cell[][] board) {
		this.board = board;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Cell[] cArr : board) {
			sb.append("|");
			for (Cell c: cArr) {
				sb.append(c);
				sb.append("|");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	public void slideRight() {
		
	}
	
	public void slideLeft() {
		rotateRight();
		rotateRight();
		slideRight();
		rotateRight();
		rotateRight();
	}
	
	public void slideUp() {
		rotateRight();
		slideRight();
		rotateRight();
		rotateRight();
		rotateRight();
	}
	
	public void slideDown() {
		rotateRight();
		rotateRight();
		rotateRight();
		slideRight();
		rotateRight();
	}
	
	public void rotateRight() {
		final int X = board.length;
		Cell[][] ret = new Cell[X][X];
		
		for (int i = 0; i < X; i ++) 
			for (int j = 0; j < X; j ++) 
				ret[j][X-1-i] = board[i][j];
		
		board = ret;
	}
}
