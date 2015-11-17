

public class ConcurrentRingBuffer<T> {

	private T[] buffer;
	private volatile int head;
	private volatile int tail;
	private final int CAPACITY;
	
	
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
	
	@SuppressWarnings("unchecked")
	public void put(T item) throws InterruptedException {
		
		if (buffer == null) {
			buffer = (T[]) new Object[CAPACITY];
		}
		
		synchronized(this) {
			
			while(isFull()) {
				this.wait();
			}
			
			buffer[head] = item;
			head = Math.floorMod((head - 1), CAPACITY);

			this.notifyAll();
		}
	}
	
	public T remove() throws InterruptedException {
				
		synchronized(this) {
			
			while(isEmpty()) {
				this.wait();
			}
			
			int oldTail = tail;
			tail = Math.floorMod((tail - 1), CAPACITY);
			
			final T oldTailItem = buffer[oldTail];
			buffer[oldTail] = null;
			
			
			this.notifyAll();
			return oldTailItem;
		}
	}
	
	public synchronized boolean isEmpty() {
		return tail == head;
	}
	
	public synchronized boolean isFull() {
		return Math.floorMod((head - tail), CAPACITY) == 1;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < CAPACITY; i++) {
			if (buffer[i] != null)
				sb.append(buffer[i].toString() + ",,, ");
			else
				sb.append("null, ");
		}
		
		return sb.toString();
	}
}