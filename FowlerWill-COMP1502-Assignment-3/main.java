import java.util.Scanner;
public class main
{
    
    public static void main(String args[])
    {
        Deck aDeck = new Deck();
        Menu aMenu = new Menu(aDeck);
        char menuChoice;
        Scanner keyboard = new Scanner(System.in);
        
        while(!aMenu.haveTheyQuit())
        {  
            System.out.println(aMenu.toString());
            menuChoice = keyboard.next().toUpperCase().charAt(0);
            aMenu.menuQuery(menuChoice);
        }

    }
}
