import java.util.Scanner;
import java.util.*;
import java.io.*;
/*
 * *Will Fowler 
 * *Feb 18 2013
 * *Menu class that has methods:
 * *    menuQuery   - accepts a selection from menu and enacts appropriate methods
 * *    clearGrid   - clears the board
 * *    readGrid    - reads a file to the board
 * *    dispGrid    - displays the current board
 * *    setGridVal  - sets a value in the board
 * *    verify      - checks for win condition/errors on board
 * *    writeGrid   - writes the board to a file
 * *    quit        - exits the program
 * *    toString    - prints the menu
 */

public class Menu
{
    Scanner keyboard = new Scanner(System.in);
    
    /*
     * Menu Query method, accepts a char and enacts the corresponding menu action.
     * Also accepts the board class for context of actions.
     */
    public void menuQuery(char menuEntry, Board theBoard)
    {
        if (menuEntry == 'C')
            this.clearGrid(theBoard);
        else if (menuEntry == 'R')
            this.readGrid(theBoard);
        else if (menuEntry == 'D')
            this.dispGrid(theBoard);
        else if (menuEntry == 'S')
            this.setGridVal(theBoard);
        else if (menuEntry == 'V')
            this.verify(theBoard);
        else if (menuEntry == 'W')
            this.writeGrid(theBoard);
        else if (menuEntry == 'Q')
            this.quit();
        else if (menuEntry == 'N')
            this.toString();
        else if (   menuEntry != 'C' ||
                    menuEntry != 'R' ||
                    menuEntry != 'D' ||
                    menuEntry != 'S' ||
                    menuEntry != 'V' ||
                    menuEntry != 'W' ||
                    menuEntry != 'Q' ||
                    menuEntry != 'R' ||
                    menuEntry != 'N')
        {
            System.out.println("Invalid Entry, please try again");
        }
    }
    
    
    /*
     * clearGrid method tells the Board class to clear itself
     */
    public void clearGrid(Board theBoard)
    {
        theBoard.clear();
        System.out.println("Board Cleared");
    }
    
    
    /*
     * readGrid method prompts the user for a file to open, and passes each line of the file to the 
     * readFile method of the Board
     */
    public void readGrid(Board theBoard)
    {
        //Prompt user for file to read, puts it in fileScanner
        System.out.println("File to open: ");
        String fileName = keyboard.next();        
        try
        {
            Scanner fileScanner = new Scanner(new File (fileName));
            
            for(int i=0; fileScanner.hasNext(); i++) 
                theBoard.readFile(i, fileScanner.nextLine());
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("invalid file");
        }
    }
    
    
    /*
     * prints the current board to the screen.
     */
    public void dispGrid(Board theBoard)
    {
        System.out.println(theBoard.toString());
    }
    
    
    /*
     * setGridVal prompts user for a row and column int, checks that they are within range, and prompts
     * for a value to enter as well, and checks that that value is within range as well. Once all values
     * are within range, it sets the value in the proper position by using the board's setVal method
     */
    public void setGridVal(Board theBoard)
    {
        System.out.println("Setting a value...\n\tenter row: ");
        int theRow = keyboard.nextInt();
        if(theRow > 8 || theRow < 0)
        {
            System.out.println("Invalid Row, Please enter a new number");
            theRow = keyboard.nextInt();
        }
        System.out.println("\tenter column: ");
        int theCol = keyboard.nextInt();
        if(theCol > 8 || theCol < 0)
        {
            System.out.println("Invalid Column, Please enter a new number");
            theCol = keyboard.nextInt();
        }
        System.out.println("\tenter new value (or 0 to clear): ");
        char theVal = keyboard.next().charAt(0);
        if( theVal == '0')
            theVal = ' ';
        else if(theVal > '9' || theVal < '0')
        {
            System.out.println("Invalid Value, Please enter a new number");
            theVal = keyboard.next().charAt(0);
        }
        theBoard.setVal(theRow, theCol, theVal); 
    }
    
    /*
     * verify enacts the board's verify method, which will return a boolean. If boolean returns true, the
     * board has passed the verification and congradulates the user.
     * if the board returns false, the user is encouraged to keep trying. After either condition, the user
     * should be re-prompted in the main method with the menu again
     */
    public void verify(Board theBoard)
    {
        if(theBoard.verify())
            System.out.println("Great Job! Your Board looks complete :)");
        else
            System.out.println("Your board has a few errors, keep trying!");
    }
     
    /*
     * Writes the current board to a file using the FileWriter and BufferedWriter Classes,
     * it advances through each position in the board and writes each character in turn. writes a 
     * newline at the end of each row.
     */
    public void writeGrid(Board theBoard)
    {
        String fileName = "";
        System.out.println("Please Enter the filename you'd like to use");
        fileName = keyboard.next();
        try
        {
              // Create file 
              FileWriter fstream = new FileWriter(fileName + ".txt");
              BufferedWriter out = new BufferedWriter(fstream);
              
              for(int row=0; row<Board.GRID_WIDTH; row++)
              {
                  for(int col=0; col<Board.GRID_WIDTH; col++)
                  {
                      out.write(theBoard.getBoard(row, col));
                  }
                  out.newLine();
              }
              
              out.close();
              System.out.println("File Written Successfully");
        }
        catch (Exception e)
        {
              System.err.println("Error writing file: " + e.getMessage());
        }
    }  
    
    
    /*
     * quit method reprompts the user in case q was entered by accident and then upon confirmation kills the
     * program
     */
    public void quit()
    {
        System.out.println("Are you sure you wish to quit? y/n");
        char input = keyboard.next().toUpperCase().charAt(0);
        if(input == 'Y')
            System.exit(0);
    }
    
    /*
     * prints the menu :-P
     */
    public String toString()
    {
        return "Available Options" + 
                            "\n\tC – Clear the grid" +
                            "\n\tR – Read a Sudoku grid from a file" +
                            "\n\tD – Display the current grid" +
                            "\n\tS – Set a value in the grid" +
                            "\n\tV – Verify the current grid" +
                            "\n\tW – Write the current grid to a file" +
                            "\n\tQ - Quit";
    }
    
}
