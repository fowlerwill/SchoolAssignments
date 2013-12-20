package toDo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ToDo program class
 * 
 * @author Will Fowler
 *
 */
public class ToDo {

	private ConsoleCom com;
    private Menu mainMenu;
    private Scanner userInput = new Scanner(System.in);
    private ToDoList toDoList;

    /**
     * Constructor method - sets up fields
	 * @param com
	 * @param exampleMenu
	 */
	public ToDo() {
		this.com = new ConsoleCom();
		this.mainMenu = new Menu(com);
		initMenu();
	}
	
	/**
	 * initialize menu with options
	 */
	private void initMenu() {
		mainMenu.addMenuOption( new MenuOption("D", "Display List") );
		mainMenu.addMenuOption( new MenuOption("A", "Add Item") );
		mainMenu.addMenuOption( new MenuOption("R", "Remove Item") );
		mainMenu.addMenuOption( new MenuOption("U", "Update Item Priority") );
		mainMenu.addMenuOption( new MenuOption("Q", "Quit") );
	}
	
	/**
	 * Runs the Menu and calls appropriate methods with the todolist
	 */
	public void run() {
		
		boolean end = false;

        while(!end) {
            // displays the users choice and returns 
            // the selected option as an object
            MenuOption selected = mainMenu.getUserChoice(); 

            //check the users choice
            if(selected.isAMatch("Q"))
                end = true;
            else if(selected.isAMatch("D") ) {
                toDoList.display();
            }
            else if(selected.isAMatch("U") ) {
            	int i = findInBounds();
            	ToDoEntry theEntryInQuestion = toDoList.lookup(i-1);
            	char yesOrNo;
            	do {
            		yesOrNo = promptForMessage("Reprioritize \"" + toDoList.lookup(i-1) +"\" (y/n)?").charAt(0);
            	} while(yesOrNo != 'y' && yesOrNo != 'n');
            	
            	if(yesOrNo == 'y') {
            		int priority = 0;
            		do {
    	            	char charPriority = promptForMessage("enter priority (h/m/l):").charAt(0);
    	            	switch(charPriority) {
    	            	case 'h':
    	            		priority = 1;
    	            		break;
    	            	case 'm':
    	            		priority = 2;
    	            		break;
    	            	case 'l':
    	            		priority = 3;
    	            		break;
    	            	}
                	} while(priority == 0);
            		theEntryInQuestion.updatePriority(priority);
            	}
            }
            else if(selected.isAMatch("A") ) {
            	String desc = promptForMessage("describe new item:");
            	int priority = 0;
            	do {
	            	char charPriority = promptForMessage("enter priority (h/m/l):").charAt(0);
	            	switch(charPriority) {
	            	case 'h':
	            		priority = 1;
	            		break;
	            	case 'm':
	            		priority = 2;
	            		break;
	            	case 'l':
	            		priority = 3;
	            		break;
	            	}
            	} while(priority == 0);
                toDoList.add( new ToDoEntry(priority, desc) );
            }
            else if(selected.isAMatch("R") ) {
            	int i = findInBounds();
            	char yesOrNo;
            	do {
            		yesOrNo = promptForMessage("Remove \"" + toDoList.lookup(i-1) +"\" (y/n)?").charAt(0);
            	} while(yesOrNo != 'y' && yesOrNo != 'n');
            	
                toDoList.remove( i-1 );
            }
        }
	}
	
	/**
	 * Prompt the user for a todoentry index until they select a valid one
	 * and return it
	 * @return int i - valid index of todoentry in todolist
	 */
	public int findInBounds() {
		boolean inBounds = false;
    	
    	int i = 0;
    	do {
    		i = Integer.valueOf(promptForMessage("Which Index?"));
        	if(i < toDoList.theList.size() && i > 0)
        		inBounds = true;
        		
    	} while(!inBounds);
    	return i;
	}
	
	/**
	 * Accepts a message as an arguement, and returns the string that the
	 * user types in
	 * @param msg String the text that you would like to prompt the user with
	 * @return String the user types in (whole line)
	 */
	private String promptForMessage(String msg) {
		System.out.println(msg);
		return userInput.nextLine();
	}
    
	/**
	 * Main run method
	 * @param args
	 */
	public static void main(String[] args) {
		ToDo toDoProg = new ToDo();
		FileHandler file = null;
		Scanner mainInput = new Scanner(System.in);
		boolean promptAgain = false;
		
		do {
			String fileName = toDoProg.promptForMessage("Please Enter File Name: ");
			try {
				file = new FileHandler( fileName );
			} catch (FileNotFoundException e) {
				
				char createFile = toDoProg.promptForMessage(fileName + " not found, would you like to create it? y/n").toUpperCase().charAt(0);
				if(createFile == 'Y') {
					try {
						file = new FileHandler( fileName, true );
					} catch (IOException e1) {
						System.out.println("Weird File Error: " + e1);
					}
					promptAgain = false;
				}
				else {
					promptAgain = true;
				}
			} catch (IOException e) {
				System.out.println("File Error: " + e);
			}
			promptAgain = false;
		} while( promptAgain );
		
		ArrayList<String> rawEntries = file.toArrayList();
		
		//Build us that list!
		toDoProg.toDoList = new ToDoList();
		for(int i=0; i < rawEntries.size(); i++) {
			toDoProg.toDoList.add( new ToDoEntry( 
					Integer.parseInt(rawEntries.get(i).substring(0, 1)),
					rawEntries.get(i).substring(2) ) );
		}
		
		
		
		toDoProg.run();
		
		try {
			file.saveFile(toDoProg.toDoList.toString());
		} catch (IOException e) {
			System.out.println("File Error: " + e);
		}
		System.out.println(file.getFileName()+" Saved" );
		mainInput.close();
		
	}

}
