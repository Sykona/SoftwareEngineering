import java.util.ArrayList;
import java.util.List;

/**
 * The Class CompositItem.
 * A CompositItem is a composition of Items (CompositItems and/or PrimitiveItems)
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class CompositItem implements Item {
	
	List<Item> children;
	String name;
	
	/**
	 * Instantiates a new composit item.
	 *
	 * @param name the name
	 */
	public CompositItem(String name) {
		this.name = name;
		children = new ArrayList<Item>();
	}

	/* (non-Javadoc)
	 * @see Item#getChildren()
	 */
	public List<Item> getChildren() {
		return children;
	}
	
	/* (non-Javadoc)
	 * @see Item#add(Item)
	 */
	public void add(Item item) {
		children.add(item);
	}
	
	/* (non-Javadoc)
	 * @see Item#getPrice()
	 */
	public double getPrice() {
		
		double price = 0;
		
		for(Item i : children)
			price = price + i.getPrice();
		
		return price;
	}
	
	/* (non-Javadoc)
	 * @see Item#getName()
	 */
	public String getName() {
		return this.name;
	}
	
	/* (non-Javadoc)
	 * @see Item#find(java.lang.String)
	 */
	public Item find(String name) {
		
		Item item = null;
		
		if (name.equals(this.name))
			return this;
		
		for (Item i : children) {
			item = i.find(name);
			if (item != null)
				break;
		}
		
		return item;
	}
}
