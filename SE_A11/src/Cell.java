import java.util.List;

public abstract class Cell{
	
	protected int shift = 0;
	
	public abstract boolean isEmpty();
	
	public abstract List<Cell> merge(List<Cell> line);
	
}
