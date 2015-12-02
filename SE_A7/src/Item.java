import java.util.List;

public interface Item {
	
	public List<Item> getChildren();
	
	public double getPrice();
	
	public String getName();
	
	public Item find(String name);
	
	public void add(Item item);

}
