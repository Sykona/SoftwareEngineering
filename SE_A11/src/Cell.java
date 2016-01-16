
public abstract class Cell{
	
	protected int shift = 0;
	protected int value = 0;
	protected int bonusScore = 0;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void increaseShift(int shift) {
		this.shift += shift;
	}
	
	public int getShift() {
		return shift;
	}
	
	public abstract boolean isEmpty();
	
	public abstract boolean merge(Cell c);
	
	public abstract int getScore();
	
	public abstract boolean isVisible();
}
