import java.util.List;

public class StandardCell extends Cell {
	
	final int[] values = { 2 , 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
	
	public StandardCell() {
	}
	
	public StandardCell(int value) {
		this.value = value;
		for (int i : values) {
			if (value == i)
				return;
		}
		System.err.println("Standard Cell value must be 2⁰, 2¹, ... , 2¹¹");
	}
	
	@Override
	public boolean isEmpty() {
		return value == 0;
	}
	
	@Override
	public String toString() {
		if (isEmpty())
			return String.format("%4s", " ");
		else
			return String.format("%4s", value);
	}

	@Override
	public List<Cell> merge(List<Cell> line) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
