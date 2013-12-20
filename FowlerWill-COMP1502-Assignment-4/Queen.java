/**
 * Queen class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */
public class Queen extends ChessPiece
{

    /**
     * Constructor for class
     * @params column, row, player, board
     */
    public Queen(int aCol, int aRow, int aPlayer, ChessBoard aBoard)
    {
        super(aCol, aRow, aPlayer, aBoard);
    }
    
    /**
     * validMove tests whether the move is valid for the bishop
     * @params x, y (intended destination of the piece)
     */
    public boolean validMove(int x, int y)
    {
        if( Math.abs(col - x) == Math.abs(row - y) )
            return true;
        else if( col == x  || row == y )
            return true;
        else         
            return false;
    }
    
    /**
     * toString - it produces a capital Q for white and lowercase q for black
     */
    public String toString()
    {
        if(player == 0)
            return "Q";
        else
            return "q";
    }
}
    