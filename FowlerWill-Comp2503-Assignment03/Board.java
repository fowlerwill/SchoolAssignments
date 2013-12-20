import java.util.ArrayList;


/**
 * 
 * Board class for StackGame
 * 
 * @author wfowl594
 *
 */
public class Board {
	
	private Tile[] tileArray;

	/**
	 * 
	 */
	public Board(int n) {
		tileArray = new Tile[2*n+1];
		int nCopy = n;
		for(int i = 0; i < tileArray.length; i++) {
			if(nCopy > 0) {
				if( i < tileArray.length/2)
					tileArray[i] = new Tile('O');
				else
					tileArray[i] = new Tile('X');
				nCopy --;
			}
			else {
				tileArray[i] = new Tile(' ');
				nCopy = n;
			}
		}
	}
	
	/**
	 * 
	 */
	public Board(Board oldBoard) {
		this.tileArray = new Tile[oldBoard.tileArray.length];
		
		for(int i = 0; i < oldBoard.tileArray.length; i++) {
			this.tileArray[i] = new Tile(oldBoard.getTile(i));
		}
		
		
	}
	
	public boolean checkWin() {
		for(int i = 0; i < tileArray.length/2; i++) {
			if(tileArray[i].getDirection() > 0)
				return false;
		}
		
		if(tileArray[tileArray.length/2].getDirection() != 0)
			return false;
		else
			return true;
	}
	
	public boolean compareBoards(Board a, Board b) {
		if(a.tileArray.length != b.tileArray.length)
			return false;
		for(int i = 0; i < a.tileArray.length; i++) {
			if(a.getTile(i).getDirection() != b.getTile(i).getDirection())
				return false;
		}
		return true;
	}
	
	/**
	 * @param i
	 * @param aTile
	 */
	public void setTile(int i, Tile aTile) {
		tileArray[i] = aTile;
	}
	
	/**
	 * @param i
	 * @return
	 */
	public Tile getTile(int i) {
		return tileArray[i];
	}
	
	
	/**
	 * @param i
	 * @return
	 */
	public Board moveTile(int i) {
		//build a copy of the current board
		Board newBoard = new Board(this);
		//get the direction of the tile given (-1, 0, 1)
		int dir = this.getTile(i).getDirection();
		if(dir == 0)
			return this;
		//got rid of the spaces now, let's get the value: ('X', 'O')
		Tile adjacentTile = null;
		
		//is the move in bounds?
		if(!isMoveInBounds(i+dir))
			return this;
		else
			adjacentTile = this.getTile(i+dir);
		
		//is that move same team?
		if (adjacentTile.getTileValue() == this.getTile(i).getTileValue()) {
			return this;
		} else if (adjacentTile.getDirection() == 0) {
			//it's a space! let's do it!
			newBoard.setTile(i+dir, this.getTile(i));
			newBoard.setTile(i, this.getTile(i+dir));
			return newBoard;
		} else if(adjacentTile.getTileValue() != this.getTile(i).getTileValue()) {
			//its the opposite piece, can we hop it?
			int nextSpace = i+(2*dir);
			if(isMoveInBounds(nextSpace) && this.getTile(nextSpace).getDirection() == 0) {	
				newBoard.setTile(nextSpace, this.getTile(i));
				newBoard.setTile(i, this.getTile(nextSpace));
				return newBoard;
			} else {
				return this;
			}
		} else {
			//oh who knows how it'd end up here... but just in case
			return this;
		}
			
	}
	
	/**
	 * @param i
	 * @return
	 */
	private boolean isMoveInBounds(int i) {
		try {
			tileArray[i].getTileValue();
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * @return
	 */
	public ArrayList<Board> findAllPossibleMoves() {
		ArrayList<Board> possibleMoves = new ArrayList<Board>();
		
		for(int i=0; i<tileArray.length; i++) {
			Board currBoard = moveTile(i);
			if(!possibleMoves.contains(currBoard))
				possibleMoves.add(currBoard);
		}
		
		
		return possibleMoves;
	}
	
	public String toString() {
		String returnString = "";
		for(int i = 0; i < tileArray.length; i++) {
			returnString += tileArray[i].getTileValue();
		}
		return returnString;
	}

}
