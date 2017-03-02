
public abstract class DateList {
	protected DateNode first = new DateNode(null);
	protected DateNode last = first;
	protected int length = 0;
	
	public int getLength(){
		return length;
	}
	
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
