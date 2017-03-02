
import java.awt.GridLayout;

import javax.swing.*;        

public class Lab11 {
    
   public static void main(String[] args) {
        createAndShowGUI();
   }
   
   private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("lab11");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( 100,100);//width, height);
        frame.setLocation(200,200);//x, y);
        frame.setLayout(new GridLayout(2,1));

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane);
        JLabel label = new JLabel("The sum of the numbers in the matrix is: ");
        frame.getContentPane().add(label);

        textArea.setText("The matrix should print here");
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        readAndDisplayMatrix(frame, textArea, label);
    }
   
   private static void readAndDisplayMatrix(JFrame myFrame, JTextArea myText,
                                            JLabel myLabel) {
   
   
   int[][] myMatrix = {{1,2,3,4},{4,5,6,7},{7,8,9,10}};
   String newMatrix = display(myMatrix);
   myText.append(newMatrix);
   int s = sum(myMatrix);
   String sum = Integer.toString(s);
   myLabel.setText("The sum is "+sum);
   
  
   
   
   }

   private static String display(int[][] myMatrix) {
	   String temp = "\n";
   
	   for (int i=0; i<myMatrix.length; i++) {
			for (int j=0; j<myMatrix[i].length;j++){
				temp = temp + myMatrix[i][j];
			}
			temp = temp +"\n";
		}
					
	return temp;
}

     private static int sum(int[][] myMatrix){
    	 int sum = 0;
  	   for (int i=0; i<myMatrix.length; i++) {
  			for (int j=0; j<myMatrix[i].length;j++){
  				sum = sum + myMatrix[i][j];
  			}
  		}
  					
  	return sum;
     }  
}