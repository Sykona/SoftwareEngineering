
public class Main {

	public static void main(String[] args) {
		
		Cell[][] cells = new Cell[4][4];
		
		for (Cell[] cArr: cells) {
			for (int i = 0; i < cArr.length; i ++) {
				cArr[i] = new StandardCell();
			}
		}
		
		cells[0][0] = new StandardCell(2048);
		
		Game2048Board board = new Game2048Board(cells);
		
		System.out.println(board);
		
		board.rotateRight();
		
		System.out.println(board);
		
		board.rotateRight();
		
		System.out.println(board);
		
	}

}
