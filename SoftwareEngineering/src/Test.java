import java.util.Scanner;


public class Test {

	public static void main(String[] args) {
		
		if (args.length > 0) {
			for (String s : args)
				System.out.print(s + " ");
			System.out.println();
			printResult(args);
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Bitte Rechenaufgabe eingeben: ");
			printResult(sc.nextLine().split(" "));
			sc.close();
		}

	}
	
	public static void printResult(String[] args){
		RPNCalculator calculator = new RPNCalculator(new ListStack<>());
		try {
			System.out.println("Solution: " + calculator.calculate(args));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}
