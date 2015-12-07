import java.util.ArrayList;
import java.util.List;

public class CompositItem implements Item {
	
	List<Item> children;
	String name;
	
	public CompositItem(String name) {
		this.name = name;
		children = new ArrayList<Item>();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Item> getChildren() {
		return children;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void add(Item item) {
		children.add(item);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public double getPrice() {
		
		double price = 0;
		
		for(Item i : children)
			price = price + i.getPrice();
		
		return price;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * {@inheritDoc}
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
