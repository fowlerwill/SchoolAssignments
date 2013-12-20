/**
 * 
 * Tile class for StackGame
 * 
 * @author wfowl594
 *
 */
public class Tile {

	private char tileValue;
	private int direction;

	public Tile(char value) {
		
		value = Character.toUpperCase(value);
		
		switch (value) {
		case 'X':
			tileValue = value;
			direction = -1;
			break;
		case 'O':
			tileValue = value;
			direction = 1;
			break;
		default:
			tileValue = ' ';
			direction = 0;
			break;
		}
		
	}
	
	public Tile(Tile oldTile) {
		tileValue = oldTile.getTileValue();
		direction = oldTile.getDirection();
	}
	
	/**
	 * @return the tileValue
	 */
	public char getTileValue() {
		return tileValue;
	}

	/**
	 * @param tileValue the tileValue to set
	 */
	public void setTileValue(char tileValue) {
		this.tileValue = tileValue;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	

}
