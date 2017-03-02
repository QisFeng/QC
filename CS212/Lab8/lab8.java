import java.util.*;


public class lab8 {

	public static int[][] myArray;
	public static StringTokenizer myTokens;
	
	public static void main(String[] args) {
		myArray = fillArray("twodimension8.txt");
		
		printArray(myArray);
		
	}
	private static void printArray (int[][] theArray) {
		for (int i=0; i<theArray.length; i++) {
			for (int j=0; j<theArray[i].length;j++)
				display(theArray[i][j]);
			System.out.println();
		}
	}
	private static void display (int num){
					System.out.print(num+" ");
	}
	
	
	
	public static int[][] fillArray(String myFile){
		TextFileInput in = new TextFileInput(myFile);
		String line = in.readLine();
		myTokens = new StringTokenizer(line,",");
		int r = Integer.parseInt(myTokens.nextToken());
		int c = Integer.parseInt(myTokens.nextToken());
		int[][] newArray = new int[r][c];
		
		
		for (int i=0;i<newArray.length;i++){
			line = in.readLine();
			myTokens = new StringTokenizer(line,",");
			for(int j=0;j<newArray[i].length;j++) {
			newArray[i][j] = Integer.parseInt(myTokens.nextToken());
			}				
		}
				
		return newArray;
	}
	
	
}
