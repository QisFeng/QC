
public class BadWalletMain {
   
   static String[] dataSource =  {"B5","Q","B18","Q","B10","P","N","BAD","D","Q"};
   static Wallet myWallet = new Wallet();
   
   public static void main (String[] args) {
           
      for (int i=0;i<dataSource.length;i++){
         char dataItem = dataSource[i].charAt(0);
         if (dataItem == 'B') {
        	// try / catch begins here 
        	try{
            int billValue= Integer.parseInt(dataSource[i].substring(1,dataSource[i].length()));
            myWallet.addToWallet(new Bill(billValue));
        	}
        	catch (IllegalBillException e){
        		System.out.println(e.getMessage());
        	}
        	catch(NumberFormatException f){
        		System.out.println("Not a Bill number: "+dataSource[i]);
        	}
         }   
         else
            if (dataItem == 'Q')
               myWallet.addToWallet(new Quarter());
            else
            if (dataItem == 'D')
               myWallet.addToWallet(new Dime());
            else
            if (dataItem == 'N')
               myWallet.addToWallet(new Nickel());
            else
            if (dataItem == 'P')
               myWallet.addToWallet(new Penny());
         }
      
	  myWallet.print();
	  System.out.println("My wallet contains: $"+myWallet.getValue());
   }
   
   
}
