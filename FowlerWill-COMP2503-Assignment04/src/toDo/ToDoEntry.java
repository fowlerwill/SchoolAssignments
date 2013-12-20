package toDo;
/**
 * holds information and functionality about a single to do list entry
 * @author Will Fowler
 */
public class ToDoEntry 
{
	public int priority;
	public String entryDescription;
	
	/**
	 * constructor used to set up basic information about the todo entry
	 * @param priority the priority of the entry
	 * @param description the description of what the to do entry is
	 */
	public ToDoEntry(int aPriority, String description)
	{
		this.priority = aPriority;
		this.entryDescription = description;
	}
	
	/**
	 * mutates the this's priority field
	 * @param newPriority int 1 || 2 || 3
	 */
	public void updatePriority(int newPriority) {
		this.priority = newPriority;
	}
	
	/**
	 * Concerts the object into a string representation
	 * @return returns the string representation of the object
	 */
	public String toString()
	{
	  return priority + " " + entryDescription; 
	}
}
