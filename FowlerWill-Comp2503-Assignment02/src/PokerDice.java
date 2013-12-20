import java.util.ArrayList;


/**
 * 
 * Poker Dice Game By 
 * @author Will Fowler
 * Roll 3 dice and compare your scores 
 */
public class PokerDice {
	public ConsoleCom con;
	public Menu mainMenu;
	public Menu gameMenu;
	public Player[] player = new Player[2];
	
	/**
	 * Default Constructor
	 */
	public PokerDice() {
		
		this.con = new ConsoleCom();
		this.mainMenu = new Menu(con);
		this.gameMenu = new Menu(con);
		initGameMenu();
		initMainMenu();
		
	}
	
	/**
	 * initialize game with Players
	 */
	private void initGame() {
		String p1Name = con.getInput_String("Enter Player 1's name: ");
    	String p2Name = con.getInput_String("Enter Player 2's name: ");
    	player[0] = new Player(p1Name);
    	player[1] = new Player(p2Name);
	}
	
	/**
	 * The Main game loop.
	 */
	private void runGame() {
		
		int turn = 0;
		//herein is the turn loop, in each turn the player should be shown their dice and prompted
		//by a menu
		while(turn < 6) {
			System.out.println("\n-------------------\n" + player[turn%2].name+"'s turn ("+((turn/2)+1)+"/3)\n"+player[turn%2]);
			player[turn%2].rollDice(runGameMenu());
			turn++;
		}	
		
		System.out.println(player[0].name + "\n" + player[0] + " -====== VS ======- \n" + player[1].name + "\n" + player[1]);
		
		if(player[0].rankHand() > player[1].rankHand())
			System.out.println(player[0].name + " wins!");
		else if(player[0].rankHand() < player[1].rankHand())
			System.out.println(player[1].name + " wins!");
		else
			System.out.println("Tie Game!");
	}
	
	/**
	 * Runs the menu for the players turn
	 * @return ArrayList<Integer> - returns an ArrayList<Integer> of the dice that the user would
	 * like to roll
	 */
	private ArrayList<Integer> runGameMenu() {
		boolean end = false;
		ArrayList<Integer> diceToRoll = new ArrayList<Integer>();

        while(!end) {
            // displays the users choice and returns 
            // the selected option as an object
        	if(!diceToRoll.isEmpty()) {
        		System.out.print("Dice to be rolled: ");
        		for(int n : diceToRoll)
        		System.out.print(n+1 + " ");
        		System.out.println();
        	}
            MenuOption selected = gameMenu.getUserChoice(); 

            //check the users choice
            if(selected.isAMatch("E"))
                end = true;
            else if(selected.isAMatch("1") ) {        	
            	if(!diceToRoll.contains(0))
            		diceToRoll.add(0);
            }
            else if(selected.isAMatch("2") ) {
            	if(!diceToRoll.contains(1))
            		diceToRoll.add(1);
            }
            else if(selected.isAMatch("3") ) {      
            	if(!diceToRoll.contains(2))
            		diceToRoll.add(2);
            }
            else if(selected.isAMatch("A") ) {      
            	if(!diceToRoll.contains(0))
            		diceToRoll.add(0);
            	if(!diceToRoll.contains(1))
            		diceToRoll.add(1);
            	if(!diceToRoll.contains(2))
            		diceToRoll.add(2);
            	end = true;
            }
            else if(selected.isAMatch("R") ) {
            	diceToRoll.clear();
            }
        }
        return diceToRoll;
	}
	
	/**
	 * Initialize game menu with options.
	 */
	private void initGameMenu() {
		gameMenu.addMenuOption(new MenuOption("1", "Roll Die # 1"));
		gameMenu.addMenuOption(new MenuOption("2", "Roll Die # 2"));
		gameMenu.addMenuOption(new MenuOption("3", "Roll Die # 3"));
		gameMenu.addMenuOption(new MenuOption("A", "Roll all dice"));
		gameMenu.addMenuOption(new MenuOption("R", "Reset dice to roll"));
		gameMenu.addMenuOption(new MenuOption("E", "End Turn"));
	}
	
	/**
	 * initialize main menu with options
	 */
	private void initMainMenu() {
		mainMenu.addMenuOption( new MenuOption("N", "New Game") );
		mainMenu.addMenuOption( new MenuOption("Q", "Quit") );
	}
	
	/**
	 * Runs the Main Menu and calls appropriate methods from user selection
	 */
	public void runMainMenu() {
		
		boolean end = false;

        while(!end) {
            // displays the users choice and returns 
            // the selected option as an object
            MenuOption selected = mainMenu.getUserChoice(); 

            //check the users choice
            if(selected.isAMatch("Q"))
                end = true;
            else if(selected.isAMatch("N") ) {        	
            	initGame();
            	runGame();
            }
        }
	}
	
	/**
	 * 
	 * Main Method to run the program
	 * @param args
	 */
	public static void main(String[] args) {
		PokerDice game = new PokerDice();
		System.out.println("Welcome to Poker Dice!\n");
		game.runMainMenu();
		
	}

}
