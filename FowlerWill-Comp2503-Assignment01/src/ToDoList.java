import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * all functionality related around managing a list of to do entries.
 * @author Will Fowler
 */
public class ToDoList {
	
	public ArrayList<ToDoEntry> theList = new ArrayList<ToDoEntry>();
	
	/**
	 * displays the list to the given console output stream
	 */
	public void display() {
		boolean medPriorityPrinted = false, lowPriorityPrinted = false;
		System.out.println("\nHigh Priority: \n");
		for(int i=0; i<theList.size(); i++) {
			
			if( theList.get(i).priority == 2 && !medPriorityPrinted ) {
				System.out.println("\nMedium Priority: \n");
				medPriorityPrinted = true;
			} else if(theList.get(i).priority == 3 && !lowPriorityPrinted) {
				System.out.println("\nLow Priority: \n");
				lowPriorityPrinted = true;
			}
			
			System.out.println(i+1 + ": - " + theList.get(i).entryDescription);
		}
		
		System.out.print("\n");
		
	}
	
	/**
	 * passes back the given entry and the location 
	 * @param index the location of the entry
	 * @return the entry object if found, null if it was out of bounds
	 */
	public ToDoEntry lookup(int index) {
		return theList.get(index);
	}
	
	/**
	 * removes the item with the given position number (if possible)
	 * @param index
	 */
	public void remove(int index) {
		theList.remove(index);
	}
	
	/**
	 * adds an item with the given description and priority
	 * @param entry the entry object to add
	 */
	public void add(ToDoEntry entry) {
		theList.add(entry);
		Collections.sort(theList, new Comparator<ToDoEntry>() {
	        @Override public int compare(ToDoEntry e1, ToDoEntry e2) {
	            return e1.priority - e2.priority;
	        };
		});
	}
	

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String returnString = "";
		
		for(int i=0; i<theList.size(); i++) {
			returnString += theList.get(i).toString() + "\n";
		}
		
		return returnString;
	}
	
}
