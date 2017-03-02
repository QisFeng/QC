
import java.io.*;

public class ReadMoney {

  public static void main(String argv[]) throws Exception {
    FileInputStream fis = new FileInputStream("money.out");
    ObjectInputStream ois = new ObjectInputStream(fis);
    Money money = (Money) ois.readObject();
    System.out.println("The money is: "+ money);
    ois.close();
    fis.close();
  }
}