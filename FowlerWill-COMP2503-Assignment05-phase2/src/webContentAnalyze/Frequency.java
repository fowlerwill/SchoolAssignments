package webContentAnalyze;

/**
 * 
 * Data object for Binary Search Tree,
 * Holds a token and a frequency count
 * @author WFowler
 * @version 1
 * 
 *      Created: Nov 20, 2013 - created (WFowler)
 *
 */
public class Frequency
{
	private String token = "";
	private int freq = 0;
	
	/**
	 * Constructor - sets instance var, and increments to 
	 * frequency of 1
	 * @param aToken String - Token to track.
	 */
	public Frequency(String aToken)
	{
		token = aToken;
		freq++;
	}
	
	/**
	 * Increment Frequency by 1
	 */
	public void increment()
	{
		freq++;
	}
	
	/**
	 * return the token
	 * @return the token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * set the token
	 * @param token the token to set
	 */
	public void setToken(String token)
	{
		this.token = token;
	}

	/**
	 * get the frequency
	 * @return the freq
	 */
	public int getFreq()
	{
		return freq;
	}

	/**
	 * set the frequency to specific number
	 * @param freq the freq to set
	 */
	public void setFreq(int freq)
	{
		this.freq = freq;
	}

	/**
	 * toString method
	 */
	public String toString()
	{
		return token + " appears " + freq + " times\n";
	}

}
