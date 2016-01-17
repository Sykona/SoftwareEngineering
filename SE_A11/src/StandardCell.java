/**
 * This class represents the standard cell for our 2048 game
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */

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
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean merge (Cell c) {
		if (c.isEmpty() || isEmpty()) {
			return false;
		} else if (c.getValue() == value) {
			value *= 2;
			return true;
		}
		return false;
	}

	@Override
	public int getScore() {
		return value + bonusScore;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible() {
		return true;
	}
	
	
}
