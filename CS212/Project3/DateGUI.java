import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		setTitle("Date List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createFileMenu();
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
	 * call the project3 to put the data in unsorted TextArea
	 */
	public void display(String d ) {
		unsorted.append("original:\n");
		unsorted.append(d);
	}
	
	/**
	 * call the project3 to put the data in sorted TextArea
	 */
	public void display2(String d ){
		sorted.append("sorted:\n");
		sorted.append(d);
	}
	/**
	 * create FileMenu Bar and the FileMenu
	 * calling FileMenuHandler
	 */
	private void createFileMenu( ) {
	      JMenuItem   item;
	      JMenuBar    menuBar  = new JMenuBar();
	      JMenu       fileMenu = new JMenu("File");
	      FileMenuHandler fmh  = new FileMenuHandler(this);
	      
	      
	      item = new JMenuItem("Open");    
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      fileMenu.addSeparator();           
	    
	      item = new JMenuItem("Quit");       
	      item.addActionListener( fmh );
	      fileMenu.add( item );
	      
	      

	      setJMenuBar(menuBar);
	      menuBar.add(fileMenu);
	      
	   } 
	
}



