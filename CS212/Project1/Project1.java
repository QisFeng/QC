//Zhang,Yuqian  SEC 11D
import java.util.*;



public class Project1 {
	public static String [] myArray;
	public static StringTokenizer myTokens;
	public static int size=0;


	public static void main(String[] args){

		myArray = filterArray(args[0]);
		DateGUI myGUI =new DateGUI();
		myGUI.display(myArray);
		SelectionSort(myArray,size);
		myGUI.display2(myArray);

	}//main



	public static void SelectionSort(String[] Array, int length) {
		for(int i=0;i<length-1;i++){
			int indexLowest = i;

			for(int j = i+1;j<length;j++)
				if (Array[j].compareTo(Array[indexLowest])<0)
					indexLowest = j;

			if( indexLowest != i){
				String temp = Array[indexLowest];
				Array[indexLowest]=Array[i];
				Array[i] = temp;
			}	 
		}
	}	//SelectionSort method

	public static boolean isDigit(String s) {
		for(int i=0;i<s.length();i++){
			if(! Character.isDigit(s.charAt(i)))
				return false;		
		}
		return true;
	}//isDigit

	public static boolean isValid(String s){
		if(s.length()!=8 || (!isDigit(s)))
			return false;
		return true;
	}//isValid

	public static String[] filterArray(String myFile) {
		TextFileInput in = new TextFileInput(myFile); 
		String []newArray= new String[100];
		String line = in.readLine();
		int i =0;
		while(line!=null){
			myTokens = new StringTokenizer(line,",");
			while(myTokens.hasMoreTokens()){

				String temp = myTokens.nextToken();
				if (isValid(temp))
					newArray[i++] = temp;
				else System.out.println("Invalid dates:" + temp);

			}
			line = in.readLine();
		}
		size = i;
		return newArray;
	}//fill in and filter the original data


}

