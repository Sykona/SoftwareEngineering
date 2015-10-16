
public class ListStack<T> implements Stack<T> {
	
	private int size;
	private LinkedList<T> list;
	
	public ListStack(){
		size = -1;
		list = new LinkedList<T>();
	}
	
	@Override
	public int getStackSize() {
		return size;
	}

	@Override
	public void push(T item) {
		list.add(item);
		size ++;
	}

	@Override
	public T pop() {
		size --;
		return list.get();
	}

}
