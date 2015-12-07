import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * The Main class
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SAXException the SAX exception
	 */
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
