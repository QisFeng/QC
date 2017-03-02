
public class Bill extends Money{
   private int dollars;
   
   public Bill (int d) {
      dollars = d;
   }
   public int getValue () {
      return dollars;
   }
   public String toString() {
	      return ("$ "+getValue()+".00");
	   }
}
