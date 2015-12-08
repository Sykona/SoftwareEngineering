import java.io.IOException;
import java.io.Writer;

/**
 * Abstract WriterDecorator class which our Reader Decorators should inherit from
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */

public abstract class WriterDecorator extends Writer {
	
	protected Writer writerToBeDecorated;

	public WriterDecorator(Writer writerToBeDecorated) {
		this.writerToBeDecorated = writerToBeDecorated;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		writerToBeDecorated.close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException {
		writerToBeDecorated.flush();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (char[] cbuf) throws IOException {
		writerToBeDecorated.write(cbuf);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		writerToBeDecorated.write(cbuf, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (int c) throws IOException {
		writerToBeDecorated.write(c);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (String str) throws IOException {
		writerToBeDecorated.write(str);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (String str, int off, int len) throws IOException {
		writerToBeDecorated.write(str, off, len);
	}
}
