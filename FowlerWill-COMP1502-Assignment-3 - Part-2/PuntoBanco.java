/**
 * Punto Banco Game
 * @author: Will Fowler
 * @date: 26 Mar 2013
 */
import java.util.ArrayList;
import java.util.Collections;
public class PuntoBanco
{
    char winner;
    Deck aDeck;
    
    /**
     * Default constructor takes the player, who they bet on and how much.
     */
    public PuntoBanco(char gameType, Player aPlayer, double aBet)
    {
        if(gameType == 'P')
            winner = 'P';
        else if(gameType == 'B')
            winner = 'B';
        else if(gameType == 'T')
            winner = 'T';
        else
            System.out.println("There was an error with your input, try again");
        
        aDeck = new Deck();
        if(game(aDeck) == winner )
        {
            if( winner == 'T' )
                aBet = aBet * 8;
            else if( winner == 'B' )
                aBet = aBet * 0.95;
            else
                {}//do nothing                
            aPlayer.setWallet(aPlayer.getWallet() + aBet);
            System.out.println("You won: $" + aBet + " you now have $" + aPlayer.getWallet() + " in your account");
        }
        else
        {
            aPlayer.setWallet(aPlayer.getWallet() - aBet);
            System.out.println("You lost, try again. you now have $" + aPlayer.getWallet() + " in your account");
        }
        
        aPlayer.store();
        
    }
    
    /**
     * PuntoBanco.game is the actual game mechanics for the card game, descisions listed with inline comments.
     * returns a char of the winner ('P' = player, 'B' = banker, 'T' = tie)
     */
    public char game(Deck aDeck)
    {
        
        aDeck.shuffle();
        //build an array to store the player and banker's cards
        ArrayList <Card> playerHand = new ArrayList <Card>();
        ArrayList <Card> bankerHand = new ArrayList <Card>();
        
        //deal a card to player and banker
        playerHand.add(aDeck.dealACard());
        bankerHand.add(aDeck.dealACard());
        playerHand.add(aDeck.dealACard());
        bankerHand.add(aDeck.dealACard());
        
        //print hands
        System.out.println("Player's Hand: \n" + printHand(playerHand) +
                            "\nBanker's Hand: \n" + printHand(bankerHand) );
                            
        //Start fun Punto Rules:
        
        //If the Player or Banker has a total of 8 or 9, no more cards are dealt

        if( handValue(playerHand) == 8 || handValue(playerHand) == 9 ||
            handValue(bankerHand) == 8 || handValue(bankerHand) == 9 )                                        
        {
            //Do Nothing, No More Cards dealt - game is a natural
        } 
        else 
        {
            
            //If the Player has a total of 0-5, the player gets a third card face up.
            
            if( handValue(playerHand) <= 5 )
            {
                playerHand.add(aDeck.dealACard());
                System.out.println( "Player Hand 0-5, player gets another card\n" + 
                                    "Player's Hand: \n" + printHand(playerHand) );
            } 
            else
            {
                System.out.println( "Player Hand 6 or 7, player stands");
            }
            
            if( playerHand.size() == 2 )
            {
                
                //If the Player did not draw a card, the Banker draws if he has 0-5, and stands if he has 6-7. 
                
                if( handValue(bankerHand) < 6 )
                {
                    bankerHand.add(aDeck.dealACard());
                    System.out.println( "Banker Hand 0-5, banker gets another card\n" + 
                                        "Banker's Hand: \n" + printHand(bankerHand) );
                } else if( handValue(bankerHand) == 6 || handValue(bankerHand) == 7 )
                {
                    System.out.println("Banker has 6 or 7, banker stands");
                }
            } 
            else
            {
                
                /*
                 * If the Player Drew a card and:
                 *  If the Player drew a 2 or 3, 
                 *      the Banker draws if he has 0-4, 
                 *      and stands if he has 5-7. 
                 *  If the Player drew a 4 or 5, 
                 *      the Banker draws if he has 0-5, 
                 *      and stands if he has 6-7. 
                 *  If the Player drew a 6 or 7, 
                 *      the Banker draws if he has 0-6, 
                 *      and stands if he has 7. 
                 *  If the Player drew an 8, 
                 *      the Banker draws if he has 0-2, 
                 *      and stands if he has 3-7. 
                 *  If the Player drew an Ace, 9, 10, or face card (Jack, Queen or King), 
                 *      the Banker draws if he has 0-3, 
                 *      and stands if he has 4-7.
                 */
                
                if( playerHand.get(2).value() == 2 || playerHand.get(2).value() == 3 )
                {
                    if( handValue(bankerHand) < 5 )
                    {
                        bankerHand.add(aDeck.dealACard());
                        System.out.println( "Player drew a 2 or 3, and banker has 0-4, banker gets another card\n" + 
                                            "Banker's Hand: \n" + printHand(bankerHand) );
                    } else
                    {
                        System.out.println("Banker has 5 to 7, banker stands");
                    }                    
                } else if ( playerHand.get(2).value() == 4 || playerHand.get(2).value() == 5 )
                {
                    if( handValue(bankerHand) < 6 )
                    {
                        bankerHand.add(aDeck.dealACard());
                        System.out.println( "Player drew a 4 or 5, and banker has 0-5, banker gets another card\n" + 
                                            "Banker's Hand: \n" + printHand(bankerHand) );
                    } else
                    {
                        System.out.println("Banker has 6 or 7, banker stands");
                    }
                } else if ( playerHand.get(2).value() == 6 || playerHand.get(2).value() == 7 )
                {
                    if( handValue(bankerHand) < 7 )
                    {
                        bankerHand.add(aDeck.dealACard());
                        System.out.println( "Player drew a 6 or 7, and banker has 0-6, banker gets another card\n" + 
                                            "Banker's Hand: \n" + printHand(bankerHand) );
                    } else
                    {
                        System.out.println("Banker has 7, banker stands");
                    }
                } else if ( playerHand.get(2).value() == 8 )
                {
                    if( handValue(bankerHand) < 3 )
                    {
                        bankerHand.add(aDeck.dealACard());
                        System.out.println( "Player drew an 8, and banker has 0-2, banker gets another card\n" + 
                                            "Banker's Hand: \n" + printHand(bankerHand) );
                    } else
                    {
                        System.out.println("Banker has 3-7, banker stands");
                    }
                } else if ( playerHand.get(2).value() == 1 || playerHand.get(2).value() >= 9 )
                {
                    if( handValue(bankerHand) < 4 )
                    {
                        bankerHand.add(aDeck.dealACard());
                        System.out.println( "Player drew an Ace, 9, 10, or face card and banker has 0-3, banker gets another card\n" + 
                                            "Banker's Hand: \n" + printHand(bankerHand) );
                    } else
                    {
                        System.out.println("Banker has 4-7, banker stands");
                    }
                }
                
                /*
                 * END of Banker drawing dependant on if the player drew a card
                 */
                
            }
        }
        
        /*
         * At this point, the result of the game is determined: 
         *      -the Player wins if his total beats the Banker, 
         *      -the Banker wins if his total beats the Player, 
         *      -or the result is a tie (Egalite) if the totals are the same.
         */
        
        if( handValue(playerHand) > handValue(bankerHand) )
        {
            System.out.println("Player Wins!");
            return 'P';
        } else if( handValue(playerHand) == handValue(bankerHand) ) 
        {
            System.out.println("Tie Game!");
            return 'T';
        } else
        {
            System.out.println("Banker Wins!");
            return 'B';
        }
        
    }
    
    /**
     * Hand Value adds the value of all cards in the hand passed to it, and returns the result mod 10
     */
    public int handValue(ArrayList <Card> hand)
    {
        int handValue = 0;
        for( int i=0; i<hand.size(); i++ )
        {
            if(hand.get(i).value() >= 10)
                handValue += 10;
            else
                handValue += hand.get(i).value();
        }
        return handValue%10;
    }
    
    /**
     * printHand returns a string of every card in the hand passed to it
     */
    public String printHand(ArrayList <Card> hand)
    {
        String returnString = "";
        for( int i=0; i<hand.size(); i++ )
        {
            returnString += "\t" + hand.get(i) + "\n";
        }
        returnString += "Hand Value: " + handValue(hand) + "\n";
        return returnString;
    }

}
