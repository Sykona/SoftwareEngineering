import java.io.IOException;
import java.io.Writer;

/**
 * This class decorates the writer classes out the java.io package so that
 * every character that is written is translated into its upper representation
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */

public class UpperCaseWriter extends WriterDecorator {

	public UpperCaseWriter(Writer writerToBeDecorated) {
		super(writerToBeDecorated);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (char[] cbuf) throws IOException {
		writerToBeDecorated.write(cbuf);
		for (char c : cbuf) {
			c = Character.toUpperCase(c);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		writerToBeDecorated.write(cbuf, off, len);
		for (int i = 0; i < len; i ++) {
			cbuf[off + i ] = Character.toUpperCase(cbuf[off + i]);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (int c) throws IOException {
		c = Character.toUpperCase((char) c);
		writerToBeDecorated.write(c);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (String str) throws IOException {
		writerToBeDecorated.write(str.toUpperCase());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (String str, int off, int len) throws IOException {
		String start = str.substring(0, off);
		String end = str.substring(off+len, str.length());
		String toUpper = str.substring(off, off+len);
		writerToBeDecorated.write(start+toUpper+end, off, len);
	}

}
