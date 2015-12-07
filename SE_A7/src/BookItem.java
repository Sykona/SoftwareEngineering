
public class BookItem extends ComponentItem {
	
	private final String ISBN;
	
	public BookItem(String name, double price, String isbn) {
		super(name, price);
		this.ISBN = isbn;
	}
	
	public String getISBN() {
		return ISBN;
	}
}
