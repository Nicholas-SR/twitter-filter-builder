package src;

/**
 * Stores relevant tweet information into a TweetInfo object
 *
 */

public class TweetInfo {
	String username = "";
	String tweet = "";
	String bio = "";
	
	/**
	 * This method is the constructor for a TweetInfo instance
	 * @param username The username of the fetched account
	 * @param tweet The tweet obtained matching the user and search query
	 * @param bio The biography obtained matching the user and search query
	 */
	
	public TweetInfo(String username, String tweet, String bio) {
		this.username = username;
		this.tweet = tweet;
		this.bio = bio;
	}
	
	/**
	 * This method returns the username of the TweetInfo instance
	 * @return username Returns the username of the TweetInfo instance
	 */
	
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * This method returns the tweet of the TweetInfo instance
	 * @return tweet Returns the tweet of the TweetInfo instance
	 */
	
	public String getTweet() {
		return this.tweet;
	}
	
	/**
	 * This method returns the biography of the TweetInfo instance
	 * @return bio Returns the biography of the TweetInfo instance
	 */
	
	public String getBio() {
		return this.bio;
	}
}
