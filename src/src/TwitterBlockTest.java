package src;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter_Users;

public class TwitterBlockTest {
	private Twitter twitter;
	private Twitter_Users twitterUser;
	private OAuthSignpostClient oauthClient;

	@Before
	public void setUp() throws Exception {
		oauthClient = new OAuthSignpostClient(OAuthSignpostClient.JTWITTER_OAUTH_KEY, 
                OAuthSignpostClient.JTWITTER_OAUTH_SECRET, "",
                "");
		twitter = new Twitter("testuser", oauthClient);
		twitterUser = twitter.users();
	}

	@After
	public void tearDown() throws Exception {
		twitter = null;
		twitterUser = null;
		oauthClient = null;
	}

	@Test
	public void testBlockUsers() {
		ArrayList<String> temp = new ArrayList<>();
		temp.add("bbcnews");
		TwitterBlock.blockUsers(twitterUser, temp);
		assertTrue(twitterUser.showById(twitterUser.getBlockedIds()).get(0).toString().equals("bbcnews"));
	}

	@Test
	public void testGetBlocked() {
		ArrayList<String> temp = new ArrayList<>();
		temp.add("bbcnews");
		twitterUser.block("bbcnews");
		ArrayList<String> blocked = TwitterBlock.getBlocked(twitterUser);
		assertTrue(blocked.equals(temp));
	}

}
