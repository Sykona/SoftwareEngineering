
public class Consumer<T> extends Thread{

	private ConcurrentRingBuffer<T> buffer;
	private volatile int consumed;

	
	public Consumer(ConcurrentRingBuffer<T> buffer) {
		consumed = 0;
		this.buffer = buffer;
	}
	
	public void run() {
		try {
			while(!isInterrupted()) {
				System.err.println("removed " + buffer.remove());
				consumed++;
			}
		} catch (InterruptedException e) {
			System.out.println("Consumer done");
		}
	}
	
	public synchronized int getConsumed() {
		return consumed;
	}
}
