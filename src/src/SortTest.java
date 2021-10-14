package src;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortTest {
	ArrayList<ArrayList<String>> testCases;
	@Before
	public void setUp() throws Exception {
		testCases = new ArrayList<ArrayList<String>>();
		for(int i=0; i < 10; i++) {
			testCases.add(generateRandomStringList());
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMergeSort() {
		Sort s = new Sort();
		for(ArrayList<String> l: testCases) {
			ArrayList<String> tempList = s.mergeSort(l);
			assertTrue(isSorted(tempList));
			
		}
		
	}
	private ArrayList<String> generateRandomStringList(){
		Random r = new Random();
		int numStrings = r.nextInt(21) + 10;
		ArrayList<String> returnList = new ArrayList<String>();
		for (int i = 0; i < numStrings; i++) {
			StringBuilder s = new StringBuilder();
			int stringLength = r.nextInt(6)+10;
			for(int j = 0; j < stringLength; j++) {
				s.append(r.nextInt(26)+'a');
			}
			returnList.add(s.toString());
		}
		return returnList;
	}
	private boolean isSorted(ArrayList<String> l){
		for(int i = 0; i< l.size()-1; i++) {
			if(l.get(i).compareTo(l.get(i+1)) > 0 ) {
				return false;
			}
		}
		return true;
	}

}
