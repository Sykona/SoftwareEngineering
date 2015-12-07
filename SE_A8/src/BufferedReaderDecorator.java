import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderDecorator extends BufferedReader {

	public BufferedReaderDecorator(Reader arg0) {
		super(arg0);
	}
	
	public BufferedReaderDecorator(Reader in, int sz) {
		super(in, sz);
	}
	
	/**
	 * Reads a single character and ciphers it with rot13
	 * 
	 * @return the int value of the rot 13 ciphered character read
	 * 
	 * @throws IOExeption if an I/O error occurs
	 */
	@Override
	public int read () throws IOException {
		return rot13encrypt(super.read());
	}
	
	/**
	 * Reads characters into a portion of an array and ciphers it with rot13
	 * 
	 * @param cbuf the array where to store the characters
	 * @param offset the offset on which position of the array we start
	 * @param length the length of characters to read
	 * 
	 * @return the number of characters read, or -1 if the end of the stream has been reached
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public int read (char[] cbuf, int offset, int length) throws IOException {
		int temp = super.read(cbuf, offset, length);
		for (int i = 0; i < length; i ++ ) {
			cbuf[offset + i] = (char) rot13encrypt(cbuf[offset + i]);
		}
		return temp;
	}
	
	/**
	 * Reads a whole line and ciphers each single character with rot13
	 * 
	 * @return a String which contains the whole line with rot13 ciphered characters
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public String readLine () throws IOException {
		String toEncrypt = super.readLine();
		StringBuilder sb = new StringBuilder();
		for (char c : toEncrypt.toCharArray() ) {
			sb.append((char) rot13encrypt(c));
		}
		return sb.toString();
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
