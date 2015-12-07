import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader test = new BufferedReader(new FileReader("input.txt"));
		Rot13Reader reader = new Rot13Reader(test);
		while (reader.ready()) {
			System.out.print((char) reader.read());
		}
		reader.close();
		
		/*
		OutputStream output = new FileOutputStream(new File("output.txt"));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(output));
		write.write("Alle meine Entchen schwimmen auf dem See, Köpfchen in das Wassser, Schwänzchen in die Höh!");
		write.close();*/
	}

}
