import java.util.ArrayList;

/**
 * Player class for the PokerDice game - a player holds 3 dice and can rank their hand.
 * @author wfowl594
 *
 */
public class Player {

	public ArrayList<Die> dice = new ArrayList<Die>(3);
	public String name = null;
	
	/**
	 * Default constructor
	 */
	public Player(String aName) {
		this.dice.add(new Die());
		this.dice.add(new Die());
		this.dice.add(new Die());
		this.name = aName;
	}
	
	/**
	 * Rolls the specified dice
	 * @param diceToRoll - ArrayList<Integer> - arraylist of positions to roll.
	 */
	public void rollDice(ArrayList<Integer> diceToRoll) {
		for(int n : diceToRoll)
			dice.get(n).roll();
	}
	
	/**
	 * Ranks players hand, with a two digit int
	 * first digit: 1 = no pairs, 2 = 2 dice alike, 3 = 3 dice alike
	 * second digit: value of pair / 3 of a kind or highest card
	 * @return int - hand rank
	 */
	public int rankHand() {
		int 	die1 = dice.get(0).getValue(), 
				die2 = dice.get(1).getValue(),
				die3 = dice.get(2).getValue(),
				handRank = 0,
				pairValue = 0;
		//check for 3 of a kind
		if(die1 == die2 && die1 == die3)
			handRank += 30;
		//chk for pair
		else if(die1 == die2 || die1 == die3) {
			pairValue = die1;
			handRank += 20;
		}
		else if(die2 == die3) {
			pairValue = die2;
			handRank += 20;
		}
		//highcard
		else
			handRank += 10;
		
		//find the value of the 3 of a kind, pair or highest card.
		switch (handRank) {
			case 30:
				handRank += die1;
				break;
			case 20:
				handRank += pairValue;
				break;
			case 10:
				//find largest die
				int highDie;
				if(die1>die2)
					highDie = die1;
				else 
					highDie = die2;
				
				if(highDie < die3)
					highDie = die3;
				
				handRank += highDie;
				break;
			default:
				handRank = -1;
				break;
			}
		return handRank;
	}
	
	/**
	 * toString Method
	 */
	public String toString() {
		String returnString = "Die # 1: \n";
		returnString += dice.get(0);
		returnString += "Die # 2: \n" + dice.get(1);
		returnString += "Die # 3: \n" + dice.get(2);
		return returnString;
	}

}
