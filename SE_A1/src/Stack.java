/**
 * Interface for the standard operations on a LIFO
 * ("Last in, first out") stack.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> generic type argument T
 */
public interface Stack<T>{
	
	/**
	 * Returns the current amount of items on the stack.
	 * @return amount of items on the stack
	 */
	public int getStackSize();
	
	/**
	 * Pushes the item on the stack.
	 * @param item to be pushed on the stack
	 */
	public void push (T item);
	
	/**
	 * Pops the first (the top) item off the stack.
	 * @return first item on the stack
	 */
	public T pop ();
}
