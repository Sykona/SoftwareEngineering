import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public abstract class ReaderDecorator extends Reader {

	protected Reader readerToBeDecorated;
	
	public ReaderDecorator (Reader readerToBeDecorated) {
		this.readerToBeDecorated = readerToBeDecorated;
	}
	
	public void close () throws IOException {
		readerToBeDecorated.close();
	}
	
	public void mark (int readAheadLimit) throws IOException {
		readerToBeDecorated.mark(readAheadLimit);
	}
	
	public boolean markSupported () {
		return readerToBeDecorated.markSupported();
	}
	
	public int read () throws IOException {
		return readerToBeDecorated.read();
	}
	
	public int read (char[] cbuf, int off, int len) throws IOException {
		return readerToBeDecorated.read(cbuf, off, len);
	}
	
	public int read (CharBuffer target) throws IOException {
		return readerToBeDecorated.read(target);
	}
	
	public boolean ready () throws IOException {
		return readerToBeDecorated.ready();
	}
	
	public void reset () throws IOException  {
		readerToBeDecorated.reset();
	}
	
	public long skip (long n) throws IOException {
		return readerToBeDecorated.skip(n);
	}
}
