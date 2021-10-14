package src;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.*;


/**
 * Class that reads information from a CSV file
 * Uses the Gson library to manage the json objects in the dataset
 * Uses FileInputStream library to efficiently read the json file
 * Uses IO Exception library to support the FileInputStream library
 */
public class Reader{
	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		r.Read();
	}
	
	ArrayList<TweetInfo> tweetInfoArray = new ArrayList<>();
	
	/**
	 * Method that reads the information from a CSV file and adds it to an ArrayList of TweetInfo tweetInfoArray
	 * Uses the Gson library to manage the json objects in the dataset
	 * @throws IOException
	 * 
	 */
	
	public void  Read() throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		try {
		    inputStream = new FileInputStream("data/tweets2k.json");
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();

		        if (line.substring(2,8).equals("delete")) {
		        	continue;
		        }
		    	/**
		    	 * Gets the json object from the dataset 
		    	 * 
		    	 */
		        JsonObject obj = new Gson().fromJson(line, JsonObject.class);
		        String username;
		        String tweet;
		        String bio;
		        
		    	/**
		    	 * Gets the nested json field for the username, accounts for invalid or deleted tweets
		    	 * 
		    	 */
		        try {
		        	username = obj.getAsJsonObject("user").get("screen_name").getAsString();

		        }
		        catch(Exception NullPointerException) {
		        	username = "";
		        }
		        
		    	/**
		    	 * Gets the nested json field for the tweet, accounts for invalid or deleted tweets
		    	 * 
		    	 */
		        try {
		        	tweet = obj.get("text").getAsString();

		        }
		        catch(Exception NullPointerException) {
		        	tweet = "";
		        }
		        
		    	/**
		    	 * Gets the nested json field for the bio, accounts for invalid or deleted tweets
		    	 * 
		    	 */
		        try {
		        	bio = obj.getAsJsonObject("user").get("description").getAsString();
		        }
		        catch(Exception NullPointerException) {
		        	bio = "";	
		        }
		        
				TweetInfo info = new TweetInfo(username, tweet, bio);
		        tweetInfoArray.add(info);

		    }
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
	}
	
	/**
	 * Method to find a tweet in the generated tweetInfoArray
	 * @param username The username to search
	 * @return getTweet the tweet matching the username
	 *
	 */
	
	public String findTweet(String username) {
		for (TweetInfo info : tweetInfoArray) {
			if (info.getUsername().equalsIgnoreCase(username)) {
				return info.getTweet();
			}
		}
		return "Username not found";
	}
	
	/**
	 * Method to find a bio in the generated tweetInfoArray
	 * @param username The username to search
	 * @return getBio the biography matching the username
	 *
	 */
	
	public String findBio(String username) {
		for (TweetInfo info : tweetInfoArray) {
			if (info.getUsername().equalsIgnoreCase(username)) {
				return info.getBio();
			}
		}
		return "Username not found";
	}
}
