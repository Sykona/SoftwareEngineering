import java.util.ArrayList;
import java.util.List;

public abstract class ComponentItem implements Item {

	protected String name;
	protected double price;
	
	public ComponentItem(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Gets the child of the item
	 * @return
	 */
	public Item getChild() {
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Item> getChildren() {
		return new ArrayList<Item>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Item find(String name) {
		
		if (this.name.equals(name))
			return this;
		
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void add(Item i) {}
}
