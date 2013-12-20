//########################################
//#Will Fowler - Comp 1502 && Assignment 1
//########################################
import java.util.*;
import java.io.*;

public class main
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner keyboard = new Scanner(System.in);
        final int MAX = 10;
        BuildOperation[] operation = new BuildOperation[MAX];
        int i = 0;
        
        //Prompt user for file to read, puts it in fileScanner
        System.out.println("File to open: ");
        String fileName = keyboard.next();        
        Scanner fileScanner = new Scanner(new File (fileName));
        
        while(fileScanner.hasNext()) 
        {
            operation[i] = new BuildOperation(fileScanner.nextLine());
            System.out.println(operation[i]);
            i++;
        }
        

        
    }
}
