import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Class Test.
 *
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class Test {

	/**
	 * The main method for testing our Producer-Consumer-Pattern with the array-based buffer
	 *
	 * @param args the args
	 * @throws FileNotFoundException the file not found exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		String filePath = "/home/sebastian/Documents/Uni/se/A5/input.txt";
		
		ConcurrentRingBuffer<String> buffer = new ConcurrentRingBuffer<String>(50);
		File inputFile = new File(filePath);
		
		Producer<String> p = new Producer<String>("p", buffer, inputFile);
		Consumer<String> c1 = new Consumer<String>("c1", buffer, System.out);
		Consumer<String> c2 = new Consumer<String>("c2", buffer, System.err);
		
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
}
