import java.io.*;
import java.util.Scanner;
/**
 * Player Class
 * Simple class to hold a player name and wallet value.
 * can get & set name and wallet values
 * can store it's information in SystemInfo.txt
 * 
 * @author Will Fowler
 * @date 26 mar 2013
 * 
 */

public class Player
{
    private String name = "";
    private double wallet = 0.0;
    private Scanner keyboard = new Scanner(System.in);
    
    public Player(String aName, double aWallet)
    {
            this.setName(aName);
            if( aWallet >= 0 )
                this.setWallet(aWallet);
            else
                wallet = -1;            //TODO: MAKE NEGATIVE WALLET VALUE BAD (call start again method?)
    }
    
    public void setName(String aName)
    {
        try
        {
        name = aName;
        }
        catch(Exception e)
        {
            System.out.println("Name Input did not match expected data type (Must be String)");
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setWallet(double aWallet)
    {
        wallet = aWallet;
    }
    
    public double getWallet()
    {
        return wallet;
    }
    
    public void store()
    {
        try
        {
              // Create file 
              FileWriter fstream = new FileWriter("SystemInfo.txt");
              BufferedWriter out = new BufferedWriter(fstream);
              
              //Fill it with parameters (name & amount) provided
              out.write(name);
              out.newLine();
              out.write(String.valueOf(wallet));
              
              //Close stream and confirm
              out.close();
              System.out.println("File Saved Successfully");
        }
        catch (Exception e)
        {
              System.err.println("Error writing file: " + e.getMessage());
        }
    }
    
    public double makeBet()
    {
        double betAmount = 0;
        do
            {
                try
                {
                    System.out.println("How much would you like to bet? (up to " + getWallet() + ")");
                    betAmount = keyboard.nextDouble();
                }
                catch(Exception e)
                {
                    System.out.println("Please enter a valid double (like 10.0)");
                }
            }
            while(betAmount > getWallet() || betAmount <= 0 );
        return betAmount;
    }
    
    public String toString()
    {
        return  "\tPlayer Name:\t" + name + 
                "\n\tWallet:\t\t" + wallet;
    }
        
}
