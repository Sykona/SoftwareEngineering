
/**
 * The Class BookItem.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class BookItem extends PrimitiveItem {
	
	private final String ISBN;
	
	/**
	 * Instantiates a new book item.
	 *
	 * @param name the name
	 * @param price the price
	 * @param isbn the isbn
	 */
	public BookItem(String name, double price, String isbn) {
		super(name, price);
		this.ISBN = isbn;
	}
	
	/**
	 * Gets the isbn.
	 *
	 * @return the isbn
	 */
	public String getISBN() {
		return ISBN;
	}
}
