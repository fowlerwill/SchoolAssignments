import java.util.Random;

/**
 * 
 * Die Class by
 * @author Will Fowler
 */
public class Die {
	
	public int currentValue;
	private Random rand = new Random();

	/**
	 * Constructs a die by rolling it.
	 */
	public Die() {
		this.roll();
	}
	
	/**
	 * Returns value of die
	 * @return int - value of die
	 */
	public int getValue() {
		return currentValue;
	}
	
	/**
	 * Rolls the die
	 * @return int - new current value;
	 */
	public int roll() {
		currentValue = rand.nextInt(6) + 1;
		return currentValue;
	}
	
	/**
	 * To String Visualization of die
	 */
	public String toString() {
		String returnString = null;
		switch (currentValue) {
		case 1:
			returnString = 		" ----- \n"
							+ 	"|     |\n"
							+	"|  @  |\n"
							+ 	"|     |\n"
							+ 	" ----- \n";
			break;
		case 2:
			returnString = 		" ----- \n"
							+ 	"| @   |\n"
							+	"|     |\n"
							+ 	"|   @ |\n"
							+ 	" ----- \n";
			break;
		case 3:
			returnString = 		" ----- \n"
							+ 	"| @   |\n"
							+	"|  @  |\n"
							+ 	"|   @ |\n"
							+ 	" ----- \n";
			break;
		case 4:
			returnString = 		" ----- \n"
							+ 	"| @ @ |\n"
							+	"|     |\n"
							+ 	"| @ @ |\n"
							+ 	" ----- \n";
			break;
		case 5:
			returnString = 		" ----- \n"
							+ 	"| @ @ |\n"
							+	"|  @  |\n"
							+ 	"| @ @ |\n"
							+ 	" ----- \n";
			break;
		case 6:
			returnString = 		" ----- \n"
							+ 	"| @ @ |\n"
							+	"| @ @ |\n"
							+ 	"| @ @ |\n"
							+ 	" ----- \n";
			break;
		}
		
		return returnString;
	}

}
