import java.util.Vector;
import Utilities.*;

class Database extends MyObject {

   private int numReaders = 0;
   private boolean isWriting = false;
   private Vector waitingReaders = new Vector();
   private Vector waitingWriters = new Vector();

   public Database() { super("rwDB"); }

   public synchronized String toString() {
      return getName() + " numR=" + numReaders
         + " numRw=" + waitingReaders.size() + " isW="
         + isWriting + " numWw=" + waitingWriters.size();
   }

   public void startRead(int i) {
      Object convey = new Object();
      synchronized (convey) {
         if (cannotReadNow(convey))
            while (true) // wait to be notified, not interrupted
               try { convey.wait(); break; }
               // notify() after interrupt() race condition ignored
               catch (InterruptedException e) { continue; }
      }
   }

   private synchronized boolean cannotReadNow(Object convey) {
      boolean status;
      System.out.println("  cannotR <: " + this);
      if (isWriting || waitingWriters.size() > 0) {
         waitingReaders.addElement(convey);
         status = true;
      } else {
         numReaders++;
         status = false;
      }
      System.out.println("  cannotR >: " + this);
      return status;
   }

   public void startWrite(int i) {
      Object convey = new Object();
      synchronized (convey) {
         if (cannotWriteNow(convey))
            while (true) // wait to be notified, not interrupted
               try { convey.wait(); break; }
               catch (InterruptedException e) { continue; }
      }
   }

   private synchronized boolean cannotWriteNow(Object convey) {
      boolean status;
      System.out.println("  cannotW <: " + this);
      if (isWriting || numReaders > 0) {
         waitingWriters.addElement(convey);
         status = true;
      } else {
         isWriting = true;
         status = false;
      }
      System.out.println("  cannotW >: " + this);
      return status;
   }

   public synchronized void endRead(int i) {
      System.out.println("  endR <: " + this);
      numReaders--;
      if (numReaders == 0 && waitingWriters.size() > 0) {
         synchronized (waitingWriters.elementAt(0)) {
            waitingWriters.elementAt(0).notify();
         }
         waitingWriters.removeElementAt(0);
         isWriting = true;
      }
      System.out.println("  endR >: " + this);
   }
  
   public synchronized void endWrite(int i) {
      System.out.println("  endW <: " + this);
      isWriting = false;
      if (waitingReaders.size() > 0) {
         while (waitingReaders.size() > 0) {
            synchronized (waitingReaders.elementAt(0)) {
               waitingReaders.elementAt(0).notify();
            }
            waitingReaders.removeElementAt(0);
            numReaders++;
         }
      } else if (waitingWriters.size() > 0) {
         synchronized (waitingWriters.elementAt(0)) {
            waitingWriters.elementAt(0).notify();
         }
         waitingWriters.removeElementAt(0);
         isWriting = true;
      }
      System.out.println("  endW >: " + this);
   }
}

/* ............... Example compile and run(s)

D:\>javac rwcv.java rwdr.java

D:\>java ReadersWriters -R10
ReadersWriters: numReaders=3, numWriters=2, rNap=2, wNap=3, runTime=10
All threads started
age=60, Reader0 napping for 1697 ms
age=60, Reader1 napping for 980 ms
age=60, Reader2 napping for 1340 ms
age=60, WRITER0 napping for 687 ms
age=60, WRITER1 napping for 1524 ms
age=770, WRITER0 wants to write
  cannotW <: rwDB numR=0 numRw=0 isW=false numWw=0
  cannotW >: rwDB numR=0 numRw=0 isW=true numWw=0
age=770, WRITER0 writing for 888 ms
age=1050, Reader1 wants to read
  cannotR <: rwDB numR=0 numRw=0 isW=true numWw=0
  cannotR >: rwDB numR=0 numRw=1 isW=true numWw=0
age=1430, Reader2 wants to read
  cannotR <: rwDB numR=0 numRw=1 isW=true numWw=0
  cannotR >: rwDB numR=0 numRw=2 isW=true numWw=0
age=1600, WRITER1 wants to write
  cannotW <: rwDB numR=0 numRw=2 isW=true numWw=0
  cannotW >: rwDB numR=0 numRw=2 isW=true numWw=1
  endW <: rwDB numR=0 numRw=2 isW=true numWw=1
  endW >: rwDB numR=2 numRw=0 isW=false numWw=1
age=1650, WRITER0 finished writing
age=1650, WRITER0 napping for 867 ms
age=1650, Reader1 reading for 80 ms
age=1650, Reader2 reading for 1541 ms
  endR <: rwDB numR=2 numRw=0 isW=false numWw=1
  endR >: rwDB numR=1 numRw=0 isW=false numWw=1
age=1760, Reader1 finished reading
age=1760, Reader0 wants to read
  cannotR <: rwDB numR=1 numRw=0 isW=false numWw=1
  cannotR >: rwDB numR=1 numRw=1 isW=false numWw=1
age=1760, Reader1 napping for 1885 ms
age=2530, WRITER0 wants to write
  cannotW <: rwDB numR=1 numRw=1 isW=false numWw=1
  cannotW >: rwDB numR=1 numRw=1 isW=false numWw=2
  endR <: rwDB numR=1 numRw=1 isW=false numWw=2
  endR >: rwDB numR=0 numRw=1 isW=true numWw=1
age=3240, Reader2 finished reading
age=3240, Reader2 napping for 880 ms
age=3240, WRITER1 writing for 109 ms
  endW <: rwDB numR=0 numRw=1 isW=true numWw=1
  endW >: rwDB numR=1 numRw=0 isW=false numWw=1
age=3350, WRITER1 finished writing
age=3350, WRITER1 napping for 1310 ms
age=3350, Reader0 reading for 442 ms
age=3680, Reader1 wants to read
  cannotR <: rwDB numR=1 numRw=0 isW=false numWw=1
  cannotR >: rwDB numR=1 numRw=1 isW=false numWw=1
  endR <: rwDB numR=1 numRw=1 isW=false numWw=1
  endR >: rwDB numR=0 numRw=1 isW=true numWw=0
age=3790, Reader0 finished reading
age=3790, Reader0 napping for 1559 ms
age=3790, WRITER0 writing for 2087 ms
age=4180, Reader2 wants to read
  cannotR <: rwDB numR=0 numRw=1 isW=true numWw=0
  cannotR >: rwDB numR=0 numRw=2 isW=true numWw=0
age=4670, WRITER1 wants to write
  cannotW <: rwDB numR=0 numRw=2 isW=true numWw=0
  cannotW >: rwDB numR=0 numRw=2 isW=true numWw=1
age=5390, Reader0 wants to read
  cannotR <: rwDB numR=0 numRw=2 isW=true numWw=1
  cannotR >: rwDB numR=0 numRw=3 isW=true numWw=1
  endW <: rwDB numR=0 numRw=3 isW=true numWw=1
  endW >: rwDB numR=3 numRw=0 isW=false numWw=1
age=5930, WRITER0 finished writing
age=5930, WRITER0 napping for 2330 ms
age=5930, Reader1 reading for 1996 ms
age=5930, Reader2 reading for 1915 ms
age=5930, Reader0 reading for 1873 ms
  endR <: rwDB numR=3 numRw=0 isW=false numWw=1
  endR >: rwDB numR=2 numRw=0 isW=false numWw=1
age=7800, Reader0 finished reading
age=7800, Reader0 napping for 542 ms
  endR <: rwDB numR=2 numRw=0 isW=false numWw=1
  endR >: rwDB numR=1 numRw=0 isW=false numWw=1
age=7860, Reader2 finished reading
age=7860, Reader2 napping for 387 ms
  endR <: rwDB numR=1 numRw=0 isW=false numWw=1
  endR >: rwDB numR=0 numRw=0 isW=true numWw=0
age=7910, Reader1 finished reading
age=7910, Reader1 napping for 626 ms
age=7910, WRITER1 writing for 2221 ms
age=8240, Reader2 wants to read
  cannotR <: rwDB numR=0 numRw=0 isW=true numWw=0
  cannotR >: rwDB numR=0 numRw=1 isW=true numWw=0
age=8240, WRITER0 wants to write
  cannotW <: rwDB numR=0 numRw=1 isW=true numWw=0
  cannotW >: rwDB numR=0 numRw=1 isW=true numWw=1
age=8350, Reader0 wants to read
  cannotR <: rwDB numR=0 numRw=1 isW=true numWw=1
  cannotR >: rwDB numR=0 numRw=2 isW=true numWw=1
age=8570, Reader1 wants to read
  cannotR <: rwDB numR=0 numRw=2 isW=true numWw=1
  cannotR >: rwDB numR=0 numRw=3 isW=true numWw=1
age()=10050, time to stop the threads and exit
                                            ... end of example run(s)  */
