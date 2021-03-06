
public class Course
{
    private String subject, letterGrade;
    private int number;
    private double gradePoint;
    
    public Course( String subject, int number, String letterGrade ) 
    {
        assert subject.length() == 4;
        assert number / 1000 <= 1;
        assert letterGrade.length() < 3;
        this.subject = subject;
        this.number = number;
        this.letterGrade = letterGrade;
    }
    
    public String getSubject()
    {
        return subject;
    }
    
    public void setSubjec( String subject )
    {
        this.subject = subject;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    public void setNumber( int number )
    {
        this.number = number;
    }
    
    public String getLetterGrade()
    {
        return letterGrade;
    }
    
    public void setLetterGrade(String letterGrade)
    {
        this.letterGrade = letterGrade;
    }
    
    public double getGradePoint()
    {
        double gradePoint;
        if( letterGrade.charAt(0) == 'A' )              //Set up basic GPAs
            gradePoint = 4.0;                        
        else if( letterGrade.charAt(0) == 'B' ) 
            gradePoint = 3.0;
        else if( letterGrade.charAt(0) == 'C' ) 
            gradePoint = 2.0;
        else if( letterGrade.charAt(0) == 'D' )
            gradePoint = 1.0;
        else
            gradePoint = 0.0;
        
        if( letterGrade.length() == 2 )                 //Modify basic GPAs to account for + and -
        {
            if( letterGrade.charAt(1) == '+' )
            {
                if( letterGrade.charAt(0) == 'A' )
                {} // do nothing
                else if( letterGrade.charAt(0) == 'D' )
                    gradePoint = gradePoint + 0.5;
                else
                gradePoint = gradePoint + 0.3;
            }
            else if( letterGrade.charAt(1) == '-' )
            {
                if( letterGrade.charAt(0) == 'D' )
                {} //do nothing
                else
                gradePoint = gradePoint - 0.3;
            }
        }
        return gradePoint;
        
    }
    
    public String toString()
    {
        return subject + " " + number + " " + letterGrade + "\t(worth = " + this.getGradePoint() + ")";
    }
    
    
}
