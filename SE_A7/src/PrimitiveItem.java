import java.util.ArrayList;
import java.util.List;

public abstract class PrimitiveItem implements Item {

	protected String name;
	protected double price;
	
	public PrimitiveItem(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Item getChild() {
		return null;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Item> getChildren() {
		return new ArrayList<Item>();
	}
	
	public Item find(String name) {
		
		if (this.name.equals(name))
			return this;
		
		return null;
	}
	
	public void add(Item i) {}
}
