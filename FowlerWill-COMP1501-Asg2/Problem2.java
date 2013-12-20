import java.util.Scanner;
public class Problem2
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int totalSeconds, hours, minutesInSeconds, minutes, seconds; //All variables are here
        System.out.println("Welcome to the Seconds to Hours, Minutes, Seconds Converter!");
        System.out.println("");
        System.out.print("Total number of seconds: \t");
        totalSeconds = scanner.nextInt();   //Get Total amount of time in seconds from user
        System.out.println("");
        
        //Begin Conversion
        hours               = totalSeconds / 3600;
        minutesInSeconds    = totalSeconds % 3600; //minutesInSeconds represents all leftover time 
        minutes             = minutesInSeconds / 60;
        seconds             = minutesInSeconds % 60;
        
        //Output 
        System.out.println("This is equivalent to:");
        System.out.print("\tnumber of hours:\t" + hours + "\n");
        System.out.print("\tnumber of minutes:\t" + minutes + "\n");
        System.out.print("\tnumber of seconds:\t" + seconds + "\n");
    }
}
