package exampleProgram;
/**
 * Holds information for a single individual
 * @author JKidney
 * @version 2 
 * 
 *      Created: Oct 25, 2013
 * Last Updated: Oct 25, 2013 - creation (jkidney)
 */
public class ContactInformation 
{
   private String firstName;
   private String lastName;
   private String emailAddress;
   
   /**
    * Constructor
    * @param firstName first name for the contact
    * @param lastName last name for the contact
    * @param emailAddress email address for the contact (format is not verified)
    */
   public ContactInformation(String firstName, String lastName, String emailAddress) 
   {
	this.firstName = firstName;
	this.lastName = lastName;
	this.emailAddress = emailAddress;
   }

  public String getFirstName() {return firstName;}
  public void setFirstName(String firstName) { this.firstName = firstName; }
  
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  public String getEmailAddress() { return emailAddress; }
  
  public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
	
   /**
    * Formats and returns a string for easy display of the information to a console 
    * @return the formated string ready for output
    */
  public String getConsolPrintString()
  {
	  return String.format("%30s %15s %15s", emailAddress, firstName, lastName);
  }
}
