import java.lang.reflect.Array;

public class ArrayStack<T> implements Stack<T> {
	private T[] array;
	private int index;
	private final int CAPACITY;
	
	public ArrayStack (int size){
		if (size <= 0)
			throw new IllegalArgumentException("invalid size");
		this.CAPACITY = size;
		this.index = -1;
	}
	
	@Override
	public int getStackSize() {
		return index;
	}

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

	@Override
	public T pop() {
		if(index < 0)
			throw new RuntimeException("empty stack");
		T pop = array[index];
		index --;
		return pop;
	}

}
