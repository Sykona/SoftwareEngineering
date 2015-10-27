import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class test {

	public static <T> void main(String[] args) throws FileNotFoundException {

		Class<ArrayList> ArrayListDummy = ArrayList.class;
		Reconstructor re = new Reconstructor();
		
		re.reconstruct(ArrayListDummy, new PrintStream("output.java"));

	}

}
