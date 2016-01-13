import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public void addRandomCell() {
		List<Cell> list = availableCells();
		if (!list.isEmpty()) {
			int index = new Random().nextInt(list.size());
			list.get(index).setValue( Math.random() < 0.9 ? 2 : 4 );
		}
	}
	
	private List<Cell> availableCells() {
		final List<Cell> list = new ArrayList<Cell>();
		for (Cell[] cArr: board) {
			for (Cell c: cArr) {
				if (c.isEmpty())
					list.add(c);
			}
		}
		return list;
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
