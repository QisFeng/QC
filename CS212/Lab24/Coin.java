
public abstract class Coin extends Money {
   private int value;
   
   public Coin(int v) {
      value = v;
   }
   public int getValue () {
      return value;
   }
   public String toString() {
	  
      return ("$ 0."+getValue());
   }
}
