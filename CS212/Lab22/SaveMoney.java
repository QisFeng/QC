
import java.io.*;

public class SaveMoney {

  public static void main(String argv[]) throws Exception {
    FileOutputStream fos = new FileOutputStream("money.out");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    Money money = new Quarter();
    oos.writeObject(money);
    oos.flush();
    oos.close();
    fos.close();
  }
}