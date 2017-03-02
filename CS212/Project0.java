//Yuqian, Zhang  CS212 LAB SEC11D
import javax.swing.JOptionPane;

public class Project0 {
	public static void main(String[] args){
		String inputWord;
		String []characters;
		while(true){
			
			inputWord = JOptionPane
					.showInputDialog(null,"Enter a string.(Enter STOP to quit)");
			
			if (inputWord.equalsIgnoreCase("STOP"))
				System.exit(0);
			
			
			characters = inputWord.split("");
			int count = 0;
			for (int i =0;i<characters.length-1;i++){
				if (characters[i+1].equals(characters[i])){
				count++;
				}
			}
			
			JOptionPane.showMessageDialog(null,"There are "+ count +" pairs of consecutive letters");
		}
	}
	
	
	
}
