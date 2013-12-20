/**
 * Menu Class for COMP 1502 Assignment 3 part 2
 * @author Will Fowler
 * @date 26 Mar 2013
 */

import java.util.Scanner;
import java.util.Random;

public class Menu
{
    private Deck theDeck;
    private Scanner keyboard = new Scanner(System.in);
    private Random rand = new Random();
    private boolean isDone = false;
    private double betAmount = 0.0;
    
    /**
     * Constructor for the Menu. 
     * Accepts the Deck object
     * 
     */
    public Menu(Deck aDeck)
    {
        theDeck = aDeck;
    }
    
    /**
     * menuQuery accepts a char menuchoice from the menu and enacts the appropriate actions:
     * 1: Play Punto Banko
     * 2: Play Hi Lo
     * Q: Quit
     */
    public void menuQuery(char menuChoice, Player aPlayer)
    {
        if(menuChoice == '1') //Playing Punto Banko
        {
            char puntoOutcomeBet;
            System.out.println("Welcome to Punto Banko, who would you like to bet on?\n" +
                                    "\tP: Player Wins – This bet pays out if the Player (Punto) wins.\n" +
                                    "\tB: Banker Wins – This bet pays out if the Banker (Banco) wins.\n" +
                                    "\tT: Tie Game – This bet pays out if there is a tie (Egalite).");
            do
            {
                System.out.println("Please make a choice");                
                puntoOutcomeBet = keyboard.next().toUpperCase().charAt(0);
            }
            while(puntoOutcomeBet != 'P' && puntoOutcomeBet != 'B' && puntoOutcomeBet != 'T');
            
            betAmount = aPlayer.makeBet();
            
            puntoBancoGame(puntoOutcomeBet, aPlayer, betAmount);
        }
        else if(menuChoice == '2') //PLAY HI LO
        {
            System.out.println("Welcome to Hi Lo Same Guess Game\n");
            betAmount = aPlayer.makeBet();
            hiLoGame(betAmount, aPlayer);
        }
        else if(menuChoice == 'N') //New Deck
            theDeck.newDeck();           
        else if(menuChoice == 'S') //Shuffle Deck
            theDeck.shuffle();
        else if(menuChoice == 'T') //Show Top Card
        {
            Card topCard = theDeck.cardValue(0);
            System.out.println(topCard.toString());
        }
        else if(menuChoice == 'C') //Cut the Deck
        {
            assert theDeck.size() > 0;
            int location = rand.nextInt(theDeck.size());
            Card cutCard = theDeck.cut(location);
            System.out.println(cutCard.toString());
        }
        else if(menuChoice == 'D') //Deal 2 hands of variable size
        {
            System.out.println("How many cards would you like dealt? (0 - " + theDeck.size()/2 + ")");
            int numCards = Integer.parseInt(keyboard.next());
            while(numCards < 0 || numCards > theDeck.size()/2)
            {
                System.out.println("Sorry, that's an invalid value, please try again.");
                numCards = Integer.parseInt(keyboard.next());
            }
            System.out.println(theDeck.deal2Hands(numCards));
        }
        else if(menuChoice == 'Q') //Quit
            isDone = true;
        else if(menuChoice == 'V') //View the Deck
            System.out.println(theDeck.viewDeck());
        else                        //improper input
            System.out.println("Sorry, your input didn't work, please try again");
    }
    
    /**
     * haveTheyQuit()  method returns the isDone boolean, which is flipped by
     * 'Q' selection in menuQuery by the user.
     */
    public boolean haveTheyQuit()
    {
        return isDone;
    }
    
    /**
     * punto banko menu method sets up a game of punto banko to be played
     * bet is a char:
     *  P: Player Wins – This bet pays out if the Player (Punto) wins.
        B: Banker Wins – This bet pays out if the Banker (Banco) wins.
        T: Tie Game – This bet pays out if there is a tie (Egalite).
        
       aPlayer is the player doing the betting,
       aBetAmount is the amount bet.
     */
    public void puntoBancoGame(char bet, Player aPlayer, double aBetAmount)
    {
        PuntoBanco aPuntoGame = new PuntoBanco(bet, aPlayer, aBetAmount);    
    }
    
    /**
     * HiLo Game builder
     */
    public void hiLoGame(double bet, Player aPlayer)
    {
        HiLo aHiLoGame = new HiLo(bet, aPlayer);
    }

    /**
     * toString Method. prints the menu.
     */
    public String toString()
    {
        return "Casino Menu, Please choose an option:" +
                    "\n\t 1: Play Punto Banko" +
                    "\n\t 2: Play Hi Lo" +
                    "\n\t Q: Quit";
    }
}
