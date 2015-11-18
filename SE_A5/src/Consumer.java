import java.io.PrintStream;

public class Consumer<T> extends Thread{

	private ConcurrentRingBuffer<T> buffer;
	private volatile int consumed;
	private PrintStream stream;

	
	public Consumer(ConcurrentRingBuffer<T> buffer, PrintStream stream) {
		consumed = 0;
		this.buffer = buffer;
		this.stream = stream;
	}
	
	public void run() {
		try {
			while(!isInterrupted()) {
				stream.println("removed " + buffer.remove());
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
