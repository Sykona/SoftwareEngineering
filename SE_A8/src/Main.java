import java.io.*;

/**
 * Test Class for our Rot13Reader and the UpperCaseWriter, which
 * decorate the Reader respectively the Writer class.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		
		File file = new File("test.txt");
		FileOutputStream fileOutStream = new FileOutputStream(file);
		OutputStreamWriter outStreamWriter = new OutputStreamWriter(fileOutStream);
		BufferedWriter buffWriter = new BufferedWriter(outStreamWriter);
		UpperCaseWriter upperWriter = new UpperCaseWriter(buffWriter);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(fileReader);
		Rot13Reader rot13Reader = new Rot13Reader(buffReader);
		
		// We write a String in lower case to a file test.txt, due to the UpperCaseWriter, it should be upper case
		upperWriter.write("alle meine Entchen schwimmen auf dem see, köpfchen in das wasser, schwänzchen in die höh!");
		upperWriter.flush();
		
		// Then we read out of the file test.txt with our Rot13Reader, so it should be rot13 ciphered
		StringBuilder sb = new StringBuilder();
		if (rot13Reader.ready()) {
			sb.append((char) rot13Reader.read());
			rot13Reader.mark(0);
		}
		while (rot13Reader.ready()) {
			sb.append((char) rot13Reader.read());
		}
		
		// Then we write again the rot13ciphered String we read to the file test.txt
		String rot13ciphered = sb.toString();
		upperWriter.write("\n");
		upperWriter.write(rot13ciphered);
		upperWriter.flush();
		
		// Then we read again and double rot13 ciphered should be the original text due to the alphabet
		// has 26 letters and we print out our solution to the console
		rot13Reader.reset();
		while(rot13Reader.ready()) {
			System.out.print((char) rot13Reader.read());
		}
		
		// Now we check the statistic writer which also decorates the Writer classes
		StatisticsWriter statisticWriter = new StatisticsWriter(upperWriter);
		statisticWriter.write("\nALLE MEINE ENTCHEN SCHWIMMEN AUF DEM SEE, KÖPFCHEN IN DAS WASSER, SCHWÄNZCHEN IN DIE HÖH!\n");
		statisticWriter.write(rot13ciphered);
		statisticWriter.close();
		while(buffReader.ready()) {
			System.out.print((char) buffReader.read());
		}
		
		rot13Reader.close();
	}

}
