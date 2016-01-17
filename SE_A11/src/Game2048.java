import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * This class represents our 2048 game
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */
public class Game2048 {
	
	private Cell[][] board;
	private int score;
	private boolean win;
	
	public Game2048() {
		resetBoard();
		Scanner s = new Scanner(System.in);
		
		System.out.println(this);
		System.out.println("Bitte Spielzug mittels W (UP) A (LEFT) S (DOWN) oder D (Right) eingeben");
		
		while (!isWin() && !isLoose()) {
			switch (Character.toUpperCase(s.nextLine().charAt(0))) {
				case 'W' :
					upMove();
					break;
				case 'A' :
					leftMove();
					break;
				case 'S' :
					downMove();
					break;
				case 'D' :
					rightMove();
					break;
				default:
					System.err.println("Bitte Spielzug mittels W (UP) A(LEFT) S(DOWN) oder D(Right) eingeben");
			}
			System.out.println(this);
		}
		
		if (isWin()) {
			System.out.println("Yeaaah you won! Congratulations");
			s.close();
		}
		
		if (isLoose()) {
			System.out.print("Game over! Your score was: " + score + " Points!");
			s.close();
		}
	}

	private void resetBoard() {
		board = new Cell[4][4];
		for (Cell[] cArr: board)
			for (int i = 0; i < board.length; i ++)
				cArr[i] = new StandardCell();
		addRandomCell();
		addRandomCell();
		score = 0;
		win = false;
	}
	
	
	private boolean isWin() {
		return win;
	}
	
	private boolean isLoose() {
		if (!isWin() && !canMove())
			return true;
		return false;
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
	
	/**
	 * adds a new random cell to a free one with 80% approx a '2' with 10% a '4' and with another 10% a bonus cell '@'
	 */
	private void addRandomCell() {
		List<Cell> list = availableCells();
		if (!list.isEmpty()) {
			int index = new Random().nextInt(list.size());
			Cell c = list.get(index);
			int line = 0;
			int row = 0;
			for (int i = 0; i < 4; i ++) {
				for (int j = 0; j < 4; j ++) {
					if (board[i][j].equals(c)) {
						line = i;
						row = j;
					}
				}
			}
			double rnd = Math.random();
			if (rnd < 0.8)
				board[line][row] = new StandardCell(2);
			else if (rnd < 0.9 && rnd >= 0.8)
				board[line][row] = new StandardCell(4);
			else 
				board[line][row] = new BonusCell();
		}
	}
	
	/**
	 * @return a list of available cells
	 */
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
	
	/**
	 * @return wether no cell is free
	 */
	private boolean isFull() {
		return availableCells().size() == 0;
	}
	
	/**
	 * @return wether a move is possible
	 */
	private boolean canMove() {
		if (!isFull()) {
			return true;
		}
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				Cell c = board[i][j];
				if ( (i < 3 && c.getValue() == board[i + 1][j].getValue()) || (j < 3 && c.getValue() == board[i][j+1].getValue()) )
					return true;
			}
		}
		return false;
	}
	
	/**
	 * merges a line to left
	 * @param oldLine the line to be merged
	 * @return the merged line
	 */
	private Cell[] mergeLinetoLeft(Cell[] oldLine) {
		ArrayList<Cell> newLine = new ArrayList<Cell>();
		int shift = 0;
		
		for (int i = 0; i < 4; i ++) {
			if (!oldLine[i].isEmpty()) {
				Cell temp = oldLine[i];
				int j = i + 1;
				while (j < 4) {
					boolean merge = temp.merge(oldLine[j]);
					if (!merge && !oldLine[j].isEmpty()) {
						j = 3;
					} else if (merge || oldLine[j].merge(temp)) {
						score += temp.getScore();
						temp.setBonusScore(0);
						temp.increaseShift(oldLine[j].getShift() + (j-i));
						if (temp.getValue() == 2048)
							win = true;
						i += j - i;
						j = 4;
					}
					j ++;
				}
				temp.increaseShift(shift);
				if (temp.isVisible())
					newLine.add(temp);
			}
			else { shift ++; }
		}
		
		if (newLine.size() == 0) {
			return oldLine;
		} else {
			while (newLine.size() != 4)
				newLine.add(new StandardCell());
			return newLine.toArray(new Cell[4]);
		}
	}
	
	/**
	 * does a left move to the board
	 */
	private void leftMove () {
		boolean changed = false;
		for (int i = 0; i < 4; i ++) {
			Cell[] oldLine = board[i];
			Cell[] newLine = mergeLinetoLeft(oldLine);
			if (!Arrays.equals(oldLine, newLine))
					changed = true;
			board[i] = newLine;
		}
		if (!isFull() && changed) {
			addRandomCell();
		}
	}
	
	/**
	 * does a right move to the board
	 */
	private void rightMove () {
		rotateRight();
		rotateRight();
		leftMove();
		rotateRight();
		rotateRight();
	}
	
	/**
	 * does a up move to the board
	 */
	private void upMove () {
		rotateRight();
		rotateRight();
		rotateRight();
		leftMove();
		rotateRight();
	}
	
	/**
	 * does a down move to the board
	 */
	private void downMove () {
		rotateRight();
		leftMove();
		rotateRight();
		rotateRight();
		rotateRight();
	}
	
	/**
	 * rotates the board rightwards
	 */
	private void rotateRight() {
		final int X = board.length;
		Cell[][] ret = new Cell[X][X];
		
		for (int i = 0; i < X; i ++) 
			for (int j = 0; j < X; j ++) 
				ret[j][X-1-i] = board[i][j];
		
		board = ret;
	}
	
}
