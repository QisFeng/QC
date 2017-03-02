import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class DateGUI extends JFrame{
	
	private static final long serialVersionUID = 5396173809777104332L;
	private Container cPane;
	private JTextArea unsorted,sorted;
	
	/**
	 * create a constructor which initialize a new JFrame 
	 * and set up TextArea as required
	 */
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

	/**
	 * call the project2 to put the data in unsorted TextArea
	 */
	public void display(String d ) {
		unsorted.append("original:\n");
		unsorted.append(d);
	}
	
	/**
	 * call the project2 to put the data in sorted TextArea
	 */
	public void display2(String d ){
		sorted.append("sorted:\n");
		sorted.append(d);
	}

}



