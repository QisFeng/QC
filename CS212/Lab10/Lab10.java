/***************************************************************
 * 
 * @author K. Lord
 * A program that works with two-dimension arrays.
 *
 */
public class Lab10 {
  private static int[][] matrix;
   
   public static void main(String[] args){
      matrix = new int[6][4];
      fillMatrix(matrix, 5);
      System.out.println(sumMatrix(matrix));
   }
   /**
    * Each cell of an integer matrix is filled with
    * the given value.
    *  
    * @param m The matrix to be filled
    * @param v The value to fill in each cell
    */
   public static void fillMatrix(int[][] m, int v){
      for (int i=0;i<m.length;i++)
         for (int j=0; j<m[i].length; j++)
            m[i][j]=v;
   }
   
   public static void fillMatrixSeq(int[][] m, int v){
      int seqValue=0;
      for (int i=0;i<m.length;i++)
         for (int j=0; j<m[i].length; j++)
            m[i][j]=seqValue++;
   }
   
   public static int sumMatrix(int[][] m){
      int sum = 0;
      for (int i=0;i<m.length;i++)
         for (int j=0; j<m[i].length; j++)
            sum += m[i][j];
      return sum;
   }
   
   public static int sumMatrixRow (int[][] m){
      int sum = 0;
      for (int i=0;i<m.length;i++)
         for (int j=0; j<m[i].length; j++)
            sum += m[i][j];
      return sum;
   }
}
