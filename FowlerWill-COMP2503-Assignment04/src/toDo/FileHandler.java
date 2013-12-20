package toDo;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File handling class. Builds File object from inputted filename, saves files and validates
 * data
 * 
 * @author Will Fowler
 *
 */
public class FileHandler {
	
	public String fileName;
	public Scanner inputtedFile;
	
	/**
	 * Default Constructor 
	 * @param theFileName String - the file name to use
	 * @throws IOException - in case the file doesn't exist
	 */
	public FileHandler(String theFileName) throws IOException {
		fileName = theFileName;
		inputtedFile = new  Scanner( new File(fileName) );
	}
	
	/**
	 * Overloaded constructor for the class to create a new file
	 * @param theFileName String - the actual name of the file
	 * @param newFile Boolean - set to true to make a new file.
	 * @throws IOException 
	 */
	public FileHandler(String theFileName, boolean newFile) throws IOException {
		if(newFile) {
			fileName = theFileName;
			
			PrintStream newFileSave = new PrintStream(new FileOutputStream(fileName));
			newFileSave.print("");
			newFileSave.close();
			
			inputtedFile = new  Scanner( new File(fileName) );
		}
		
	}
	
	/**
	 * returns fileName
	 * @return String - name of the file
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * saves the file
	 * @param fileContent String - contents of the file to save
	 * @throws IOException
	 */
	public void saveFile(String fileContent) throws IOException {
		PrintStream newFileSave = new PrintStream(new FileOutputStream(fileName));
		newFileSave.print(fileContent);
		newFileSave.close();
	}
	
	/**
	 * Verifies that a String is formatted correctly for the todoentry
	 * @param theLine String - The line in question
	 * @return Boolean - True or False if the line is good or not.
	 */
	public boolean verifyLine(String theLine) {
		//conditions for failure
		
		char priority = theLine.charAt(0);
		
		if( Character.isDigit(priority) &&
				Character.valueOf(priority) > '0' &&
				Character.valueOf(priority) < '4' &&
			theLine.charAt(1) == ' ' &&
			theLine.length() > 2 ) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	/**
	 * Returns an ArrayList of the inputted file's contents once verified
	 * @return ArrayList<String> - Contents of file, verified.
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> line = new ArrayList<String>();
		while(inputtedFile.hasNextLine()) {
			String thisLine = inputtedFile.nextLine();
			if(verifyLine(thisLine))
				line.add(thisLine);
		}
		return line;
	}

}
