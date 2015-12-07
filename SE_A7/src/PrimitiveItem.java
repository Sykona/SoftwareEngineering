import java.util.ArrayList;
import java.util.List;

/**
 * The Class PrimitiveItem.
 * This abstract class handles all methods, that are required for every primitive item,
 * so for the actual primitive items we can concentrate solely on the specific details
 *
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public abstract class PrimitiveItem implements Item {

	private String name;
	private double price;
	
	/**
	 * Instantiates a new primitive item.
	 *
	 * @param name the name
	 * @param price the price
	 */
	public PrimitiveItem(String name, double price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * Primitive items don't have children, so we always return null;
	 *
	 * @return the child
	 */
	public Item getChild() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Item#getPrice()
	 */
	public double getPrice() {
		return price;
	}
	
	/* (non-Javadoc)
	 * @see Item#getName()
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see Item#getChildren()
	 */
	public List<Item> getChildren() {
		return new ArrayList<Item>();
	}
	
	/* (non-Javadoc)
	 * @see Item#find(java.lang.String)
	 */
	public Item find(String name) {
		
		if (this.name.equals(name))
			return this;
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Item#add(Item)
	 */
	public void add(Item i) {}
}
