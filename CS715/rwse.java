// This is Java-like pseudocode, but NOT valid Java
// because the signaling discipline and condition
// variables are wrong.
import Utilities.*;
import Synchronization.*;

class Database extends MyObject {

   private int numReaders = 0;
   private boolean isWriting = false;
   private ConditionVariable OKtoRead = new ConditionVariable();
   private ConditionVariable OKtoWrite = new ConditionVariable();

   public synchronized void startRead(int i) {
      if (isWriting) wait(OKtoRead);
      else if (!empty(OKtoWrite)) {
         wait(OKtoRead); // new incoming readers cannot starve writers
      }
      numReaders++;
      notify(OKtoRead); // when a writer finishes, all waiting readers start
   }
  
   public synchronized void endRead(int i) {
      numReaders--;
      if (numReaders == 0) notify(OKtoWrite);
   }
  
   public synchronized void startWrite(int i) {
      if (numReaders != 0 || isWriting) wait(OKtoWrite);
      isWriting = true;
   }
  
   public synchronized void endWrite(int i) {
      isWriting = false;
      if (!empty(OKtoRead)) notify(OKtoRead);
      else notify(OKtoWrite); // nor do writers starve readers
   }
}
