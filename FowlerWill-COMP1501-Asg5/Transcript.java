import java.util.Scanner;
import java.io.*;

public class Transcript
{
    private Scanner fileScanner;
    private Course course;

    public Transcript (FileInputStream input )
    {
        fileScanner = new Scanner(input);
    }

    public boolean hasNextCourse()
    {
        if( fileScanner.hasNext() )
            return true;
        else
            return false;
    }

    public Course nextCourse()
    {
        String theSubject       = fileScanner.next().toUpperCase();
        int theCourseNumber     = fileScanner.nextInt();                
        String theLetterGrade   = fileScanner.next().toUpperCase();     

        //Error detecting for invalid input
        if( theSubject.length() != 4 ||
            theSubject.charAt(0) < 'A' || theSubject.charAt(0) > 'Z' ||
            theSubject.charAt(1) < 'A' || theSubject.charAt(1) > 'Z' ||
            theSubject.charAt(2) < 'A' || theSubject.charAt(2) > 'Z' ||
            theSubject.charAt(3) < 'A' || theSubject.charAt(3) > 'Z')                   //Ensure Subject is 4 letters long & contains only letters
            return null;
        else if( theCourseNumber / 1000  < 1 || theCourseNumber / 10000 > 0)            //Ensure CourseNumber is >1,000 and <10,000
            return null;
        else if( theLetterGrade.charAt(0) > 'F' || theLetterGrade.charAt(0) < 'A' ||    //letter grade is A,B,C,D,F
        theLetterGrade.charAt(0) == 'E' || theLetterGrade.length() > 2 )                //Not E, and length of string isn't >2 
            return null;
        else
            return new Course(theSubject, theCourseNumber,  theLetterGrade);
    }

}
