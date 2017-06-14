/*
 * Fair to both readers and writers in the sense that as
 * soon as a writer arrives (while the database is being
 * read, say) then no more newly arriving readers are allowed
 * to start reading.  Instead the newly arriving readers
 * must wait until after that writer has written.  That
 * writer will then sweep into reading the database those
 * readers that had to wait.  However a finishing writer
 * will sweep into reading the database ALL waiting readers,
 * even those readers that arrived after a writer that
 * arrived after the current writer that is just finishing.
 */
import Utilities.*;

class Database extends MyObject {

   private int numReaders = 0;
   private int numWriters = 0;
   private int numWaitingReaders = 0;
   private int numWaitingWriters = 0;
   private boolean okToWrite = true;
   private long startWaitingReadersTime = 0;

   public Database() { super("rwDB"); }

   public synchronized void startRead(int i) {
      long readerArrivalTime = 0;
      if (numWaitingWriters > 0 || numWriters > 0) {
         numWaitingReaders++;
         readerArrivalTime = age();
         while (readerArrivalTime >= startWaitingReadersTime)
            try {wait();} catch (InterruptedException e) {}
         numWaitingReaders--;
      }
      numReaders++;
   }
  
   public synchronized void endRead(int i) {
      numReaders--;
      okToWrite = numReaders == 0;
      if (okToWrite) notifyAll();
   }
  
   public synchronized void startWrite(int i) {
      if (numReaders > 0 || numWriters > 0) {
         numWaitingWriters++;
         okToWrite = false;
         while (!okToWrite)
            try {wait();} catch (InterruptedException e) {}
         numWaitingWriters--;
      }
      okToWrite = false;
      numWriters++;
   }
  
   public synchronized void endWrite(int i) {
      numWriters--;              // ASSERT(numWriters==0)
      okToWrite = numWaitingReaders == 0;
      startWaitingReadersTime = age();
      notifyAll();
   }
}

/* ............... Example compile and run(s)

D:\>javac rwmo.java rwdr.java

D:\>java ReadersWriters -E5 -W3 -e2 -w2 -R5
ReadersWriters: numReaders=5, numWriters=3, rNap=2, wNap=2, runTime=5
age=110, Reader1 napping for 391 ms
age=110, Reader3 napping for 1319 ms
age=110, Reader0 napping for 308 ms
age=110, Reader4 napping for 1597 ms
age=160, Reader2 napping for 1026 ms
age=160, WRITER2 napping for 1401 ms
age=160, WRITER1 napping for 874 ms
age=160, WRITER0 napping for 1119 ms
All threads started
age=440, Reader0 wants to read
age=440, Reader0 reading for 1530 ms
age=490, Reader1 wants to read
age=490, Reader1 reading for 93 ms
age=600, Reader1 finished reading
age=600, Reader1 napping for 1931 ms
age=1040, WRITER1 wants to write
age=1210, Reader2 wants to read
age=1320, WRITER0 wants to write
age=1430, Reader3 wants to read
age=1590, WRITER2 wants to write
age=1760, Reader4 wants to read
age=1980, Reader0 finished reading
age=1980, Reader0 napping for 329 ms
age=1980, WRITER1 writing for 1144 ms
age=2310, Reader0 wants to read
age=2530, Reader1 wants to read
age=3130, WRITER1 finished writing
age=3130, WRITER1 napping for 774 ms
age=3130, Reader4 reading for 985 ms
age=3180, Reader2 reading for 1007 ms
age=3240, Reader3 reading for 472 ms
age=3240, Reader0 reading for 1436 ms
age=3240, Reader1 reading for 1894 ms
age=3680, Reader3 finished reading
age=3680, Reader3 napping for 1759 ms
age=3900, WRITER1 wants to write
age=4120, Reader4 finished reading
age=4120, Reader4 napping for 1946 ms
age=4170, Reader2 finished reading
age=4170, Reader2 napping for 210 ms
age=4390, Reader2 wants to read
age=4670, Reader0 finished reading
age=4670, Reader0 napping for 1898 ms
age=5110, Reader1 finished reading
age=5110, Reader1 napping for 1580 ms
age=5110, WRITER2 writing for 6 ms
age=5160, WRITER2 finished writing
age=5160, WRITER2 napping for 1462 ms
age=5220, Reader2 reading for 800 ms
age()=5220, time to stop the threads and exit
                                            ... end of example run(s)  */
