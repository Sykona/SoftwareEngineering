import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReaderDecorator reader = new BufferedReaderDecorator(new FileReader("input.txt"));
		while (reader.ready()) {
			System.out.println(reader.readLine());
		}
		reader.close();
		
		
	}

}
