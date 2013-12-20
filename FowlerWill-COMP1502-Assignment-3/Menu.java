import java.util.Scanner;
import java.util.Random;
/**
 * Menu Class for COMP 1502 Assignment 3 part 1
 * Requires a Deck Object for construction
 * Has the following options
 *  -New Deck
 *  -Shuffle Deck
 *  -Top Card
 *  -Cut The Deck
 *  -Deal Hands
 *  -Quit
 * 
 * @author Will Fowler
 * @version March 10, 2013
 */
public class Menu
{
    private Deck theDeck;
    private Scanner keyboard = new Scanner(System.in);
    private Random rand = new Random();
    private boolean isDone = false;
    
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
     * N: New Deck
       S: Shuffle Deck
       T: Top Card
       C: Cut The Deck
       D: Deal Hands
       Q: Quit
     */
    public void menuQuery(char menuChoice)
    {
        if(menuChoice == 'N')
            theDeck.newDeck();           
        else if(menuChoice == 'S')
            theDeck.shuffle();
        else if(menuChoice == 'T')
        {
            Card topCard = theDeck.cardValue(0);
            System.out.println(topCard.toString());
        }
        else if(menuChoice == 'C')
        {
            assert theDeck.size() > 0;
            int location = rand.nextInt(theDeck.size());
            Card cutCard = theDeck.cut(location);
            System.out.println(cutCard.toString());
        }
        else if(menuChoice == 'D')
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
        else if(menuChoice == 'Q')
            isDone = true;
        else if(menuChoice == 'V')
            System.out.println(theDeck.viewDeck());
        else
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
     * toString Method. prints the menu.
     */
    public String toString()
    {
        return "Deck Menu, Please choose an option:" +
                    "\n\t N: New Deck" +
                    "\n\t S: Shuffle Deck" +
                    "\n\t T: Top Card" +
                    "\n\t C: Cut The Deck" +
                    "\n\t D: Deal Hands" +
                    "\n\t V: View Deck" +
                    "\n\t Q: Quit";
    }
}
