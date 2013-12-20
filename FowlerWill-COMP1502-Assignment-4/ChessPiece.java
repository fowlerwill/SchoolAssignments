
/**
 * Abstract class ChessPiece
 * 
 * @author Will Fowler
 * @version 02 April 2013
 */
public abstract class ChessPiece
{
    // instance variables - replace the example below with your own
    protected int         row;
    protected int         col;
    protected int         player;
    protected ChessBoard  gameBoard;

    /**
     * Default constructor sets position of object.
     */
    public ChessPiece(int aCol, int aRow, int aPlayer, ChessBoard aBoard)
    {
        row = aRow;
        col = aCol;
        player = aPlayer;
        gameBoard = aBoard;
    }
    
    /**
     * getters and setters
     */
    public int getPlayer()  { return player; }
    public int getRow()     { return row; }
    public int getCol()     { return col; }
    public String getPos()
    {
        return "(" + this.getCol() + "," + this.getRow() + ")";
    }
    
    public void setPlayer(int aPlayer)
    {
        player = aPlayer;
    }
    public void setRow(int aRow)
    {
        row = aRow;
    }
    public void setCol(int aCol)
    {
        col = aCol;
    }
    
    /**
     * validMove 
     * filled in in subclasses returns true if move is valid
     */
    public boolean validMove(int destinationX, int destinationY)
    {
        return true;
    }
    

    /**
     * updatePieceLocation 
     * 
     */
    public void updatePieceLocation()
    {
        gameBoard.setPieceAtLocation(col, row, this);
    }
    
    /**
     * Movers Move simply a single space in each specified direction
     */
    public void moveUp(){   row -= 1;}
    public void moveDown(){ row += 1;}
    public void moveLeft(){ col -= 1;}
    public void moveRight(){col += 1;}
}
