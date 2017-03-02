public class MoneyList {
	private MoneyNode first = new MoneyNode(null);
	private MoneyNode last = first;
	private int length = 0;

	public int getLength() {
		return length;
	}

	public void append(Money d) {
		MoneyNode n = new MoneyNode(d);
		last.next = n;
		last = n;
		length++;

	} // method append(String)

	public void prepend(Money d) {
		MoneyNode n = new MoneyNode(d);
		if (length == 0)
			last = n;
		n.next = first.next;
		first.next = n;
		length++;

	} // method append(String)
	
	public void print(){
		print(first.next);
	}
	
	public void print(MoneyNode m){
		if(m==null) return;
		System.out.print(m.data+" ");
		print(m.next);
	}

	public void printReverse(){
		printReverse(first.next);
	}
	
	public void printReverse(MoneyNode m){
		if (m==null) return;
		printReverse(m.next);
		System.out.print(m.data+" ");
	}
	
	public double sumWallet(){
		return sumWallet(first.next);
	}
	
	public double sumWallet(MoneyNode m){
		if (m==null) return 0;
		if (m.data instanceof Bill) 
			return ((Bill)m.data).getValue()+sumWallet(m.next);
		else return (((Coin) m.data).getValue()*0.01) + sumWallet(m.next);
				
	}
	
	public double sumQuarters(){
		return sumQuarters(first.next);
	}
	
	public double sumQuarters(MoneyNode m){
		if (m==null) return 0;
		if (m.data instanceof Quarter) 
			return ((Coin)m.data).getValue()*0.01+sumQuarters(m.next);
		return sumQuarters(m.next);
	}
	
	public String toString() {
		MoneyNode p = first.next;
		String returnString = "";
		while (p != null) {
			returnString += p.data + " ";
			p = p.next;
		}
		return returnString;
	}

	/**
	 * Determines whether this LinkedList is equal in value to the parameter
	 * object. They are equal if the parameter is of class LinkedList and the
	 * two objects contain the same short integer values at each index.
	 *
	 * @param other
	 *            the object to be compared to this LinkedList
	 *
	 * @return <code>true</code> if the parameter object is a LinkedList
	 *         containing the same numbers at each index as this LinkedList,
	 *         <code>false</code> otherwise.
	 */
	public boolean equals(Object other) {
		if (other == null || getClass() != other.getClass()
				|| length != ((MoneyList) other).length)
			return false;

		MoneyNode nodeThis = first;
		MoneyNode nodeOther = ((MoneyList) other).first;
		while (nodeThis != null) {
			// Since the two linked lists are the same length,
			// they should reach null on the same iteration.

			if (nodeThis.data != nodeOther.data)
				return false;

			nodeThis = nodeThis.next;
			nodeOther = nodeOther.next;
		} // while

		return true;
	} // method equals

	public String getValue() {
		int bills = 0;
		int coins = 0;
		MoneyNode p = first.next;
		
		while(p!=null) {
			if (p.data instanceof Bill)
				bills += ((Bill) p.data).getValue();
			if(p.data instanceof Coin)
				coins += ((Coin)p.data).getValue();
			p=p.next;
		}
	
		
		if (coins > 100) {
			bills = bills + coins / 100;
			coins = coins % 100;
		}
		return ("$ " + bills + "." + coins);
	}

}
