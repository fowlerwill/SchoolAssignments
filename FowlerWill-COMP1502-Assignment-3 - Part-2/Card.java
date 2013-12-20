/**
 * Card Class - It's a class for cards. It holds a number and a suit.
 * 
 * @author will Fowler 
 * @date 2013-03-05
 */
public class Card
{
    // instance variables
    private int theValue;
    private char theSuit;

    /**
     * Card constructor, 
     * takes aValue 1-13 and aSuit 'D', 'H', 'S' or 'C'
     * if erronious values are entered in either field, the card will return with either 
     * theSuit as '0'
     * or 
     * theValue as 0 
     */
    public Card(int aValue, char aSuit)
    {
        if( aValue < 1 || aValue > 13 )
            theValue = 0;
        else
            theValue = aValue;
            
        if(aSuit != 'D' &&
           aSuit != 'H' &&
           aSuit != 'S' &&
           aSuit != 'C')
            theSuit = '0';
        else            
            theSuit = aSuit;

    }
    /**
     * Value returner, returns the value of the the card object as an int. 
     */

    public int value()
    {
        return theValue;
    }
    
    /**
     * Overloaded version of value() This method will return 
     * 'A' for theValue = 1
     * 'J' for theValue = 11
     * 'Q' for theValue = 12
     * 'K' for theValue = 13
     * when given the input 'P'
     */
    public String value(char Pretty)
    {
        if(Pretty == 'P')
            if(theValue == 1)
                return "A";
            else if(theValue == 11)
                return "J";
            else if(theValue == 12)
                return "Q";
            else if(theValue == 13)
                return "K";
            else
            return String.valueOf(theValue);
        else
            return "0";
    }
    
    /**
     * suit Returns the suit of the card.
     */
    public char suit()
    {
        return theSuit;
    }
    
    /**
     * toString returns a string of the card in the format:
     * theValue of theSuit
     */
    public String toString()
    {
        String returnString = "";
        returnString += this.value('P');
        returnString += " of ";
        if(theSuit == 'D')
            returnString += "Diamonds";
        else if(theSuit == 'H')
            returnString += "Hearts";
        else if(theSuit == 'S')
            returnString += "Spades";
        else if(theSuit == 'C')
            returnString += "Clubs";
        else
            returnString += "Errors";
            
        return returnString;
    }
        
}
