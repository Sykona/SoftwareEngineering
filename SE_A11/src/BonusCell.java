/**
 * This class represents the bonus cell for our 2048 game
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */
public class BonusCell extends Cell {
	private int count = 0;
	final char column = '@';
	
	public BonusCell () {
		value = 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if (count != 0) 
			return true;
		else 
			return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean merge(Cell c) {
		if (count == 0 && !c.isEmpty()) {
			count ++;
			c.bonusScore += c.value;
			value = c.value * 2;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible() {
		if (count != 0)
			return false;
		return true;
	}
	
	@Override
	public void increaseShift(int shift) {
		count ++;
		super.increaseShift(shift);
	}
}
