
public class Question2 {
  
   public static void main(String[] args) {
   int [][] a = {{1,3,3,2},{4,2,5,6},{1,2,3,4},{7,8,9,4}};
   if (diagonalEqualsRow(a,2))
      System.out.println("Yes.");

   }
   public static boolean diagonalEqualsRow(int[][]myArray, int row) {
      if (row >= myArray.length)
         throw new IllegalArgumentException("Row out of range.");
      if (myArray.length != myArray[0].length)
         throw new IllegalArgumentException("Matrix not square.");
      
      int rowSum=0;
      int diagSum=0;
      
      for (int i=0; i<myArray.length;i++) {
         rowSum += myArray[row][i];
         diagSum += myArray[i][i];
      }
      
      return rowSum == diagSum;
      
   }

}
