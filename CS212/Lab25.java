
public class Lab25 {
	public static void main(String[] args){
		int [] intArray ={3,5,9,4,10,33,12,19,4,16,23,30};
		int[] intArray1 = { 1, 2, 4, 5, 5, 6, 7, 8, 9, 100 };
		
		System.out.println(largest(intArray,0));
		System.out.println(sum(intArray,0));
		System.out.println(isSorted(intArray, 0));
		System.out.println(isSorted(intArray, 8));
		System.out.println(isSorted(intArray1, 0));
	}
	
	
	public static int largest(int[] myArray,int index){
		if (index==myArray.length-1) 
			return myArray[index];
		if (myArray[index] < largest(myArray, index+1))
			return  largest(myArray, index+1);
		else return myArray[index];
	}
	
	public static int sum(int[]myArray, int index){
		if (index==myArray.length-1)
			return myArray[index];
		return myArray[index]+sum(myArray,index+1);
	}
	
	public static boolean isSorted(int []myArray, int index){
		if (index==myArray.length-1) return true;
		if (myArray[index]<=myArray[index+1])
			return isSorted(myArray,index+1);
		return false;
		
	}
}
