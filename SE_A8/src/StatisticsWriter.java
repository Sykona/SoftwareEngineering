import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * This class decorates the writer classes out the java.io package so that
 * on the close() or the flush() method a statistic for each character wrote is written
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */

public class StatisticsWriter extends WriterDecorator {

	private HashMap<Character, Integer> statistic = new HashMap<Character, Integer>();
	
	public StatisticsWriter(Writer writerToBeDecorated) {
		super(writerToBeDecorated);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		writeStatistics();
		writerToBeDecorated.close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException {
		writeStatistics();
		writerToBeDecorated.flush();
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
			Character c = cbuf[off+i];
			Integer old = statistic.get(c);
			if (old == null)
				statistic.put(c, 1);
			else
				statistic.put(c, old+1);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (int c) throws IOException {
		Character ch = new Character((char) c);
		Integer old = statistic.get(ch);
		if (old == null)
			statistic.put(ch, 1);
		else
			statistic.put(ch, old+1);
		writerToBeDecorated.write(c);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (String str) throws IOException {
		for (Character c : str.toCharArray()) {
			Integer old = statistic.get(c);
			if (old == null)
				statistic.put(c, 1);
			else
				statistic.put(c, old+1);
		}
		writerToBeDecorated.write(str.toUpperCase());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write (String str, int off, int len) throws IOException {
		String start = str.substring(0, off);
		String end = str.substring(off+len, str.length());
		String toAdd = str.substring(off, off+len);
		for (Character c : toAdd.toCharArray()) {
			Integer old = statistic.get(c);
			if (old == null)
				statistic.put(c, 1);
			else
				statistic.put(c, old+1);
		}
		writerToBeDecorated.write(start+toAdd+end, off, len);
	}
	
	private void writeStatistics() throws IOException {
		StringBuilder sb = new StringBuilder();
		statistic.remove('\n');
		for (Map.Entry<Character, Integer> entry : statistic.entrySet()) {
			sb.append("\n Character: '");
			sb.append(entry.getKey());
			sb.append("' Wrote times: ");
			sb.append(entry.getValue());
		}
		sb.append("\n");
		writerToBeDecorated.write(sb.toString());
	}

}
