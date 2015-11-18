import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		File a = new File("/home/oremy/Documents/uni_salzburg/3.Semester/softwareEngineering/input.txt");
		ConcurrentRingBuffer<String> buffer = new ConcurrentRingBuffer<String>(50);
		
		Producer<String> p = new Producer<String>(buffer, a);
		Consumer<String> c1 = new Consumer<String>(buffer, System.out);
		Consumer<String> c2 = new Consumer<String>(buffer, System.err);
		
		
		
		p.start();
		c1.start();
		c2.start();
		
		try {
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		if (c1.getState() == Thread.State.WAITING && c2.getState() == Thread.State.WAITING) {
		if (buffer.isEmpty()) {
			c1.interrupt();
			c2.interrupt();
			
			System.out.println("p produced:  " + p.getProduced());
			System.out.println("c1 consumed: " + c1.getConsumed());
			System.out.println("c2 consumed: " + c2.getConsumed());
		}

			
	
	}

}
