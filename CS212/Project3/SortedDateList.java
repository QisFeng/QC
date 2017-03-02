
public class SortedDateList extends DateList{
	/**
	 * creating add method which insert the data
	 * @param d
	 */
	public void add(Date212 d){
		DateNode n = new DateNode(d);
		DateNode previous = first;
		DateNode current=first.next;
		while(current!=null&&n.data.compareTo(current.data)>0){
		   previous=previous.next;
		   current=current.next;
		}
		previous.next = n;
		n.next=current;
		length++;
		
	}
	
}
