package src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to search through elements in an ArrayList
 *
 */

public class Search {
	
	ArrayList<TweetInfo> allInfo = new ArrayList<TweetInfo>();
	ArrayList<String> offendingUsers = new ArrayList<String>();
	Scanner scan = new Scanner(System.in);
	String keyword = "";
	
	/**
	 * This method is the constructor for a Search instance
	 * @param allTweets an ArrayList of TweetInfo objects to search through
	 */
	
	public Search(ArrayList<TweetInfo> allInfo) {
		this.allInfo = allInfo;	
		
		getKeyword();
		searchAll();
		
	}
	
	/**
	 * This method obtains user input for the keyword that will be searched 
	 */
	
	public void getKeyword() {

			
		while(this.keyword.length() < 2) { // Keyword must be greater than 2 characters
			System.out.println("Please enter a keyword: ");
			this.keyword = scan.nextLine();
		}
		
					
	}
	
	/**
	 * This method searches through the ArrayList and returns all searched matching the query
	 * @return offendingUsers ArrayList of users matching the search query
	 */
	
	public ArrayList<String> searchAll() {
		System.out.println("Searching user tweets and biographies for " + keyword);
		this.offendingUsers.clear();
		
		for (TweetInfo tweet : this.allInfo) {
			if (tweet.getBio().toLowerCase().contains(keyword.toLowerCase())) { // Changes both the tweet and and the keyword to lowercase in order to match better
				this.offendingUsers.add(tweet.getUsername());
			} else if (tweet.getTweet().toLowerCase().contains(keyword.toLowerCase())) {
				this.offendingUsers.add(tweet.getUsername());
			}
			
		}
		
		if (this.offendingUsers.size() == 0) {
			System.out.println("No matching tweets or biographies found");
			
		}
		
		return this.offendingUsers;
		
	}
	
	/**
	 * This method returns the list of offendingUsers
	 * @return offendingUsers ArrayList of users matching the search query
	 */
	public ArrayList<String> getOffendingUsers() { return this.offendingUsers; }


}
