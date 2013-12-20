// COMP 1501 assignment 5
// Author: Will Fowler

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class GPACalculator
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String transcriptFileName = selectFile("Double-Click to Select Transcript File:");

        FileInputStream inputFile = new FileInputStream(transcriptFileName);
        Transcript transcriptFile = new Transcript(inputFile);
        int courseCounter = 0;
        double summGpa = 0;
        Course course;
        boolean errorFree = true;

        //test if transcript file doesn't have anything in it
        if( !transcriptFile.hasNextCourse())
        {
            System.out.print("undefined GPA - no courses completed");
            errorFree = false;
        }        
        else
        {
            //find courses in transcript file and print them to screen
            while( transcriptFile.hasNextCourse() && errorFree )
            {
                course = transcriptFile.nextCourse();

                if( course == null ) // course is returned null if transcript file has errors
                {
                    System.out.print("Invalid Transcript File\n");
                    errorFree = false; //abort while loop and stop printing of standing chart
                }
                else
                {        
                    summGpa = course.getGradePoint() + summGpa;
                    courseCounter++;
                    System.out.println(course);
                }
            }
        }

        //Print Chart
        if( errorFree )
        {
            StandingChart chart = new StandingChart(4,3,(summGpa/courseCounter));
            System.out.print(chart);
        }

    }
    public static String selectFile(String prompt)
    {
        JFileChooser fileChooser = new JFileChooser(".\\test data");

        fileChooser.setDialogTitle(prompt);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setControlButtonsAreShown(true);
        fileChooser.showOpenDialog(null);

        return fileChooser.getSelectedFile().getPath();
    }
}
