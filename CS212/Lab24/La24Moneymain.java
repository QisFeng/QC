
public class La24Moneymain {
   
   static String[] dataSource =  {"B5","Q","B20","Q","P","D","N"};
   static MoneyList myWallet = new MoneyList();
   
   public static void main (String[] args) {
            
      for (int i=0;i<dataSource.length;i++){
         char dataItem = dataSource[i].charAt(0);
         if (dataItem == 'B') {
            int billValue= Integer.parseInt(dataSource[i].substring(1,dataSource[i].length()));
            myWallet.append(new Bill(billValue));
         }   
         else
            if (dataItem == 'Q')
               myWallet.append(new Quarter());
            else
            if (dataItem == 'D')
               myWallet.append(new Dime());
            else
            if (dataItem == 'N')
               myWallet.append(new Nickel());
            else
            if (dataItem == 'P')
               myWallet.append(new Penny());
         }
      
      myWallet.print();
      System.out.println();
      myWallet.printReverse();
      System.out.println();
      System.out.println( myWallet.sumWallet() );
      System.out.println( myWallet.sumQuarters() );
   }
   
   }

