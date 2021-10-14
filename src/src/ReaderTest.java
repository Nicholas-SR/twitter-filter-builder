package src;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReaderTest {

	static Reader r;

	
	@BeforeClass
	public static void setUp() throws Exception {
		r = new Reader();
		r.Read();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testReadFindBioNull() throws IOException {
		assertTrue(r.findBio("NOTAUSER234234").equals("Username not found")); 
	}
	


	@Test
	public void testReadFindTweetNull() throws IOException {
		assertTrue(r.findTweet("NOTAUSER234234").equals("Username not found"));
	}

}
