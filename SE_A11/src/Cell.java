import java.util.List;

public abstract class Cell{
	
	protected int shift = 0;
	protected int value = 0;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public abstract boolean isEmpty();
	
	public abstract List<Cell> merge(List<Cell> line);
	
}
