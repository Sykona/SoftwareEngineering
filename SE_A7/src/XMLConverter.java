import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLConverter {
	
	public static Item process(String fileName) throws ParserConfigurationException, IOException, SAXException {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new File(fileName));
		doc.getDocumentElement().normalize();
		
		Element rootElement = doc.getDocumentElement();
		
		Item rootItem = new CompositItem(rootElement.getAttribute("name"));	
		
		traverseTree(rootElement, rootItem);
		
		return rootItem;
	}
	
	private static void traverseTree(Element rootElement, Item rootItem) {
		
		NodeList nodes = rootElement.getChildNodes();

		for (int i=0; i < nodes.getLength(); i++) {
			
			Node node = nodes.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				
				Element childElement = (Element) node;
				
				String name = childElement.getAttribute("name");
				
				
				if(childElement.hasChildNodes()) {
					
					Item childItem = new CompositItem(name);
					rootItem.add(childItem);
					
					traverseTree(childElement, childItem);
				}
				else {
					String itemType = childElement.getTagName();
					Item childItem;
					
					double price = Double.parseDouble(childElement.getAttribute("price"));
					
					switch(itemType) {
					
						case "book":	String isbn = childElement.getAttribute("isbn");
										childItem = new BookItem(name, price, isbn);
										break;
										
						case "cd":		childItem = new CDItem(name, price);
										break;
										
						default:		throw new RuntimeException("The Item-type <" + itemType + "> is unknown");
					}
					
					rootItem.add(childItem);
				}	
			}
		}
	}
}
