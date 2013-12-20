/*
 * Board Class for Sudoku game. Assignment 2 in COMP 1502 
 * BY: Will Fowler
 * DATE: Feb 11
 * 
 * Board uses theBoard as the current game (it stores the chars in each position) has the following methods:
 *      - Board             - calls clear which will overwrite every position in theBoard with blanks
 *      - clear             - overwrites every position in theBoard with blanks
 *      - getBoard          - returns chars at specific coordinates of theBoard
 *      - readFile          - reads a string and a row, and converts each position of the string to the correspoding row and col.
 *      - verify            - checks for duplicate chars in each row, column, and box. returns true if good, false elsewise.
 *      - checkAgainst      - private method that flips a corresponding indicie in checkAgainstBoolean to true for the inputted char.
 *      - resetCheckAgainst - resets checkAgainstBoolean to all false valuse.
 *      - setVal            - accepts a row, col, and value, and sets the corresponding position in theBoard with the value
 *      - toString          - prints theBoard, with dividing lines and axis labels.
 */

public class Board
{
    public static final int     GRID_WIDTH              = 9;
    private char[][]            theBoard                = new char[GRID_WIDTH][GRID_WIDTH];
    private boolean[]           checkAgainstBoolean     = new boolean[GRID_WIDTH+1];
    
    /*
     * builds a board, by calling the clear method
     */
    public Board()
    {
        this.clear();
        return;
    }
    
    /*
     * sets all values in theBoared to a space char.
     */
    public void clear()
    {
        //create empty board
        for (int y=0; y<GRID_WIDTH; y++)
        {
            for( int x=0; x<GRID_WIDTH; x++ )
                theBoard[y][x] = ' ';
        }
    }
    
    /*
     * returns the char at the given row and col
     */
    public char getBoard(int row, int col)
    {
        return theBoard[row][col];
    }
    
    /*
     * reads a line of text, checks if the row given, or the column are beyond range of theBoard, and writes the values to their corresponding
     * positions.
     * TO DO: clear the board if an error is found.
     */
    public void readFile(int row, String inputLine)
    {
        
        if(row>=GRID_WIDTH)
        {
            System.out.println("File has too many rows, please re-format and try reading again");
        }
        else if(inputLine.length()>GRID_WIDTH)
        {
            System.out.println("File has too many columns in row: " + row + " please re-format and try reading again");
        }
        else
        {
            for(int col=0; col<Board.GRID_WIDTH; col++)
            {
                theBoard[row][col] = inputLine.charAt(col);
            }
            System.out.println("File row " + row + " Read Successfully");
        }
    }
    
    /*
     * verify checks the rows, columns and boxes of theBoard for duplicates.
     * if checks in rows and columns for missing spaces,
     * as it advances through positions it passes the current char in theBoard to checkAgainst, which will flip true for each char.
     * if checkAgainst is already true, the verifier knows that that position already exists, and will return the corresponding error.
     */
    public boolean verify()
    {
        boolean notMissingASpace = true, errors = false;
        
        this.resetCheckAgainst();
        //check row
        for(int row=0; row<GRID_WIDTH && notMissingASpace; row++)
        {
            for(int col=0; col<GRID_WIDTH && notMissingASpace; col++)
            {
                if( Character.getNumericValue( theBoard[row][col] ) < 1 )
                {
                    System.out.println("You're missing a space!");
                    notMissingASpace = false;
                }
                else
                {
                    if( checkAgainstBoolean[ Character.getNumericValue( theBoard[row][col] ) ] )
                    {
                        System.out.println("You've got at least one duplicate in row:" + row);
                        errors = true;
                    }
                    else
                        this.checkAgainst(row, col);
                }
            }
            this.resetCheckAgainst();
        }
        
        this.resetCheckAgainst();
        //check col
        for(int col=0; col<GRID_WIDTH && notMissingASpace; col++)
        {
            for(int row=0; row<GRID_WIDTH && notMissingASpace; row++)
            {
                if( Character.getNumericValue( theBoard[row][col] ) < 1 )
                {
                    System.out.println("You're missing a space!");
                    notMissingASpace = false;
                }
                else
                {
                    if( checkAgainstBoolean[ Character.getNumericValue( theBoard[row][col] ) ] )                        //check if char already exists
                    {
                        System.out.println("You've got at least one duplicate in col:" + col);
                        errors = true;
                    }
                    else
                        this.checkAgainst(row, col);
                }
            }
            this.resetCheckAgainst();
        }
        
        this.resetCheckAgainst();
        //check boxes
        int boxRowNumber = 0;
        int boxColNumber = 0;
        int boxNumber = 0;

        //run through the first box.
        for(int advanceBoxRow=0; advanceBoxRow<GRID_WIDTH/3; advanceBoxRow++)                                           //advance to the next row box
        {
            for(int advanceBoxCol=0; advanceBoxCol<GRID_WIDTH/3; advanceBoxCol++)                                       //advance to the next columnar box
            {
                boxNumber++;
                for(int boxRow=boxRowNumber%GRID_WIDTH; boxRow<boxRowNumber+3 && boxRow<9; boxRow++)                    //advance through rows.
                {
                    for(int boxCol=boxColNumber%GRID_WIDTH; boxCol<boxColNumber%GRID_WIDTH+3 && boxCol<9; boxCol++)     //advance through columns
                    {
                        if( checkAgainstBoolean[ Character.getNumericValue( theBoard[boxRow][boxCol] ) ] )               //check if char already exists
                        {
                            System.out.println("You've got at least one duplicate in box: " + boxNumber);
                            errors = true;
                        }
                        else
                            this.checkAgainst(boxRow, boxCol);
                    }
                }
                this.resetCheckAgainst();
                boxColNumber+=3;
            }
            boxRowNumber+=3;
        }
        
        if(!errors && notMissingASpace)
            return true;
        else
            return false;
    }
    
    /*
     * private method that will flip a corresponding indicie to true given char of board at position x, y.
     * ie: board[0][0] = '1' means checkAgainstBoolean[1] = true;
     *                    ^                            ^
     *                    this value      flips        this position
     */
    private void checkAgainst(int y, int x)
    {
        switch(theBoard[y][x])
                        {
                            case '1': checkAgainstBoolean[1] = true;
                            break;
                            case '2': checkAgainstBoolean[2] = true;
                            break;
                            case '3': checkAgainstBoolean[3] = true;
                            break;
                            case '4': checkAgainstBoolean[4] = true;
                            break;
                            case '5': checkAgainstBoolean[5] = true;
                            break;
                            case '6': checkAgainstBoolean[6] = true;
                            break;
                            case '7': checkAgainstBoolean[7] = true;
                            break;
                            case '8': checkAgainstBoolean[8] = true;
                            break;
                            case '9': checkAgainstBoolean[9] = true;
                            break;
                            default: checkAgainstBoolean[x] = false;
                        }
    }
    
    /*
     * resets the checkAgainstBoolean to all false values, so that the next condition can be checked
     */
    private void resetCheckAgainst()
    {
        //initialize checkAgainstBoolean to false
        for(int i=0; i<GRID_WIDTH+1; i++)
            checkAgainstBoolean[i] = false;
    }
    
    /*
     * sets a value at given row and col of theBoard
     */
    public void setVal(int row, int col, char val)
    {
        theBoard[row][col] = val;
    }
    
    /*
     * returns a string that has the board embedded in a nice graphic to seperate the values and aid the user in setting values by 
     * providing axis labels.
     */
    public String toString()
    {
        String boardReturnString = "";
        for(int rows=0; rows <=GRID_WIDTH*2; rows++)                            
        {
            for( int cols=0; cols<=GRID_WIDTH*2; cols++)                        //Start a loop through all cols and rows * 2 to account for spaces between 
            {                                                                   //board spaces
                if( cols == 0 )                                                 //*If we're in the far left column
                    if( rows%2>0 )                                              //and in an odd row
                        boardReturnString += rows/2 + " ";                      //print the row number
                    else                                                        //otherwise print a space
                        boardReturnString += "  ";                              //
                else if( rows == 0 )                                            //*If we're in the top row
                    if( cols%2>0 )                                              //and in an odd column
                        boardReturnString += cols/2;                            //print the column number
                    else                                                        //otherwise print a space
                        boardReturnString += "   ";                               //
                else if( cols%2 == 0 && cols>0 && rows != GRID_WIDTH*2)         //*If column is even and not the first
                    if( cols == GRID_WIDTH*2)                                   //do nothing at the last column
                        {}//do nothing                                          //
                    else if( cols%2 == 0 && rows%2 == 0 )                       //print + if at an intersection
                        boardReturnString += "-+-";                             //
                    else                                                        //
                        boardReturnString += " | ";                               //otherwise print seperator
                else if( rows%2 == 0 && cols>0 )                                //*if row is even and not the first
                    if( rows == GRID_WIDTH*2)                                   //do nothing at the last column
                        {}//do nothing                                          //
                    else                                                        //
                        boardReturnString += "-";                               //print seperator
                else if( cols%2 > 0 || rows%2 > 0  && rows<9 && cols<9 )        //
                    boardReturnString += theBoard[rows/2][cols/2];              //else print the actual game!
                    
            }
            boardReturnString += "\n";
        }
        return boardReturnString;
    }
}
