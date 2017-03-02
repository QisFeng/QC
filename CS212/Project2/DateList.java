
public class DateList {
	private DateNode first = new DateNode(null);
	private DateNode last = first;
	private int length = 0;
	
	public int getLength(){
		return length;
	}
	
	/**
	 * creating the append method 
	 * which append the data
	 */
	public void append(Date212 d){
		DateNode n = new DateNode(d);
		last.next = n;
		last = n;
		length++;
	}
	
	/**
	 * creating the insert method
	 * which insert the data by 
	 * first, initialize the DateNode
	 * then, traversing to find the spot when meet the condition
	 * at last, joining in the DateList
	 * 
	 */
	public void insert(Date212 d){
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
	
	/**
	 * creating toString method for display
	 */
	public String toString() {
	      DateNode p = first.next;
	      String returnString = "";
	      while (p != null) {
	         returnString += p.data+"\n";
			 p = p.next;
	      }
	      return returnString;
	   }
		
}
