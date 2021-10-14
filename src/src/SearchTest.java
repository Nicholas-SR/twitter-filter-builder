package src;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchTest {
	private TweetInfo t1;
	private TweetInfo t2;
	private TweetInfo t3;
	private ArrayList<TweetInfo> testTweets;

	@Before
	public void setUp() throws Exception {
		t1 = new TweetInfo("user1", "hello world", "city place");
		t2 = new TweetInfo("user2", "machine", "city place");
		t3 = new TweetInfo("user3", "MACHINE", "place");

		
		testTweets = new ArrayList<TweetInfo>();
		
		testTweets.add(t1);
		testTweets.add(t2);
		testTweets.add(t3);


	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test Complete");
	}

	@Test
	public void testSearch() {
		String keyword = "hello";
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("user1");

		
		InputStream sysInBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(keyword.getBytes());
		System.setIn(in);
		
		Search mySearch = new Search(testTweets);
		
		assertTrue(mySearch.getOffendingUsers().equals(ans));
		
		System.setIn(sysInBackup);
	}

	@Test
	public void testGetKeyword() {
		String keyword1 = "place";
		String keyword2 = "city";

		InputStream sysInBackup = System.in;
		ByteArrayInputStream in1 = new ByteArrayInputStream(keyword1.getBytes());
		ByteArrayInputStream in2 = new ByteArrayInputStream(keyword2.getBytes());

		System.setIn(in1);
		System.setIn(in2);
		
		Search mySearch = new Search(testTweets);
		mySearch.getKeyword();
		
		assertTrue(mySearch.keyword.equals("city"));
		
		System.setIn(sysInBackup);
	}

	@Test
	public void testSearchAll() {
		String keyword1 = "machine";

		ArrayList<String> ans1 = new ArrayList<String>();
		ans1.add("user2");
		ans1.add("user3");
		
		InputStream sysInBackup = System.in;
		ByteArrayInputStream in1 = new ByteArrayInputStream(keyword1.getBytes());
		System.setIn(in1);
		
		Search mySearch1 = new Search(testTweets);	
		
		mySearch1.searchAll();
		assertTrue(mySearch1.getOffendingUsers().equals(ans1));
		
		String keyword2 = "coronavirus";
		
		ByteArrayInputStream in2 = new ByteArrayInputStream(keyword2.getBytes());
		System.setIn(in2);
		
		Search mySearch2 = new Search(testTweets);	
		
		mySearch1.searchAll();
		assertTrue(mySearch2.getOffendingUsers().size() == 0);


		System.setIn(sysInBackup);
	}

}
