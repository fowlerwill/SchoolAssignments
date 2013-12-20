/**
 * Main class for terminal chess game
 * @author Will Fowler
 * @date 2013 04 10
 */
public class main
{

    /**
     * Main method
     */
    public static void main(String args[])
    {
        //alright, here's what I need to do.
        //First, present the user with the introduction
        System.out.println( 
                            "********** Welcome to the Super Duper Chess Game **********\n" + 
                            "*                                                         *\n"   +
                            "* Upper case pieces are White's and lower case            *\n"   +
                            "* pieces are Black's.                                     *\n"   +
                            "*                                                         *\n"   +
                            "* To specify a move:                                      *\n"   +
                            "* enter row and column for piece to move                  *\n"   +
                            "* then enter row and column of square to move to          *\n"   +
                            "*                                                         *\n"   +
                            "* To quit before game is finished:                        *\n"   +
                            "* enter \"-1 -1\" for piece to move                       *\n"   +
                            "*                                                         *\n"   +
                            "***********************************************************\n"
                            );
                            
        //good, that's done. Now let's build a chessboard and print it.
        ChessBoard theGame = new ChessBoard();
        
        //great! now, let's build a menu that runs until the game is done
        Menu theMenu = new Menu( theGame );
        //A counter for how many turns there have been. the count is mod'd by 2 to determine
        //the current player's turn 0 = White 1 = Black
        int playerTurns = 0;
        
        while(!theMenu.quit() && !theGame.winCondition())
        {
            //MAKE SOME MAGIC HAPPEN
            System.out.println(theGame);
            //the menu prompts the user for an origin and destination space. rinse, repeat.
            if(playerTurns%2 == 0)
                System.out.println("White's Turn");
            else
                System.out.println("Black's Turn");
            theMenu.selectOrigin(playerTurns%2);
            theMenu.selectDestination(playerTurns%2);
            playerTurns ++;
        }
                                
        
    }

}
