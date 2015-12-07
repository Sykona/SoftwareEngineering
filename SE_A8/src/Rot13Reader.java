import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class Rot13Reader extends ReaderDecorator {

	public Rot13Reader(Reader readerToBeDecorated) {
		super(readerToBeDecorated);
	}
	
	@Override
	public int read () throws IOException {
		return rot13encrypt(readerToBeDecorated.read());
	}
	
	@Override
	public int read (char[] cbuf, int offset, int length) throws IOException {
		int temp = super.read(cbuf, offset, length);
		for (int i = 0; i < length; i ++ ) {
			cbuf[offset + i] = (char) rot13encrypt(cbuf[offset + i]);
		}
		return temp;
	}
	
	@Override
	public int read (CharBuffer target) throws IOException {
		int temp = readerToBeDecorated.read(target);
		String toDecorate = target.toString();
		for (int i = 0; i < target.length(); i ++ ) {
			target.put(i, (char) rot13encrypt(toDecorate.charAt(0)));
		}
		return temp;
	}
	
	private int rot13encrypt (int c) {
		if (c >= 'A' && c <= 'Z')
			// First subtract ASCII value of A, then we have
			// values between 0 and 25. Then add the 13 of the
			// rot 13 cipher, and evaluate it with modulo 26 
			// because we need again a value between 0 and 25.
			// at last add the ASCII value of A again.
			c = (((c - 'A') + 13 ) % 26 ) + 'A';
		else if (c >= 'a' && c <= 'z')
			// same scheme with lower case letters as in
			// the upper case if above
			c = (((c - 'a') + 13 ) % 26 ) + 'a';
		return c;
	}

}
