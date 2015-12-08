import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

/**
 * Abstract ReaderDecorator class which our Reader Decorators should inherit from
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */

public abstract class ReaderDecorator extends Reader {

	protected Reader readerToBeDecorated;
	
	public ReaderDecorator (Reader readerToBeDecorated) {
		this.readerToBeDecorated = readerToBeDecorated;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void close () throws IOException {
		readerToBeDecorated.close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void mark (int readAheadLimit) throws IOException {
		readerToBeDecorated.mark(readAheadLimit);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean markSupported () {
		return readerToBeDecorated.markSupported();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int read () throws IOException {
		return readerToBeDecorated.read();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int read (char[] cbuf, int off, int len) throws IOException {
		return readerToBeDecorated.read(cbuf, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int read (CharBuffer target) throws IOException {
		return readerToBeDecorated.read(target);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean ready () throws IOException {
		return readerToBeDecorated.ready();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void reset () throws IOException  {
		readerToBeDecorated.reset();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public long skip (long n) throws IOException {
		return readerToBeDecorated.skip(n);
	}
}
