import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		
		Item root = XMLConverter.process(args[0]);
		
		Scanner scanner = new Scanner(System.in);
		String name = "";
		
		System.out.println("Type in the name of an Item and press ENTER (type EXIT to exit)");
		
		while(true) {
			name = scanner.nextLine();
			
			if(name.equals("EXIT"))
				break;
			
			Item req = root.find(name);
			double price = req.getPrice();
			System.out.println(price);
		}
		scanner.close();
	}
}
