/**
 * This class represents the absract class of a cell for our game
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 *
 */
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
	
	public void setBonusScore(int value) {
		this.bonusScore = value;
	}
	
	/**
	 * @return tells wether the cell is empty
	 */
	public abstract boolean isEmpty();
	
	/**
	 * merges two cells
	 * @param c the cell to be merged with
	 * @return wether these two cells could be merged
	 */
	public abstract boolean merge(Cell c);
	
	public abstract int getScore();
	
	/**
	 * @return tells wether the cell is visible
	 */
	public abstract boolean isVisible();
}
