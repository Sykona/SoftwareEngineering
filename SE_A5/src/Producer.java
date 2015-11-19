import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Class Producer.
 *
 * @author Oliver Remy
 * @author Sebastian Strumegger
 * 
 * @param <T> the generic type
 */
public class Producer<T> extends Thread {


	private ConcurrentRingBuffer<T> buffer;
	private BufferedReader reader;
	private int produced;

	
	/**
	 * Instantiates a new producer.
	 *
	 * @param name the name
	 * @param buffer the thread-save FIFO-Buffer
	 * @param file the input-file
	 * @throws FileNotFoundException the file not found exception
	 */
	public Producer(String name, ConcurrentRingBuffer<T> buffer, File file) throws FileNotFoundException {
		super(name);
		this.buffer = buffer;
		this.reader = new BufferedReader(new FileReader(file));
		produced = 0;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@SuppressWarnings("unchecked")
	public void run() {
		
		String line;
		
		try {
			while ((line = reader.readLine()) != null && !isInterrupted()) {
				buffer.put((T)line);
				produced++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) { }
	}
	
	
	/**
	 * Gets the number of produced items
	 *
	 * @return the produced
	 */
	public int getProduced() {
		return produced;
	}
}
