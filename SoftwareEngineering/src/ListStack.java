/**
 * Basic implementation of a LIFO stack,
 * using a linked list {@link LinkedList} and
 * the standard stack interface {@link Stack}.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> generic type argument T
 */
public class ListStack<T> implements Stack<T> {
	
	private int size;
	private LinkedList<T> list;

	/**
	 * Standard constructor for a list stack
	 */
	public ListStack(){
		size = -1;
		list = new LinkedList<T>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getStackSize() {
		return size;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T item) {
		list.add(item);
		size ++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() {
		size --;
		return list.get();
	}

}
