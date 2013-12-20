/**
 * Deck Class 
 * @author Will Fowler
 * @date 26 March 2013
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
public class Deck
{
    private ArrayList <Card> theDeck = new ArrayList <Card>();
    /**
     * Deck Method, resets by using newDeck()
     */
    public Deck()
    {
        this.newDeck();
    }
    
    /**
     * newDeck method
     * create the deck with 52 cards, 
     * ordered from ace to king of spades, hearts, clubs, and diamonds
     */
    public void newDeck()
    {
        //clear existing deck first
        theDeck.clear();
        //build new deck
        char suit;
        
        for(int s=1; s<5; s++)
        {
            if(s == 1)
                suit = 'S';
            else if(s == 2)
                suit = 'H';
            else if(s == 3)
                suit = 'C';
            else
                suit = 'D';
                
            for(int i=1; i<14; i++)
            {
                theDeck.add(new Card(i, suit));
            }
        }
    }
    
    /**
     * Shuffle Method, shuffles theDeck
     */
    public void shuffle()
    {
        Collections.shuffle(theDeck);
    }
    
    /**
     * Deal returns the inputted amount of cards
     */
    public Card dealACard()
    {
        Card tempCard = theDeck.get(0);
        theDeck.remove(0);
        return tempCard;        
    }
    
    /**
     * Deal2Hands deals 2 hands of an inputted size.
     */
    public String deal2Hands(int size)
    {
        String hand1 = "", hand2 = "", returnString = "";
        
        for(int i=0; i<size*2; i++)
        {
            if(i%2>0)
                hand1 += "[" + theDeck.get(i).toString() + "]\n";
            else
                hand2 += "[" + theDeck.get(i).toString() + "]\n";
        }
        
        //remove cards from the deck
        theDeck.subList(0, size*2).clear();
        
        return "hand 1:\n" + hand1 +"\nhand2:\n" + hand2;
        
    }
    
    /**
     * Cut method, cuts the deck at a specified point
     */
    public Card cut(int location)
    {
        Card cutCard = theDeck.get(location);
        ArrayList <Card> storage = new ArrayList <Card>();
        
        //copy all cards (including @location) to storage, add them to the back.
        for(int i=0; i<=location+1; i++)
        {
            storage.add(i, theDeck.get(i));
            theDeck.add(storage.get(i));
        }
        
        //delete copied cards from front of deck
        theDeck.subList(0, location+1).clear();

        return cutCard;
    }
    
    /**
     * Return Card at specified place in deck
     */
    public Card cardValue(int pos)
    {
        return theDeck.get(pos);
    }
    
    /**
     * size returns the number of cards left in the deck
     * 
     */
    public int size()
    {
        return theDeck.size();
    }
    
    /**
     * view deck returns a string of all cards in the deck.
     */
    public String viewDeck()
    {
        String returnString = "";
        for(int i=0; i<theDeck.size(); i++)
        {
            if(i%13 == 0)
                returnString += "\n";
            returnString += "[" + theDeck.get(i).toString() + "]";
        }
        return returnString;
    }
}
