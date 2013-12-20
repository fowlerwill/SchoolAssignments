package exampleProgram;

import comparisonObjects.StringNonCaseSensitiveCompare;
import tree.BinarySearchTree;
import tree.treeIterators.BinaryTreeIterator;

/**
 * Manages storage and retrieval of all contacts in the system
 * @author JKidney
 * @version 1 
 * 
 *      Created: Oct 25, 2013
 * Last Updated: Oct 25, 2013 - creation (jkidney)
 */

public class ContactDatabase 
{
	// the key in this case will always be the email address of the contact
	//makes for unique storage and retrieval of information
	private BinarySearchTree<String, ContactInformation> contactTree;

	/**
	 * Constructor: Sets up basic Binary Search Tree for information storage and retrieval
	 */
	public ContactDatabase()
	{
		contactTree = new BinarySearchTree<>( new StringNonCaseSensitiveCompare() ); 
	}

	/**
	 * Adds the contact to the database only if the email address does not already exist
	 * @param info the contact to add
	 * @return true if the contact was added, false if a contact with the same email address already exists
	 */
	public boolean addContact( ContactInformation info )
	{
		boolean result = false;
		if(!contactTree.containskey(info.getEmailAddress()))
		{
            contactTree.add(info.getEmailAddress(), info);
			result = true;
		}
		
		return result;
	}

	/**
	 * Retrieves the contact from the database based upon the email address
	 * @param emailAddress the email address to use for lookup
	 * @return null if not found, otherwise the original contact is returned
	 */
	public ContactInformation getContact(String emailAddress)
	{
	   return contactTree.find(emailAddress);
	}

	/**
	 * Removes the given contact from the database based upon their email address
	 * @param emailAddress
	 * @return null if no remove was done, the removed contact otherwise 
	 */
	public ContactInformation removeContact(String emailAddress)
	{
	  return contactTree.remove(emailAddress);	
	}
	
	/**
	 * prints the contacts to the console in alphabetical order
	 */
	public void consolePrint()
	{
		BinaryTreeIterator<String, ContactInformation> iter = contactTree.getTraversalIterator(BinarySearchTree.IN_TRAV);
		int count = 0;
		
		System.out.println( String.format(" %3s  %30s %15s %15s","#", "Email Address","First Name","Last name")) ;
		while(iter.canMoveToNext())
		{
			count++;
			System.out.println(String.format("(%3d) %s", count, iter.getCurrentData().getConsolPrintString() ));
			iter.moveToNext();
		}
	}
	
	/**
	 * Gives the number of contacts in the database
	 * @return
	 */
	public int getSize() { return contactTree.getSize(); }
}
