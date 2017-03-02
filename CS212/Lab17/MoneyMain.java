
public class MoneyMain {
   
   static String[] dataSource =  {"B5","Q","B20","Q","N","D","P"};
   static Money[] wallet = new Money[dataSource.length];
   
   public static void main (String[] args) {
            
      for (int i=0;i<dataSource.length;i++){
         char dataItem = dataSource[i].charAt(0);
         if (dataItem == 'B') {
            int billValue= Integer.parseInt(dataSource[i].substring(1,dataSource[i].length()));
            wallet[i]= new Bill(billValue);
         }   
         if (dataItem == 'Q')
            wallet[i]=new Quarter();
         
      	 if (dataItem =='N')
      		wallet[i]=new Nickel();
      	
      	 if (dataItem =='D')
      		wallet[i]=new Dime();
      	 
      	 if (dataItem =='P')
      		wallet[i]=new Penny();
   }
   
      printWallet();
      sumWallet();
   }
   public static void printWallet () {
      for (int i=0;i<wallet.length;i++) 
            System.out.println(wallet[i]);
   }
   
   public static void sumWallet() {
	   int bills = 0;
	   int coins = 0;
	   for (int i=0;i<wallet.length;i++){
		   if (wallet[i]instanceof Bill)
			   bills+=((Bill)wallet[i]).getValue();
		   else
			   coins += ((Coin)wallet[i]).getValue();
	   }
	   if (coins>100){
		   bills = bills+coins/100;
	   		coins = coins%100;
	   }
	   System.out.println("$ "+bills+"."+coins);
   }
}

