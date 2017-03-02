import javax.swing.JOptionPane;

public class Lab6Program1 {
	public static int[] myArray = new int[15];
	public static void main(String[] args) {

		int subArraylength = inputFromFile("lab6input.txt", myArray);
		int sum = sum(myArray,subArraylength);
		int avg = avg(myArray,subArraylength);
		JOptionPane.showMessageDialog(null, "The sum is " + sum);
		JOptionPane.showMessageDialog(null, "The avg is " + avg);
	}
		public static int sum (int[] myArray, int myArraySize) {
			int sum = 0;
			for(int i=0;i<myArray.length;i++){
				sum = sum+ myArray[i];
		}
		return sum;
		}		
		
		private static int avg(int[] myArray, int myArraySize) {
		int sum = sum(myArray,myArraySize);
		int avg = sum/myArraySize;
			
		return avg;
	}


		public static int inputFromFile(String filename, int[] myArray) {
			TextFileInput in = new TextFileInput(filename);
			String line = in.readLine();
			int counter = 0;
			while(counter<myArray.length&&line!=null){
				myArray[counter] = Integer.parseInt(line);
				line = in.readLine();
				counter++;
			}
			if(line!=null){
				System.out.println("File contains too many numbers.");
				System.out.println("This program can only process"+myArray.length+"numbers.");
				System.exit(1);
			}
			in.close();
			return counter;
		}
		
		
} 

