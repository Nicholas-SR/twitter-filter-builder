package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter_Users;
import winterwell.jtwitter.User;

/**
 * Authenticates users to the application and blocks users for the client
 * Uses winterwell external library to access twitter API to obtain access to twitter features such as blocking accounts
 */
public class TwitterBlock {
	
	/**
	 * Blocks users that use a given keyword
	 * @param user The client's authenticated twitter account
	 * @param arr Array containing the users that are going to be blocked
	 */
	public static void blockUsers(Twitter_Users user, ArrayList<String> arr) {
		//Iterating through the array to block users
		for (String s: arr) {
			try {
				user.block(s);	
			}
			//Some users no longer exist, hence this exception
			catch(Exception TwitterException) {
				continue;
			}	
		}
	}
	
	/**
	 * Obtains the list of users already being blocked by the client
	 * @param user The client's authenticated twitter account
	 * @return An ArrayList containing users that are being blocked
	 */
	public static ArrayList<String> getBlocked(Twitter_Users user){
		ArrayList<String> blockedIds = new ArrayList<>();
		List<User> userArr = user.showById(user.getBlockedIds());
		for (User u: userArr) {
			blockedIds.add(u.screenName);
		}
		return blockedIds;
		
	}

	/**
	 * The blocking application
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {


		//Creating an object to initialize the connection to the Twitter account
		OAuthSignpostClient oauthClient = new OAuthSignpostClient(OAuthSignpostClient.JTWITTER_OAUTH_KEY, 
                OAuthSignpostClient.JTWITTER_OAUTH_SECRET, "");
		
		//Opens the authorization page in the user's browser
		oauthClient.authorizeDesktop();
		
		//Prompts user to enter the PIN from the authorization page
		String v = oauthClient.askUser("Please enter the verification PIN from Twitter");
		oauthClient.setAuthorizationCode(v);
		
		//Stores the access token for potential future use
		String[] accessToken = oauthClient.getAccessToken();
		
		//Creating a Twitter object to perform actions with the account
		Twitter twitter = new Twitter("testuser", oauthClient);
		
		//Twitter_Users is the same as the Twitter class, there were too many methods so the
		//API split it up.
		Twitter_Users twitterUser = twitter.users();
		
		//APPLICATION
		
		//Reading the json File
		Reader r = new Reader();
		r.Read();
		
		//Prompting the user to enter a search term
		Search mySearch = new Search(r.tweetInfoArray);
		System.out.println("\n");
		
		//Sorting the array of user that are going to be blocked
		Sort s = new Sort();
		ArrayList<String> toBlock = s.mergeSort(mySearch.getOffendingUsers());
		
		//Displaying users that are going to be blocked to the client
		System.out.println("Users that are going to be blocked:");
		for (String user : toBlock) {
			System.out.println(user);
		}
		System.out.println("\n");
		
		//Prompting user to make a decision whether they want to continue or back out
		String ans = "";
		while (!(ans.equals("1") || ans.equals("2"))) {
			System.out.println("Enter 1 if you would like to block these users. \n"
					+ "Enter 2 if you would like to exit.");
			ans = mySearch.scan.nextLine();	
		}
		mySearch.scan.close();
		
		//Blocking users if they agree
		if (ans.equals("1")) {
			blockUsers(twitterUser, toBlock);
			System.out.println("Those users have been successfully blocked.");
			System.out.println("You are now blocking the following users:");
			ArrayList<String> currentBlock = s.mergeSort(getBlocked(twitterUser));
			for (String usernames: currentBlock) {
				System.out.println(usernames);
			}
			
		}
		//Exiting out of the program if they disagree
		else {System.out.println("Exiting");}
		
		

	}

}
