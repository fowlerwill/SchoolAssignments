package exampleProgram;

/**
 * Runs the full program for a basic Contact Information Database program
 * @author JKidney
 * @version 2 
 * 
 *      Created: Oct 25, 2013
 * Last Updated: Oct 25, 2013 - creation (jkidney)
 */

import exampleProgram.UserIO.*;

public class PeopleContactKeeper 
{
	private ContactDatabase database;
	private Menu userMenu;
	private ConsoleCom com;


	/**
	 * Constructor
	 */
	public PeopleContactKeeper()
	{
		database = new ContactDatabase();
		this.com = new ConsoleCom();
		userMenu = new Menu(com);
		setUpMenu();
	}

	/**
	 * Sets up the basic menu that will be used to interact with the user
	 */
	private void setUpMenu()
	{
		userMenu.addMenuOption( new MenuOption("d", "display contacts") );
		userMenu.addMenuOption( new MenuOption("a", "add contact") );
		userMenu.addMenuOption( new MenuOption("r", "remove contact") );
		userMenu.addMenuOption( new MenuOption("u", "update contact email") );
		userMenu.addMenuOption( new MenuOption("q", "quit") );
	}


	/**
	 * Executes the main flow of the PeopleContackKeeper Program
	 */
	public void run()
	{
		MenuOption choice = null;
		do
		{
			choice = userMenu.getUserChoice();
			executeUserChoice(choice);
		}
		while(!choice.isAMatch("q"));
	}

	/**
	 * Executes the users selected choice
	 * @param choice the selected choice by the suer from the menu
	 */
	private void executeUserChoice(MenuOption choice)
	{
		if(choice.isAMatch("d")) database.consolePrint();
		else if(choice.isAMatch("a")) addNewEntry(); 
		else if(choice.isAMatch("r")) removeEntry(); 
		else if(choice.isAMatch("u")) updateContactEmail(); 
	}

	/**
	 * interacts with the user to add a new contact
	 */
	private void addNewEntry()
	{
		String email = com.getInputString("     Enter Email: ");
		String first = com.getInputString("Enter first name: ");
		String last  = com.getInputString(" Enter last name: ");

		if(database.addContact( new ContactInformation(first, last, email)))
		{
			com.println("The contact has been added");
		}
		else
			com.println("A contact exists with the givem email address already");
	}

	/**
	 * interacts with the user to remove a contact
	 */
	private void removeEntry()
	{
		database.consolePrint();
		if(database.getSize() > 0)
		{
			String email = com.getInputString("Enter Email of contact to remove: ");

			ContactInformation info = database.getContact(email); 

			if(info != null)
			{
				
				if( com.getInputYesNo("Remove \""+info.getConsolPrintString()+"\" ?") )
				{
					database.removeContact(email);
					com.println("the selected entry has been removed");
				}
				else
					com.println("the selected entry has not been removed");
			}
			else
				com.println("No such contact exists");

			com.pauseUntilHitEnter();
		}
		else
			com.println("No entried to remove");
	}

	/**
	 * interacts with the user to update the email address for a contact
	 */
	private void updateContactEmail()
	{

		database.consolePrint();
		if(database.getSize() > 0)
		{
			String email = com.getInputString("Enter Email of contact to update: ");

			ContactInformation info = database.getContact(email); 

			if(info != null)
			{
				
				if( com.getInputYesNo("Update \""+info.getConsolPrintString()+"\" ?") )
				{
					
					database.removeContact(email);
					String nemail = com.getInputString("Enter new Email: ");
					info.setEmailAddress(nemail);
					database.addContact(info);
					
					com.println("the selected entry has been removed");
				}
				else
					com.println("the selected entry has not been removed");
			}
			else
				com.println("No such contact exists");

			com.pauseUntilHitEnter();
		}
		else
			com.println("No entried to update");
	}
}
