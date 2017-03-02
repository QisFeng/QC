import javax.swing.JOptionPane;

public class Lab5Program1 {
	
	public static void main(String[] args) {

		String[] myArray = new String[40];
		int subArray = inputFromFile("lab5input.txt", myArray);
		String isOrIsNot, inputWord;

		
		while (true) {

			inputWord = JOptionPane.showInputDialog(null,
					"Enter a word in all lower case:");
			if (inputWord.equalsIgnoreCase("STOP"))
				System.exit(0);
			
			if (wordIsThere(inputWord, myArray,subArray))
				isOrIsNot = "is"; 
			else isOrIsNot = "is not"; 

			
			JOptionPane.showMessageDialog(null, "The word " + inputWord + " "
					+ isOrIsNot + " on the list.");
		}
	} 

	public static int inputFromFile(String filename, String[] myArray) {
		TextFileInput in = new TextFileInput(filename);
		String line = in.readLine();
		int counter = 0;
		while(counter<myArray.length&&line!=null){
			myArray[counter] = line;
			line = in.readLine();
			counter++;
		}
		
		return counter;
	}

	public static boolean wordIsThere(String findMe, String[] theList,int Length) {
		for (int x = 0; x <Length; x++) {
			if (findMe.equals(theList[x]))
				return true;
		}
		return false;

	} // wordIsThere
} 