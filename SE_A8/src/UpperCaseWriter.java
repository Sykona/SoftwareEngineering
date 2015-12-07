import java.io.Writer;

public class UpperCaseWriter extends WriterDecorator {

	public UpperCaseWriter(Writer writerToBeDecorated) {
		super(writerToBeDecorated);
	}

}
