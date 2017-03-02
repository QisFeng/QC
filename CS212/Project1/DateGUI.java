import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class DateGUI extends JFrame{
	
	private static final long serialVersionUID = 5396173809777104332L;
	private Container cPane;
	private JTextArea unsorted,sorted;

	public DateGUI () {

		setSize(100,100);
		setLocation(200, 200);
		setTitle("Dates");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); 
		cPane = getContentPane();
		setLayout(new GridLayout(1,2));
		unsorted = new JTextArea();
		sorted = new JTextArea();
		cPane.add(unsorted);
		cPane.add(sorted);


		pack();
		this.setVisible(true);

	}

	public void display(String [] theArray ) {
		unsorted.append("original:\n");
		for(int i=1;i<theArray.length;i++){
			if (theArray[i]!=null)
				unsorted.append(theArray[i]+"\n");
		}
	}//call the project1 to put the data in unsorted TextArea

	public void display2(String [] theArray){
		sorted.append("sorted:\n");
		for(int i=1;i<theArray.length;i++){
			if (theArray[i]!=null)
				sorted.append(theArray[i]+"\n");
		}
	}//call the project1 to put the data in sorted TextArea
}


