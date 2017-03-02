import javax.swing.*;
import java.awt.*;
public class Lab12{

	static TextFileInput inFile;
	static String inFileName = "numbers.txt";
	static JFrame myFrame;
	static Container cPane;
	static JTextArea even, odd;

	public static void main(String[] args) {
		initialize();
		readNumbersFromFile(inFileName);

	}   
	public static void initialize() {
		inFile = new TextFileInput(inFileName);
		even = new JTextArea();
		odd = new JTextArea();
		myFrame=new JFrame();
		myFrame.setSize(400,400);
		myFrame.setLocation(200, 200);
		myFrame.setTitle(inFileName);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cPane = myFrame.getContentPane();
		myFrame.setLayout(new GridLayout(1,2));
		cPane.add(even);
		cPane.add(odd);
		myFrame.pack();
		myFrame.setVisible(true);
	}
	public static void readNumbersFromFile(String fileName){
		
		
		String line;

		line = inFile.readLine();
		while (line != null) {
			int n = Integer.parseInt(line);
			if (n%2==0)
				even.append(line+"\n");
			else 
				odd.append(line+"\n");
			line = inFile.readLine();
		}
		
	} //while
} //readSSNsFromFile




   
 //SSN

