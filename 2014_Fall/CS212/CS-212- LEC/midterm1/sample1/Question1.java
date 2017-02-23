
public class Question1 {

   /*
    Question 1.
      Write a complete main method that does the following:
      1. Takes 2 command line arguments, both integers.
      2. If there are not 2 arguments, throw an IllegalArgumentException with an appropriate message.
      3. Otherwise, output, to the console, the sum and product of those numbers, each on a different line.
      For example, 
      C:>java Question2 12 5
      12 + 5 = 17
      12 * 5 = 60
    */
   public static void main(String[] args) {
      if (args.length<2)
         throw new IllegalArgumentException("Requires two command line arguments");
      int firstNumber = Integer.parseInt(args[0]);
      int secondNumber = Integer.parseInt(args[1]);
      int sum = firstNumber+secondNumber;
      int product = firstNumber*secondNumber;
      System.out.println(firstNumber+"+"+secondNumber+"="+sum);
      System.out.println(firstNumber+"*"+secondNumber+"="+product);

   }

}
