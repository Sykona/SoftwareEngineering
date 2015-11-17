import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Producer<T> extends Thread {

	private ConcurrentRingBuffer<T> buffer;
	private BufferedReader reader;
	private volatile int produced;
	private volatile boolean done;
	
	Object lock = new Object();

	public Producer(ConcurrentRingBuffer<T> buffer, File file) throws FileNotFoundException {
		this.buffer = buffer;
		reader = new BufferedReader(new FileReader(file));
		produced = 0;
		done = false;
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		
		String line;
		
		try {
			while ((line = reader.readLine()) != null && !isInterrupted()) {
				buffer.put((T)line);
				System.out.println("added " + line);
				produced++;
			}
			done = true;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int getProduced() {
		return produced;
	}
	
	public synchronized boolean isDone() {
		return done;
	}
}
