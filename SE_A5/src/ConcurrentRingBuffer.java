/**
 * The Class ConcurrentRingBuffer represents a thread-save Ringbuffer based on a generic Array.
 * The Type of the Buffer is determined by the first inserted Item and cannot be changed afterwards.
 * 
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> the generic type
 */
public class ConcurrentRingBuffer<T> {


	private T[] buffer;
	private volatile int head;
	private volatile int tail;
	private final int CAPACITY;

	
	/**
	 * Instantiates a new concurrent ring buffer.
	 *
	 * @param size the size
	 */
	public ConcurrentRingBuffer(int size) {
		
		if (size > 0) {
			CAPACITY = size;
			head = CAPACITY - 1;
			tail = CAPACITY - 1;
		}
		else {
			throw new IllegalArgumentException("buffer size must be at least 1");
		}
		
	}
	
	
	/**
	 * puts an item at the head index of the buffer. If the buffer is full, it waits and frees the lock.
	 *
	 * @param item the item
	 * @throws InterruptedException the interrupted exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized void put(T item) throws InterruptedException {
		
		if (buffer == null) {
			buffer = (T[]) new Object[CAPACITY];
		}		
		
		while(isFull()) {
			this.wait();
		}
		
		buffer[head] = item;
		
		// we use Math.floorMod since the % operator returns negative remainders in java
		head = Math.floorMod((head - 1), CAPACITY);

		this.notifyAll();
	}
	
	
	/**
	 * Removes the item at the tail index of the buffer. If the buffer is empty, it waits and frees the lock.
	 *
	 * @return the tail item
	 * @throws InterruptedException the interrupted exception
	 */
	public synchronized T remove() throws InterruptedException {
			
		while(isEmpty()) {
			this.wait();
		}
		
		int oldTail = tail;
		
		// we use Math.floorMod since the % operator returns negative remainders in java
		tail = Math.floorMod((tail - 1), CAPACITY);
		
		final T oldTailItem = buffer[oldTail];
		buffer[oldTail] = null;
			
			
		this.notifyAll();
		
		return oldTailItem;
	}
	 
	
	/**
	 * Checks if the buffer is empty.
	 *
	 * @return true, if is empty
	 */
	public synchronized boolean isEmpty() {
		
		return tail == head;
	}
	
	
	/**
	 * Checks if the buffer is full.
	 *
	 * @return true, if is full
	 */
	public synchronized boolean isFull() {
		
		// we use Math.floorMod since the % operator returns negative remainders in java
		return Math.floorMod((head - tail), CAPACITY) == 1;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for (int i = 0; i < CAPACITY; i++) {
			if (buffer[i] != null)
				sb.append(buffer[i].toString() + ", ");
			else
				sb.append("null, ");
		}
		
		sb.setLength(sb.length() - 2);
		sb.append("]");
		
		return sb.toString();
	}
}