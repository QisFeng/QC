
public class UnsortedDateList extends DateList{
	/**
	 * creating the add method which append the data
	 * @param d
	 */
	public void add (Date212 d){
		DateNode n = new DateNode(d);
		last.next = n;
		last = n;
		length++;
	}
	
}
