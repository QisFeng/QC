import java.util.*;
/*
 * This application demonstrates the use of a StringTokenizer.
 * It will open a file, read a line with tokens separated by commas,
 * declare an array whose size is equal to the number of the tokens, 
 * extract each token one by one from the tokenized String and
 * store each string in an array.
 */
public class Tokens {
   public static TextFileInput myFile;
   public static StringTokenizer myTokens;
   public static String animal;
   public static String[] animals;
   public static String line;
   
 
   public static void main(String[] args) {
   /*
    * Open the file and read a line   
    */
      myFile = new TextFileInput("animals.txt");
      line = myFile.readLine();
      System.out.println("The input line is "+line);
   /*
    * Create a new StringTokenizer, passing the String and the delimeter (",")
    */
      myTokens = new StringTokenizer(line,",");
      System.out.println("There are "+myTokens.countTokens()+" tokens in the line.");
   /*
    * Declare an array whose size is equal to the number of tokens found
    * using the method countTokens from the StringTokenizer class
    */
      animals = new String[myTokens.countTokens()];
   /*
    * Get the separated strings one by one from the StringTokenizer and put
    * them in the array   
    */
      int i=0;
      while (myTokens.hasMoreTokens()) {
         animals[i]=myTokens.nextToken();
         i++;
      }
    /*
     * Print the array
     */
      for (int j=0;j<animals.length;j++)
         System.out.println(animals[j]);  
   } //main

} //Tokens
