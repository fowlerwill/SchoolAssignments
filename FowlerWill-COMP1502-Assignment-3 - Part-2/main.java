/**
 * main method for casino games
 * @author: Will Fowler
 * @date: 26 Mar 2013
 */
import java.util.*;
import java.io.*;
public class main
{
    public static void main(String args[])
    {
        //Initialize variables
        Scanner keyboard = new Scanner(System.in);
        Player thePlayer = new Player("Dummy", -1.0);
        char yesOrNo;
        boolean dataCorrect = true, infoCorrect = true;
        
        //Start the Program, Look for SystemInfo.txt in current directory
        File file = new File("SystemInfo.txt");
        

        if ( file.exists() ) 
        {
            //Okay, so the file exists, now let's try to get the name and amount out of it
            //if SystemInfo.txt exists, load plyrName and amountLeft
            try
            { 
                Scanner fileScanner = new Scanner(file); 
                thePlayer.setName(fileScanner.nextLine());
                thePlayer.setWallet(Double.parseDouble(fileScanner.nextLine()));
            }
            catch(NoSuchElementException e)
            {
                System.out.println("File Appears Incomplete");
                dataCorrect = false;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Wallet Input did not match expected data type (Must be Double)");
                dataCorrect = false;
            }
            catch(Exception e)
            {
                //Here there's been an unidentified problem with the file, 
                System.out.println("Error Reading File");
                dataCorrect = false;
            }
            
            if( dataCorrect )
            {
                //Prompt user to ensure information is correct
                System.out.println( "It looks like you've played before,\n" + 
                                    thePlayer.toString() +
                                    "\nis this correct? y/n");
                yesOrNo = keyboard.next().toUpperCase().charAt(0);
                
                if(yesOrNo == 'Y')
                    System.out.println("Great! Good Luck");
                else
                {
                    System.out.print("Sorry about that, you'll have to start again\n");
                    infoCorrect = false;
                }
            }
        }
        
        
        if( !dataCorrect || !file.exists() || !infoCorrect)
        {
            //else SystemInfo.txt doesn't exist, or data bad or 
            //prompt for username & give $100 to start
            System.out.print("Please enter your name: ");
            thePlayer.setName(keyboard.next());
            System.out.println( "Thanks " + thePlayer.getName() + 
                                "! We'll give you $100 to start, good luck!");
            thePlayer.setWallet(100.00);
        }
        
        //Now that the data is good, save the Player!
        thePlayer.store();
        
        /* 
         * =========================================================================================
         *              AT THIS POINT THE PLAYER NAME AND AMOUNT ARE G2G (GOOD TO GO)
         *              So let's do some gamblin'
         * ========================================================================================= 
         */
       
        Deck aDeck = new Deck();
        Menu aMenu = new Menu(aDeck);
        char menuChoice;
        
        while(!aMenu.haveTheyQuit())
        {  
            System.out.println(aMenu.toString());
            menuChoice = keyboard.next().toUpperCase().charAt(0);
            aMenu.menuQuery(menuChoice, thePlayer);
        }
    }
    
}
