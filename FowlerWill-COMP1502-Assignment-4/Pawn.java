/**
 * Pawn class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */
public class Pawn extends ChessPiece
{

    /**
     * Constructor for class
     * @params column, row, player, board
     */
    public Pawn(int aCol, int aRow, int aPlayer, ChessBoard aBoard)
    {
        super(aCol, aRow, aPlayer, aBoard);
    }
    
    /**
     * validMove tests whether the move is valid for the pawn
     * @params x, y (intended destination of the piece)
     */
    public boolean validMove(int x, int y)
    {
        boolean returnB = true;
        
        if( Math.abs(col - x) > 1 || Math.abs(row - y) > 2 )
        {
            returnB = false;
        }
        else
        {
            
            //if the space is empty
            if( gameBoard.selectPiece(x,y) == null )
            {
                //the move must be within the same column
                if( col != x )
                    returnB = false;
                else 
                {
                    if( (row == 1 || row == 6) && Math.abs(row - y) == 2 )
                        returnB = true;
                    else if( Math.abs(row - y) > 1 )
                        returnB = false;
                    else if( this.toString() == "P" && y > row )
                        returnB = false;
                    else if( this.toString() == "p" && row > y )
                        returnB = false;
                        
                }
            }
            else if( gameBoard.selectPiece(x,y).getPlayer() != player )
            {
                if( col == x )
                    returnB = false;
                else if( row == y )
                    returnB = false;
                else if( this.toString() == "P" && y > row )
                    returnB = false;
                else if( this.toString() == "p" && row > y )
                    returnB = false;
                else
                    returnB = true;
                
            }
        }
        return returnB;
    }
    
    /**
     * toString - it produces a capital P for white and lowercase p for black
     */
    public String toString()
    {
        if(player == 0)
            return "P";
        else
            return "p";
    }
}
