import java.text.*;

public class StandingChart
{
    private int maxPossible, granularity;
    private double gpa;
    private DecimalFormat df = new DecimalFormat("0.0");
    
    public StandingChart( int maxPossible, int granularity, double gpa )
    {
        assert maxPossible > 0;
        assert granularity > 0;
        this.maxPossible = maxPossible;
        this.granularity = granularity;
        this.gpa = gpa;
    }
    
    public void setMaxPossible( int maxPossible ) 
    {
        assert maxPossible > 0;
        this.maxPossible = maxPossible;
    }
    
    public void setGranularity( int granularity ) 
    {
        assert granularity > 0;
        this.granularity = granularity;
    }
    
    public void setGPA( double gpa )
    {
        this.gpa = gpa;
    }
    
    public String toString()
    {
        int totalLines = maxPossible * granularity;
        int linesLeft = totalLines;
        String standingChartString = "Current Standing: \n";
                
        while( linesLeft >= 0 )
        {
            if( linesLeft % granularity == 0 )
            {
                standingChartString = standingChartString + (linesLeft / granularity);                  //if even number print number rather than dash
            }
            else
            {
                standingChartString = standingChartString + "-";                                        //print dash in between whole numbers
            }
            
            //test if gpa == linesLeft
            if( gpa >= (double)linesLeft/granularity && gpa < (double)(linesLeft+1)/granularity )       //if gpa is between this line and next 
            standingChartString = standingChartString + "\t<-- GPA = " + df.format(gpa);                // print gpa on current line
            
            standingChartString = standingChartString + "\n";                                           //print new line
            linesLeft--;
            
        }
        
        return standingChartString;
    }
}
