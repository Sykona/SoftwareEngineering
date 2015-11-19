import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Class Test.
 *
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class Test {
	
	private static ArrayList<Thread> threads = new ArrayList<Thread>();

	/**
	 * The main method for testing our Producer-Consumer-Pattern with the array-based buffer
	 *
	 * @param args the args
	 * @throws FileNotFoundException the file not found exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		String filePath = "/home/sebastian/Documents/Uni/se/A5/input.txt";
		File inputFile = new File(filePath);
		
		ConcurrentRingBuffer<String> buffer = new ConcurrentRingBuffer<String>(50);
		
		Producer p = new Producer("p", buffer, inputFile);
		Consumer c1 = new Consumer("c1", buffer, System.out);
		Consumer c2 = new Consumer("c2", buffer, System.err);
		
		threads.add(p);
		threads.add(c1);
		threads.add(c2);
		
		
		
		p.start();
		c1.start();
		c2.start();
		
		// wait for the producer to finish, before executing further commands
		try {
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// when producer is finished and the buffer is empty, we can interrupt the consumers and join them... then print the results
		while(true) {
			if(buffer.isEmpty()) {
				c1.interrupt();
				c2.interrupt();
				c1.join();
				c2.join();
				break;
			}
		}
		
		System.out.println(p.getName() + " produced:  " + p.getProduced());
		System.out.println(c1.getName() + " consumed: " + c1.getConsumed());
		System.out.println(c2.getName() + " consumed: " + c2.getConsumed());
	}
	
	/**
	 * Interrupt all threads.
	 */
	public static void interruptAllThreads() {
		for(Thread t : threads)
			t.interrupt();
	}
}
