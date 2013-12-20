/**
 * Knight class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */
public class Knight extends ChessPiece
{
    /**
     * Constructor for class
     * @params column, row, player, board
     */
    public Knight(int aCol, int aRow, int aPlayer, ChessBoard aBoard)
    {
        super(aCol, aRow, aPlayer, aBoard);
    }
    
    /**
     * validMove tests whether the move is valid for the Knight
     * @params x, y (intended destination of the piece)
     */
    public boolean validMove(int x, int y)
    {
        if( Math.abs(row - y) == 2  && Math.abs(col - x) == 1 )
            return true;
        else if( Math.abs(col - y) == 2  && Math.abs(row - x) == 1 )
            return true;
        else         
            return false;
    }
    
    /**
     * toString - it produces a capital K for white and lowercase k for black
     */
    public String toString()
    {
        if(player == 0)
            return "N";
        else
            return "n";
    }
}
    