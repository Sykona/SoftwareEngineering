import java.io.PrintStream;

/**
 * The Class Consumer.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> the generic type
 */
public class Consumer extends Thread{


	private ConcurrentRingBuffer<String> buffer;
	private PrintStream stream;
	private int consumed;

	
	/**
	 * Instantiates a new consumer.
	 *
	 * @param name the name
	 * @param buffer the thread-save FIFO-Buffer
	 * @param stream the stream
	 */
	public Consumer(String name, ConcurrentRingBuffer<String> buffer, PrintStream stream) {
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
				stream.print("removed " + buffer.remove() + "\n");
				consumed++;
				
			}
		} catch (InterruptedException e) { }
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
