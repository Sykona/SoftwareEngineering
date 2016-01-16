import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game2048 {
	
	private Cell[][] board;
	int score;
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
			System.out.print("Game over! Your score was: " + getScore() + " Points!");
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
	
	
	public boolean isWin() {
		return win;
	}
	
	public boolean isLoose() {
		if (!isWin() && !canMove())
			return true;
		return false;
	}
	
	public int getScore() {
		return score;
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
	
	private boolean isFull() {
		return availableCells().size() == 0;
	}
	
	public boolean canMove() {
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
	
	private Cell[] mergeLinetoLeft(Cell[] oldLine) {
		ArrayList<Cell> newLine = new ArrayList<Cell>();
		int shift = 0;
		
		for (int i = 0; i < 4; i ++) {
			if (!oldLine[i].isEmpty()) {
				Cell temp = oldLine[i];
				int j = i + 1;
				while (j < 4) {
					if (!temp.merge(oldLine[j])) {
						j = 3;
					} else if (temp.merge(oldLine[j]) || oldLine[j].merge(temp)) {
						score += temp.getScore();
						temp.increaseShift(oldLine[j].getShift() + (j-i));
						if (temp.getValue() == 2048)
							win = true;
						i += j - i;
					}
					j ++;
				}
				temp.increaseShift(shift);
				if (!temp.isVisible())
					newLine.add(new StandardCell());
				else
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
	
	public void leftMove () {
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
	
	public void rightMove () {
		rotateRight();
		rotateRight();
		leftMove();
		rotateRight();
		rotateRight();
	}
	
	public void upMove () {
		rotateRight();
		rotateRight();
		rotateRight();
		leftMove();
		rotateRight();
	}
	
	public void downMove () {
		rotateRight();
		leftMove();
		rotateRight();
		rotateRight();
		rotateRight();
	}
	
	

	private void rotateRight() {
		final int X = board.length;
		Cell[][] ret = new Cell[X][X];
		
		for (int i = 0; i < X; i ++) 
			for (int j = 0; j < X; j ++) 
				ret[j][X-1-i] = board[i][j];
		
		board = ret;
	}
	
}
