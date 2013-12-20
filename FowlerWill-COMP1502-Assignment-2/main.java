import java.util.Scanner;
import java.lang.Character;
/**
                 _________ _        _       
        |\     /|\__   __/( \      ( \      
        | )   ( |   ) (   | (      | (      
        | | _ | |   | |   | |      | |      
        | |( )| |   | |   | |      | |      
        | || || |   | |   | |      | |      
        | () () |___) (___| (____/\| (____/\
        (_______)\_______/(_______/(_______/
                                            
         _______  _______           _        _______  _______ 
        (  ____ \(  ___  )|\     /|( \      (  ____ \(  ____ )
        | (    \/| (   ) || )   ( || (      | (    \/| (    )|
        | (__    | |   | || | _ | || |      | (__    | (____)|
        |  __)   | |   | || |( )| || |      |  __)   |     __)
        | (      | |   | || || || || |      | (      | (\ (   
        | )      | (___) || () () || (____/\| (____/\| ) \ \__
        |/       (_______)(_______)(_______/(_______/|/   \__/
 * *
 * *Assignment 2 for COMP 1502 
 * *Feb 18 2013
 * *
 * *This is a main class that will run a sudoko game. it creates a board object, and a menu object
 * *it then puts the user in an infinite loop, reprompting the user with the menu and requesting an entry
 * *after every action. 
 * *!!!The program is exited by the quit method of the Menu class, which performs a System.exit to get out of
 * *the infinite reprompting loop!!!
 */

public class main
{
    
    public static void main(String args[])
    {
        //initialize variables
        Scanner keyboard    = new Scanner(System.in);
        Board aBoard        = new Board();
        Menu aMenu          = new Menu();       
        char menuInput;
        
        System.out.print("Welcome to a Sudoku program by Will Fowler.\n\n");
        
        while(true) 
        {
            //prompt user for entry, feed to menu
            System.out.println(aMenu.toString());
            menuInput = keyboard.next().toUpperCase().charAt(0);
            aMenu.menuQuery(menuInput, aBoard);
        }
    }
}
