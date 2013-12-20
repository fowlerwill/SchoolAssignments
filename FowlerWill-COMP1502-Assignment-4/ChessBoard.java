/**
 * Chessboard class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */
public class ChessBoard
{
    private final int BOARD_SIZE = 8;
    private ChessPiece[][] gameBoard;
    
    /**
     * Constructor for Chessboard - puts the pieces int ehir place
     */
    public ChessBoard()
    {
        gameBoard = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        //set up board
        //Black
        gameBoard[3][0] = new King(3,0,1, this);
        gameBoard[4][0] = new Queen(4,0,1, this);
        gameBoard[1][0] = new Knight(1,0,1, this);
        gameBoard[6][0] = new Knight(6,0,1, this);
        gameBoard[0][0] = new Rook(0,0,1, this);
        gameBoard[7][0] = new Rook(7,0,1, this);
        gameBoard[2][0] = new Bishop(2,0,1, this);
        gameBoard[5][0] = new Bishop(5,0,1, this);
        for(int i=0; i<8; i++)
        {
            gameBoard[i][1] = new Pawn(i,1,1, this);
        }
        
        //White
        gameBoard[3][7] = new King(3,7,0, this);
        gameBoard[4][7] = new Queen(4,7,0, this);
        gameBoard[1][7] = new Knight(1,7,0, this);
        gameBoard[6][7] = new Knight(6,7,0, this);
        gameBoard[0][7] = new Rook(0,7,0, this);
        gameBoard[7][7] = new Rook(7,7,0, this);
        gameBoard[2][7] = new Bishop(2,7,0, this);
        gameBoard[5][7] = new Bishop(5,7,0, this);
        for(int i=0; i<8; i++)
        {
            gameBoard[i][6] = new Pawn(i,6,0, this);
        }
        
    }
    
    /**
     * checks for win condition, prints winner, returns boolean true if game over
     */
    public boolean winCondition()
    {
        int kingCount = 0;
        int kingCol = 0;
        int kingRow = 0;
        
        for(int row = 0; row < BOARD_SIZE; row++)
        {
            for(int col = 0; col < BOARD_SIZE; col++)
            {
                if(gameBoard[col][row] != null)
                {
                    if(gameBoard[col][row].toString() == "k" || gameBoard[col][row].toString() == "K")
                    {
                        kingCol = col;
                        kingRow = row;
                        kingCount++;
                    }
                }
            }
        }
        if (kingCount == 1)
        {
            if( gameBoard[kingCol][kingRow].toString() == "K" )
                System.out.println("White Wins!");
            else
                System.out.println("Black Wins!");
                
            return true;
        }
        else
            return false;
    }
    
    /**
     * selectPiece selects a piece off the board, returns null if empty
     * @params col row
     */
    public ChessPiece selectPiece(int col, int row)
    {
        if(gameBoard[col][row] == null)
            return null;
        else
            return gameBoard[col][row];
    }
   
    /**
     * setPieceAtLocation puts the passed piece at the passed location
     * @params col row Chesspiece
     */
    public void setPieceAtLocation(int col, int row, ChessPiece piece)
    {
        gameBoard[col][row] = piece;
    }
    
    /**
     * removePieceAtLocation removes a piece at the given coords
     * @params col row
     */
    public void removePieceAtLocation(int col, int row)
    {
        gameBoard[col][row] = null;
    }
    
    /**
     * toString prints the thing!
     */
    public String toString()
    {
        int row, col;
        String returnString = "      x    \n  01234567\n";
        
                
        for(row = 0; row < BOARD_SIZE; row++)
        {
            for(col = 0; col < BOARD_SIZE; col++)
            {
                if( col == 0 )
                {
                    if( row == 4 )
                        returnString += "y" + row;
                    else
                        returnString += " " + row;
                }
                
                if(gameBoard[col][row] == null)
                    returnString += " ";
                else
                    returnString += gameBoard[col][row].toString();
            }
            returnString += "\n";
        }
        
        return returnString;
    }
            
}
