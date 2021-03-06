import java.util.*;
import java.io.*;
public class BuildOperation
{
    private final int MAX_LENGTH = 35;
    private String operand1String, operator, operand2String, negativeResult = "";
    private int[] operand1Array = new int[MAX_LENGTH];
    private int[] operand2Array = new int[MAX_LENGTH];
    private int[] resultArray = new int[MAX_LENGTH];
    private int i = 0, string1Index = 0, string2Index = 0, errorCode;
    
    /*
     * ErrorCodes: 
     * -1 = Result Overflow
     * -2 = Invalid Operator
     */
    
    public BuildOperation(String line)
    {
        Scanner aScanner = new Scanner(line);
        operand1String = aScanner.next();
        operator = aScanner.next();
        operand2String = aScanner.next();
        
        if( operand1String.length() > MAX_LENGTH )
            System.out.println("*** line ignored - first number too large:\n" + line);
        else if( operand2String.length() > MAX_LENGTH )
            System.out.println("*** line ignored - second number too large:\n" + line);
        else
        {
            //build first & second operand starting from max length - length of each operand
            for(i = 0; i < MAX_LENGTH; i++)
            {
                if( i >= MAX_LENGTH - operand1String.length() )
                {
                    operand1Array[i] = operand1String.charAt(string1Index) - '0';
                    string1Index++;
                }
                if( i >= MAX_LENGTH - operand2String.length() )
                {
                    operand2Array[i] = operand2String.charAt(string2Index) - '0';
                    string2Index++;
                }
            }
            
            //add or subtract operands based on operator
            if(operator.charAt(0) == '-')
                this.subtractArrays();
            else if(operator.charAt(0) == '+')
                this.addArrays();
            else
                errorCode = -2;
        }
    }
    
    public String getOperands()
    {
        return "Operand 1: " + operand1String + "\nOperand 2: " + operand2String;
    }
    public String getOperator()
    {
        return operator;
    }
    
    private void addArrays()
    {
        for(i=0; i<MAX_LENGTH; i++)
        {
            resultArray[i] = operand1Array[i] + operand2Array[i] + resultArray[i];
            
            //if result is 10 - 18 add the carryover (will always be 1) to the next index position

            if(resultArray[i] > 9 && i == 0)
            {
                errorCode = -1;
            } else if( resultArray[i] > 9 )
            {
                resultArray[i] = resultArray[i] % 10;
                resultArray[i-1] = resultArray[i-1] + 1;
            }
        }
    }
    
    private void subtractArrays()
    {
        boolean operand1Bigger = false, operand2Bigger = false;
        
        //loop to determine the bigger number.
        for(i=0; i < MAX_LENGTH && !operand1Bigger && !operand2Bigger; i++)
        {
            if(operand2Array[i] > operand1Array[i])
                operand2Bigger = !operand2Bigger;
            else if(operand2Array[i] < operand1Array[i])
                operand1Bigger = !operand1Bigger;
        }

        //RUN THROUGH & SUBTRACT
        if(operand2Bigger)
        {
            for(i=0; i<MAX_LENGTH; i++)
            {
                resultArray[i] = operand2Array[i] - operand1Array[i];
            }
            //Result will be negative so store "-" for use in toString
            negativeResult = "-";
        } else
        {
            for(i=0; i<MAX_LENGTH; i++)
            {
                resultArray[i] = operand1Array[i] - operand2Array[i];
            }
        }
        //ACCOUNT FOR BORROWING
        for(i=MAX_LENGTH-1; i>0; i--)
        {
            if(resultArray[i]<0)
            {
               resultArray[i] = resultArray[i] + 10;
               resultArray[i-1] = resultArray[i-1] -1;
            }
        }
    }
    
    public String toString()
    {
        String topString = "  ", middleString = operator + " ", equalLine = "--", bottomString = "  ";
        boolean resultBegin = false;
        
        //operand1Array operator operand2Array line resultArray 
        for(i = 0; i < MAX_LENGTH; i++)
        {
            if( i >= MAX_LENGTH - operand1String.length() )
                topString = topString + operand1Array[i];
            else
                topString = topString + " ";
                
            if( errorCode == -2)
                middleString = "*** invalid operator ***";
            else
            {
                if( i >= MAX_LENGTH - operand2String.length() )
                    middleString = middleString + operand2Array[i];
                else
                    middleString = middleString + " ";
            }
                
            equalLine = equalLine + "-";
            
            if( errorCode == -1 )
                bottomString = "*** result overflow ***";
            else
            {
                if( resultArray[i] == 0 && !resultBegin )
                    bottomString = bottomString + " ";
                else
                {
                    bottomString =  bottomString + resultArray[i];
                    resultBegin = true;
                }
            }
        }
        return topString + "\n" + middleString + "\n" + equalLine + "\n" + bottomString + "\n";
    }
}
