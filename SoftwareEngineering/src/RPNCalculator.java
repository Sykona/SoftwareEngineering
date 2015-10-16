import java.util.Locale;
import java.util.Scanner;

/**
 * Implementation of an "reverse-polish-notation"
 * calculator using a LIFO stack.  
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class RPNCalculator {
	
	private Stack<Number> stack;
	
	/**
	 * Standard constructor for the calculator.
	 * @param stack	the stack, which is used
	 */
	public RPNCalculator(Stack<Number> stack){
		this.stack = stack;
	}
	
	public static void main(String[] args) {
		if (args.length > 0) {
			for (String s : args)
				System.out.print(s + " ");
			System.out.println();
			printResult(args);
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter arithmetic problem: ");
			printResult(sc.nextLine().split(" "));
			sc.close();
		}
	}
	
	/**
	 * Prints the result of a calculation to the command line.
	 * @param args String array of arguments to calculate
	 */
	public static void printResult(String[] args){
		RPNCalculator calculator = new RPNCalculator(new ListStack<Number>());
		try {
			System.out.println("Solution: " + calculator.calculate(args));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Calculates a arithmetic problem in "reverse-polish-notation"
	 * Example:
	 * 	input: {"1.5" "2" "+" "3" "4" "-" "*"}
	 * 	output: -3.5
	 * @param args String array of arguments to calculate
	 * @return the solution of the problem
	 */
	public Number calculate(String[] args){
		for(String element: args)
			switch(element){
			case "+":
				stack.push(add(stack.pop(), stack.pop()));
				break;
			case "-":
				stack.push(sub(stack.pop(), stack.pop()));
				break;
			case "*":
				stack.push(mult(stack.pop(), stack.pop()));
				break;
			case "/":
				stack.push(div(stack.pop(), stack.pop()));
				break;
			default:
				stack.push(convert(element));
			}
		if (stack.getStackSize() != 0)
			throw new IllegalArgumentException("invalid input : please enter formula in reverse-polish-notation");
		else
			return stack.pop();
	}
	
	private Number add(Number a, Number b){
		if(a instanceof Float || b instanceof Float)
			return new Float(a.floatValue() + b.floatValue());
		else return new Integer(a.intValue() + b.intValue());
	}
	
	private Number sub(Number a, Number b){
		if(a instanceof Float || b instanceof Float)
			return new Float(b.floatValue() - a.floatValue());
		else return new Integer(b.intValue() - a.intValue());
	}
	
	private Number mult(Number a, Number b){
		if(a instanceof Float || b instanceof Float)
			return new Float(a.floatValue() * b.floatValue());
		else return new Integer(a.intValue() * b.intValue());
	}
	
	private Number div(Number a, Number b){
		if(a instanceof Float || b instanceof Float)
			return new Float(b.floatValue() / a.floatValue());
		else return new Integer(b.intValue() / a.intValue());
	}
	
	private Number convert(String s){
		Number n = null;
		Scanner scan = new Scanner(s);
		scan.useLocale(Locale.ENGLISH);
		if(scan.hasNextInt()){
			n = scan.nextInt();
		}
		else if(scan.hasNextFloat()){
			n = scan.nextFloat();
		}
		else{
			scan.close();
			throw new IllegalArgumentException("invalid input");
		}
		scan.close();
		return n;
	}
	
}
