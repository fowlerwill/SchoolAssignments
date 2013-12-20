//COMP 1501 - Assignment 3 - Registration Number Generator by Will Fowler, esq. V1.0
import java.util.Random;
import java.util.Scanner;
import java.util.*; 
import java.text.SimpleDateFormat;
public class main
{
    public static void main(String args[])
    {
        //-----------------------Input
        Scanner scanner = new Scanner(System.in);
        //User inputs License Number
        System.out.print("Enter license plate number:\t");
        String licenseNumber = scanner.next();
        licenseNumber = licenseNumber.toUpperCase();
        int licenseAlpha = licenseNumber.charAt(1) - 64; //convert unicode to A=1, B=2, C=3 etc for registrationNumber[2]
        //User inputs Name 
        System.out.print("Enter owner name:\t\t");
        String firstName = scanner.next();
        String lastName = scanner.next();
        
        //-----------------------Computation
        
        //Calendar Stuff
        GregorianCalendar renewalDate = new GregorianCalendar();
        renewalDate.add(Calendar.YEAR, 1); //add 1 year to current date for renewal date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM yyyy"); //format date nicely
        int renewalYear = renewalDate.get(Calendar.YEAR); //store renewal year for use in registration calculation
        
        //Generate registration number. the while loop generates each number and stores it in an array, applying restrictions as needed
        Random randomGen = new Random();
        int regNumberCount = 0; //counter for generating both loop and array indicies
        final int REGISTRATION_DIGITS = 12; //total registration digits required
        int[] registrationNumber = new int[REGISTRATION_DIGITS]; //creates array with each digit of the registration stored in sequential array indecies
        String finalRegistrationNumber = "";
        while ( regNumberCount < 12 )
        {
            registrationNumber[regNumberCount] = randomGen.nextInt(10);
            if (regNumberCount == 0)
            {
                registrationNumber[regNumberCount] = randomGen.nextInt(7) + 3; //Generates random number that excludes 0,1,2
            }
            if (regNumberCount == 2)
            {
                int licenseNumbersValue = Integer.parseInt(licenseNumber.substring(4,7));
                registrationNumber[regNumberCount] = ((licenseAlpha + licenseNumbersValue) / 10) % 10;
            }
            if (regNumberCount == 7)
            {
                registrationNumber[regNumberCount] = ((renewalYear % 100)/10 + (renewalYear % 10)) % 10;
            }
            if (regNumberCount == 11)
            {
                registrationNumber[regNumberCount] = (registrationNumber[2] + registrationNumber[5] + registrationNumber[7])% 10;
            }
            finalRegistrationNumber = (String)finalRegistrationNumber + registrationNumber[regNumberCount]; //concatinates current result to final string
            regNumberCount++;
        }
        
        //Capitlization and reorganize of names
        char firstInitial = firstName.toUpperCase().charAt(0);
        char lastInitial = lastName.toUpperCase().charAt(0);
        String firstNameCorrect = firstInitial + firstName.substring(1, firstName.length());
        String lastNameCorrect = lastInitial + lastName.substring(1, lastName.length());
        
        //-----------------------Output
        System.out.println("\n-------------------------------------------");
        System.out.println("Registration No.\t" + finalRegistrationNumber);
        System.out.println("License Plate No.\t" + licenseNumber);
        System.out.println("Owner\t\t\t" + lastNameCorrect + ", " + firstNameCorrect);
        System.out.println("Renewal Month\t\t" + dateFormatter.format(renewalDate.getTime()));     
    }
}
