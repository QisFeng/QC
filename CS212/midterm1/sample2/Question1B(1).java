import javax.swing.*;
public class Question1 {

   public static void main (String[] args) {
      String line= JOptionPane.showInputDialog(null,"Enter a string.");
      
      int doubleCount=0;
      for (int i=0; i< line.length()-1;i++) {
         if( Character.isDigit(line.charAt(i)) && Character.isDigit(line.charAt(i+1))) doubleCount++;
      }
      JOptionPane.showMessageDialog (null,"There are "+doubleCount  + "pairs");
           
   }
}
