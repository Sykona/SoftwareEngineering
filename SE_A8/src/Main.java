import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReaderDecorator reader = new BufferedReaderDecorator(new FileReader("input.txt"));
		while (reader.ready()) {
			System.out.println(reader.readLine());
		}
		reader.close();
		
		OutputStream output = new FileOutputStream(new File("output.txt"));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(output));
		write.write("Alle meine Entchen schwimmen auf dem See, Köpfchen in das Wassser, Schwänzchen in die Höh!");
		write.close();
	}

}
