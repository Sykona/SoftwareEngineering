import java.util.Scanner;

public class Game2048 {
	
	Game2048Board board;
	
	public Game2048() {
		board = new Game2048Board();
		Scanner s = new Scanner(System.in);
		
		System.out.println(board);
		System.out.println("Bitte Spielzug mittels W (UP) A (LEFT) S (DOWN) oder D (Right) eingeben");
		
		while (!board.isWin() && !board.isLoose()) {
			switch (Character.toUpperCase(s.nextLine().charAt(0))) {
				case 'W' :
					board.upMove();
					break;
				case 'A' :
					board.leftMove();
					break;
				case 'S' :
					board.downMove();
					break;
				case 'D' :
					board.rightMove();
					break;
				default:
					System.err.println("Bitte Spielzug mittels W (UP) A(LEFT) S(DOWN) oder D(Right) eingeben");
			}
			System.out.println(board);
		}
		
		if (board.isWin()) {
			System.out.println("Yeaaah you won! Congratulations");
			s.close();
		}
		
		if (board.isLoose()) {
			System.out.print("Game over! Your score was: " + board.getScore() + " Points!");
			s.close();
		}
	}
	
}
