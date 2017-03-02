
public class Bill extends Money{
   private int dollars;
   
   public Bill (int d) {
	  if (d!=1&&d!=5&&d!=10&&d!=20&&d!=50&&d!=100)
		  throw new IllegalBillException("Invalid Bill: "+d);
      dollars = d;
   }
   public int getValue () {
      return dollars;
   }
   public String toString() {
	      return ("$ "+getValue()+".00");
	   }
}
