package webContentAnalyze;
/**
 * Used to generate Web Content Analyzer Statistics and other required information
 * This class uses the JSOUP Library to download and gather information for a given 
 * webpage
 * @see http://jsoup.org/
 * 
 * @author JKidney
 * @author WFowler
 * @version 1.1 
 * 
 *      Created: Nov 7, 2013
 *      Updated: Nov 7, 2013 - creation (jkidney)
 * Last Updated: Nov 21, 2013 - completion (WFowler)
 * 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import comparisonObjects.StringNonCaseSensitiveCompare;
import tree.BinarySearchTree;
import tree.treeIterators.BinaryTreeIterator;

public class WebContentAnalyzer 
{
	public static final String correctWordFileLocation = "words.txt";
	public BinarySearchTree<String, Frequency> treeT = null;
	public BinarySearchTree<String, Frequency> treeW = null;
	public BinarySearchTree<String, Frequency> treeGW = null;
	public BinarySearchTree<String, Frequency> bWords = null;
	
	/**
	 * Default constructor
	 */
	public WebContentAnalyzer() throws Exception 
	{
		treeT = new BinarySearchTree<String, Frequency>(new StringNonCaseSensitiveCompare());
		treeW = new BinarySearchTree<String, Frequency>(new StringNonCaseSensitiveCompare());
		treeGW = new BinarySearchTree<String, Frequency>(new StringNonCaseSensitiveCompare());
		bWords = new BinarySearchTree<String, Frequency>(new StringNonCaseSensitiveCompare());
		
	}
	
	/**
	 * Loads a given web page and builds content analysis information
	 * Works for a local file of a page on the Internet
	 * @param path the path to the web page to load ( either local file or full URL ) 
	 * @return an MSWordDocument object that contains all information gathered about the web content
	 * @throws Exception if any errors occur when building
	 */
	public MSWordDocument generateReportForPageContent(String path) throws Exception
	{
		
		Document doc = null; // this is the JSoup document object you will get tags and words from
		
		//Gather data from the given web page content
		
		//check and see if the given path is for a local file or
		//a page on the Internet
		if(isValidURL(path))
		{
			path = ensurePathHasProtocol(path);
			doc = buildFromInternetPage(path);
		}
		else // file on the local machine
			doc = buildFromLocalFile(path);
		
		// build the good words list
		try
		{
			buildWordsTreeFromFile("words.txt");
		}
		catch(IOException e)
		{
			System.out.println(" Good Words File Not Found");
		}

		//iterate through the elements and split into the trees that we need.
		destructElements( doc.body().getAllElements() );

		//Build the iterators to parse said trees.
		BinaryTreeIterator<String, Frequency> wordIter = treeW.getTraversalIterator(BinarySearchTree.IN_TRAV);
		BinaryTreeIterator<String, Frequency> tagIter = treeT.getTraversalIterator(BinarySearchTree.IN_TRAV);
		BinaryTreeIterator<String, Frequency> bWordIter = bWords.getTraversalIterator(BinarySearchTree.IN_TRAV);
		
		//Generate Document based upon the given data

		//Note: The library that is used to generate the MS word document has a very
		//      slow startup time when the document is generated for the very first time
		//      (around 10 seconds) 
		MSWordDocument document = new MSWordDocument();
		document.writeTitle("Report for: " + path);
		
		document.writeHeading1("Word Frequency");
		
		iterateAndWrite(document, wordIter);
		
		
		document.addPageBreak();
		
		document.writeHeading1("Tag Frequency");
		
		iterateAndWrite(document, tagIter);
		
		document.addPageBreak();
		
		document.writeHeading1("Misspelling");
		
		iterateAndWrite(document, bWordIter);
		
		return document;
	}
	
	/**
	 * Builds the treeGW Binary Search Tree From the file passed in
	 * @param fileName must be 1 word per line
	 * @throws IOException
	 */
	private void buildWordsTreeFromFile(String fileName) throws IOException
	{
		Scanner inputtedFile = new Scanner(new File(fileName));

		while(inputtedFile.hasNextLine())
			addToken(inputtedFile.nextLine(), "Gword");
		
		inputtedFile.close();
	}
	
	/**
	 * Accepts an Elements list and splits it into it's tags and words to  build the
	 * local treeT and treeW Binary Search Trees
	 * @param e Elements object of document elements
	 */
	private void destructElements(Elements e)
	{
		WordsExtractor extractor = new WordsExtractor();
		for(Element elem : e)
		{
			String tag = elem.tagName();
			extractor.setLine( elem.ownText() );
			
			
			//add tag
			addToken(tag, "tag");
			//add words
			while(extractor.hasMoreWords())
			{
				String s = extractor.nextWord();
				if(s != null)
				{
					addToken(s, "word");
					//check if it's misspelled
					if( treeGW.find(s) == null )
						addToken(s, "Bword");
				}
			}
		}
	}
	
	/**
	 * Adds the given token to the given tree, if it's found in the tree, 
	 * it increases it's frequency count
	 * @param token String - word to be added
	 * @param tree String - "tag" "word" or "Gword" are acceptable params
	 */
	private void addToken(String token, String tree)
	{
		BinarySearchTree<String, Frequency> thisTree = null;
		switch (tree)
		{
			case "tag":
				thisTree = treeT;
				break;
			case "word":
				thisTree = treeW;
				break;
			case "Gword":
				thisTree = treeGW;
				break;
			case "Bword":
				thisTree = bWords;
				break;
		}
		//add the token
		if(thisTree.containskey(token))
			thisTree.find(token).increment();
		else
			thisTree.add(token, new Frequency(token));
	}
	
	/**
	 * Iterate through tree and write to the document in the prescribed manner
	 * @param document MSWordDocument - desired document to output to
	 * @param iter BinaryTreeIterator - iterator for tree and method of traversal to 
	 */
	private void iterateAndWrite(MSWordDocument document, BinaryTreeIterator<String, Frequency> iter)
	{
		while(iter.canMoveToNext())
		{
			document.writeText( String.format("(%10s) %s", 
					iter.getCurrentData().getFreq(), 
					iter.getCurrentData().getToken()) );
			iter.moveToNext();
		}
	}
	
	/**
	 * Loads and builds a web page from the local file system
	 * @param path the path to the local page
	 * @return the constructed document object
	 * @throws Exception if any errors occur when building
	 */
	private Document buildFromLocalFile(String path) throws IOException
	{
		return Jsoup.parse( new File(path), "UTF-8", "");
	}
	/**
	 * Loads and builds a web page from an Internet source
	 * @param path the path to the local page
	 * @return the constructed document object
	 * @throws Exception if any errors occur when building
	 */
	private Document buildFromInternetPage(String url) throws IOException
	{
		return Jsoup.connect(url).get();
	}
	/**
	 * Helper method used to check if a given string is a valid URL or not
	 * @param urlStr the string to check
	 * @return true for valid false otherwise
	 */
	private boolean isValidURL(String urlStr) 
	{
		boolean result = false;
		Pattern p = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");  
		Matcher m = p.matcher(urlStr);
		result = m.matches();
		return result;
	}

	/**
	 * Checks to see if the url string beings with http:// or https://
	 * if it does not start with either it will add http:// to the url and return
	 * the new string.
	 * @param url the url path to check
	 * @return the updated url path ( with HTTP:// added if needed)
	 */
	private String ensurePathHasProtocol(String url)
	{
		if(!url.startsWith("http://") && !url.startsWith("https://")) 
			url = "http://" + url;
		return url;
	}
}
