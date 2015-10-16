import java.lang.reflect.Array;

/**
 * Basic implementation of a LIFO stack,
 * using an array and the standard stack 
 * interface {@link Stack}.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> generic type argument T
 */
public class ArrayStack<T> implements Stack<T> {
	private T[] array;
	private int index;
	private final int CAPACITY;
	
	/**
	 * Constructor for an array stack
	 * @param size the size of the stack
	 */
	public ArrayStack (int size){
		if (size <= 0)
			throw new IllegalArgumentException("invalid size");
		this.CAPACITY = size;
		this.index = -1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getStackSize() {
		return index;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void push(T item) {
		if(array == null)
			array = (T[]) Array.newInstance(item.getClass(), CAPACITY);
		else if(index >= array.length-1)
			throw new RuntimeException("stack full");
		index ++;
		array[index] = item;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() {
		if(index < 0)
			throw new RuntimeException("empty stack");
		T pop = array[index];
		index --;
		return pop;
	}

}
