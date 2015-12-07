import java.io.IOException;
import java.io.Writer;

public abstract class WriterDecorator extends Writer {
	
	protected Writer writerToBeDecorated;

	public WriterDecorator(Writer writerToBeDecorated) {
		this.writerToBeDecorated = writerToBeDecorated;
	}
	
	@Override
	public void close() throws IOException {
		writerToBeDecorated.close();
	}

	@Override
	public void flush() throws IOException {
		writerToBeDecorated.flush();
	}
	
	@Override
	public void write (char[] cbuf) throws IOException {
		writerToBeDecorated.write(cbuf);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		writerToBeDecorated.write(cbuf, off, len);
	}
	
	@Override
	public void write (int c) throws IOException {
		writerToBeDecorated.write(c);
	}
	
	@Override
	public void write (String str) throws IOException {
		writerToBeDecorated.write(str);
	}
	
	@Override
	public void write (String str, int off, int len) throws IOException {
		writerToBeDecorated.write(str, off, len);
	}
}
