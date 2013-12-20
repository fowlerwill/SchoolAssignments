/**
 * Rook class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */
public class Rook extends ChessPiece
{
    /**
     * Constructor for class
     * @params column, row, player, board
     */
    public Rook(int aCol, int aRow, int aPlayer, ChessBoard aBoard)
    {
        super(aCol, aRow, aPlayer, aBoard);
    }
    
    /**
     * validMove tests whether the move is valid for the rook
     * @params x, y (intended destination of the piece)
     */
    public boolean validMove(int x, int y)
    {
        if( col == x  || row == y )
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
            return "R";
        else
            return "r";
    }
}
    