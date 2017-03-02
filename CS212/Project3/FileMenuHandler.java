import javax.swing.*;


import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class FileMenuHandler implements ActionListener {
	DateGUI  jframe;
	/**
	 * declaring a StringTokenizer
	 * declare UnsortedDateList list1
	 * SortedDatelist&list2 and initialize them
	 */
	private StringTokenizer myTokens;
	private UnsortedDateList list1 = new UnsortedDateList();
	private SortedDateList list2 = new SortedDateList();
	public FileMenuHandler (DateGUI jf) {
		jframe = jf;
	}
	/**
	 * When click on "open",GUI is able to allow user to select input file by JFilechooser
	 * When click on "quit",it exits
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName = event.getActionCommand();
		if (menuName.equals("Open")){
			JFileChooser fd = new JFileChooser();
			fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			fillin(f);
		}
		else if (menuName.equals("Quit"))
			System.exit(0);
	} //actionPerformed
	
	/**
	 * fill in the original data 
	 * using StringTokenizer to fill in the original data
	 * and using IllegalDate212Exception to throw exceptions in the console
	 * transfer the data type from String to Date212
	 * append transferred data to list1
	 * insert transferred data to list2 
	 * 
	 * 
	 */
	public void fillin(File myFile){
		String Filepath = myFile.getAbsolutePath();
		TextFileInput in = new TextFileInput(Filepath); 
		String line = in.readLine();

		while(line!=null){
			myTokens = new StringTokenizer(line,",");

			while(myTokens.hasMoreTokens()){
				try{
					String temp = myTokens.nextToken();
					Date212 tempD = new Date212(temp);
					list1.add(tempD);
					list2.add(tempD);	
				}
				catch (IllegalDate212Exception e) {
					System.out.println("Invalid Date:"+e.getMessage());
				}

			}	
			line = in.readLine();
		}
		in.close();
		jframe.display(list1.toString());
		jframe.display2(list2.toString());
	}
}