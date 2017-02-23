import javax.swing.*;
public class Question3 {
/*
 * Question 3.
      Write a Java program that will read a String using a JOptionPane, count the number of characters that are 
      digits, and display that number in another JOptionPane.  This may make use of the method in the Character 
      wrapper class called isDigit().
       */
   public static void main(String args[]){
      String inputLine = JOptionPane.showInputDialog(null,"Enter a string");
      int sum = 0;
      for (int i=0; i<inputLine.length(); i++)
         if(Character.isDigit(inputLine.charAt(i)))
            sum++;
      JOptionPane.showMessageDialog(null, "There are "+sum+" digits.");
      
      
   }
}
