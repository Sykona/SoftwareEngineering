import java.util.List;

/**
 * This interface implements the standard item of
 * of an item list (e.g. books, cds, ...)
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */

public interface Item {
	
	/**
	 * Gets the children, if there are one
	 * @return 	the children of the item, 
	 * 			otherwise returning empty list
	 */
	public List<Item> getChildren();
	
	/**
	 * Gets or calculates the price of an item
	 * @return 	the calculated price
	 */
	public double getPrice();
	
	/**
	 * Gets the name of the item
	 * @return	the name of the item
	 */
	public String getName();
	
	/**
	 * Finds the item with the given name if exists
	 * @param 	name the item name which we are searching
	 * @return	the item if its found,
	 * 			otherwise returning null
	 */
	public Item find(String name);
	
	/**
	 * Adds the given item
	 * @param item the item we want to add
	 */
	public void add(Item item);

}
