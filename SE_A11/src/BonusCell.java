
public class BonusCell extends Cell {
	private int count = 0;
	final char column = '@';
	
	public BonusCell () {
		value = 0;
	}
	
	@Override
	public boolean isEmpty() {
		if (count != 0) 
			return true;
		else 
			return false;
	}

	@Override
	public boolean merge(Cell c) {
		if (count == 0 && !c.isEmpty()) {
			count ++;
			c.bonusScore += c.value;
			return true;
		}
		else return false;
	}

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return String.format("%4s", " ");
		}
		else {
			return String.format("%4s", column );
		}
	}

	@Override
	public boolean isVisible() {
		if (count != 0)
			return false;
		return true;
	}
}
