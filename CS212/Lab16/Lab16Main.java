
public class Lab16Main {

	public static void main(String[] args) {
		
		String [] dataSource = {"cat","bat","rat","sat","hat","fat"};
		//generate empty lists
		LinkedList myList1= new LinkedList();
		LinkedList myList2= new LinkedList();
		
		//print out the empty lists
		System.out.println("Here is myList1: "+myList1);
		System.out.println("Here is myList2: "+myList2);
		
		// fill list 1 using append and print it.
		for (int i=0; i<dataSource.length;i++)
			myList1.append(dataSource[i]);
		System.out.println("Here is myList1: "+myList1);
		
		//fill list 2 using prepend and print it.
		for (int i=0; i<dataSource.length;i++)
			myList2.prepend(dataSource[i]);
		System.out.println("Here is myList2: "+myList2);
	}
}


