import java.util.StringTokenizer;




/**
 * @author Yuqian Zhang Section 11D
 *
 */
public class Project2 {
	/**
	 * declaring a StringTokenizer
	 * declare DateList list1&list2 and initialize them
	 */
	public static StringTokenizer myTokens;
	static DateList list1 = new DateList();
	static DateList list2 = new DateList();
	
	/**
	 * fill in the data
	 * calling DateGUI to display two lists
	 */
	public static void main(String[] args){
		
		fillin("project2Input.txt");
		DateGUI myGUI =new DateGUI();
		myGUI.display(list1.toString());
		myGUI.display2(list2.toString());
		
		
	}
	
	/**
	 * fill in the original data 
	 * using StringTokenizer to filler the original data
	 * transfer the data type from String to Date212
	 * append transferred data to list1
	 * insert transferred data to list2 
	 * 
	 * 
	 */
	public static void fillin(String myFile) {
		TextFileInput in = new TextFileInput(myFile); 
		String line = in.readLine();
		while(line!=null){
			myTokens = new StringTokenizer(line,",");
			while(myTokens.hasMoreTokens()){
				String temp = myTokens.nextToken();
				Date212 tempD = new Date212(temp);
				list1.append(tempD);
				list2.insert(tempD);
			}
				line = in.readLine();
		}
		
	}
	
	
}
