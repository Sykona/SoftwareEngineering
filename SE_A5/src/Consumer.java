import java.io.PrintStream;

/**
 * The Class Consumer.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> the generic type
 */
public class Consumer<T> extends Thread{


	private ConcurrentRingBuffer<T> buffer;
	private PrintStream stream;
	private int consumed;

	
	/**
	 * Instantiates a new consumer.
	 *
	 * @param name the name
	 * @param buffer the thread-save FIFO-Buffer
	 * @param stream the stream
	 */
	public Consumer(String name, ConcurrentRingBuffer<T> buffer, PrintStream stream) {
		super(name);
		this.buffer = buffer;
		this.stream = stream;
		consumed = 0;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			while(!isInterrupted()) {
				stream.println("removed " + buffer.remove());
				consumed++;
				
			}
		} catch (InterruptedException e) { 
			stream.close();
		}
	}
	
	
	/**
	 * Gets the number of consumed items.
	 *
	 * @return the consumed
	 */
	public synchronized int getConsumed() {
		return consumed;
	}
	
	
}
