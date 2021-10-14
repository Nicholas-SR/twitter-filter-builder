package src;
import java.util.ArrayList;


/**
 * Class to sort elements in an ArrayList
 *
 */


public class Sort {
	
	/**
	 * This method is the constructor for a mergeSort instance
	 * @param elements an ArrayList of string elements to be sorted
	 */
	
	
	public ArrayList<String> mergeSort(ArrayList<String> elements){
		
		if(elements.size()<2) {
			return elements;
		}
		else {
			int m = elements.size()/2;
			ArrayList<String> l = getArraySubList(elements, 0, m);
			ArrayList<String> r = getArraySubList(elements, m, elements.size());
			return merge(mergeSort(l), mergeSort(r));
		}
	}
	
	/**
	 * This method is a private method to merge two ArrayLists
	 * @param l the first (left) ArrayList to merge
	 * @param r the second (right) ArrayList to merge
	 */
	
	private ArrayList<String> merge(ArrayList<String> l, ArrayList<String> r){
		ArrayList<String> newList = new ArrayList<String>();
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex < l.size() && rightIndex < r.size()) {
			if (l.get(leftIndex).toLowerCase().compareTo(r.get(rightIndex).toLowerCase()) <= 0) {
				newList.add(l.get(leftIndex));
				leftIndex++;
			}
			else {
				newList.add(r.get(rightIndex));
				rightIndex++;
			}
			if (leftIndex >= l.size()) {
				for(int i = rightIndex; i < r.size(); i++) {
					newList.add(r.get(i));
				}
			}
			if (rightIndex >= r.size()) {
				for(int i = leftIndex; i < l.size(); i++) {
					newList.add(l.get(i));
				}
			}
		}
		return newList;
	}
	
	/**
	 * This method is used to retrieve a sublist in an ArrayList
	 * @param elements The ArrayList we traverse
	 * @param beginIndex The index in the ArrayList that will be the first element in the sublist
	 * @param endIndex The index in the ArrayList that will be the last element in the sublist
	 */
	
	private ArrayList<String> getArraySubList(ArrayList<String> elements, int beginIndex, int endIndex){
		ArrayList<String> newList = new ArrayList<String>();
		for (int i = beginIndex; i < endIndex; i++) {
			newList.add(elements.get(i));
		}
		return newList;
		
	}
}
