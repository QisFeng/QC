import java.io.File;
import javax.swing.*;
public class ListLargestFiles {
	private static File maxFile;
	private static long maxSize;

	public static void main(String[] args) {
		JFileChooser fd = new JFileChooser();
		//        mode - the type of files to be displayed:
		//            * JFileChooser.FILES_ONLY
		//            * JFileChooser.DIRECTORIES_ONLY
		//            * JFileChooser.FILES_AND_DIRECTORIES 
		fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fd.showOpenDialog(null);
		File f = fd.getSelectedFile();
		maxFile = f;
		maxSize = 0;
		listFiles(f,"");

	}
	public static void listFiles(File f, String indent) {
		File files[] = f.listFiles();

		for (int i = 0; i<files.length; i++) {
			File f2 = files[i];
			System.out.print(indent+f2.getName());
			System.out.print("...");
			System.out.print(f2.lastModified());
			System.out.print("...");
			System.out.print(f2.length());
			if (f2.length()>maxSize){
				maxSize = f2.length();
				maxFile = files[i];
			}
			System.out.println();
			if (f2.isDirectory())
				listFiles(f2, indent+"   ");
		}
		System.out.println("Largest Files");
		System.out.print(maxFile.getName());
		System.out.println("..."+maxFile.length());

	}
}
