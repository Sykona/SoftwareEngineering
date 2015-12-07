import java.util.List;

/**
 * The Interface Item, based on Composit-Designpattern.
 * Items can be either Compositions of Items or primitive Items
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public interface Item {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice();
	
	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<Item> getChildren();
	
	/**
	 * Find.
	 *
	 * @param name the name
	 * @return the item
	 */
	public Item find(String name);
	
	/**
	 * Adds the.
	 *
	 * @param item the item
	 */
	public void add(Item item);

}
