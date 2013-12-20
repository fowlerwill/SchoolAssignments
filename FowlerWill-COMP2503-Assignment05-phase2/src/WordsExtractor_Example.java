import webContentAnalyze.WordsExtractor;


public class WordsExtractor_Example {

	public static void main(String[] args) 
	{
		WordsExtractor extractor = new WordsExtractor();
		
		extractor.setLine("this is an example input line 1 1-0 & %");
		
		while(extractor.hasMoreWords())
		{
			System.out.println(extractor.nextWord());
		}

	}

}
