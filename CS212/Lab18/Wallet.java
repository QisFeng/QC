
public class Wallet {
  private MoneyList walletContents = new MoneyList();
  
  public Wallet(){
	  
  }
  public void addToWallet(Money m){
	  walletContents.append(m);
  }
  public void print () {
	  System.out.println(walletContents.toString());
  }
  
  public String getValue() {
	 String sum =  walletContents.getValue();
	 return sum;
	 
	
	  
  }
}
