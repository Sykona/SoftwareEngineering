package ps_se_ws2015.remy;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class test {

	public static <T> void main(String[] args) throws FileNotFoundException, ClassNotFoundException {

		Class<ArrayList> ArrayListDummy = ArrayList.class;
		Reconstructor re = new Reconstructor();
		
	//	String linkedListClass = LinkedList.class.getCanonicalName();
		re.reconstruct(ArrayListDummy, System.out);
		//re.reconstruct(ArrayListDummy, new PrintStream("ArrayListDummy.java"));
		//re.reconstruct(linkedListClass, new PrintStream("LinkedListDummy.java"));
		
	}
}
