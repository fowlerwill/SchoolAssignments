/**
 * King class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */
public class King extends ChessPiece
{

    /**
     * Constructor for class
     * @params column, row, player, board
     */
    public King(int aCol, int aRow, int aPlayer, ChessBoard aBoard)
    {
        super(aCol, aRow, aPlayer, aBoard);
    }
    
    /**
     * validMove tests whether the move is valid for the pawn
     * @params x, y (intended destination of the piece)
     */
    public boolean validMove(int x, int y)
    {
        if( Math.abs(row - y) > 1 ||
            Math.abs(col - x) > 1 )
            return false;
        else         
            return true;
    }
    
    /**
     * toString - it produces a capital K for white and lowercase k for black
     */
    public String toString()
    {
        if(player == 0)
            return "K";
        else
            return "k";
    }
}
    