import Utilities.*;

class Reader extends MyObject implements Runnable {

   private int id = 0;
   private int rNap = 0; // milliseconds
   private Database db = null;

   public Reader(String name, int id, int rNap, Database db) {
      super(name + id);
      this.id = id;
      this.rNap = rNap;
      this.db = db;
      new Thread(this).start();
   }

   public void run() {
      int napping;
      while (true) {
         napping = 1 + (int) random(rNap);
         System.out.println("age=" + age() + ", " + getName()
            + " napping for " + napping + " ms");
         nap(napping);
         System.out.println("age=" + age() + ", " + getName()
            + " wants to read");
         db.startRead(id);
         napping = 1 + (int) random(rNap);
         System.out.println("age=" + age() + ", " + getName()
            + " reading for " + napping + " ms");
         nap(napping);
         db.endRead(id);
         System.out.println("age=" + age() + ", " + getName()
            + " finished reading");
      }
   }
}

class Writer extends MyObject implements Runnable {

   private int id = 0;
   private int wNap = 0; // milliseconds
   private Database db = null;

   public Writer(String name, int id, int wNap, Database db) {
      super(name + id);
      this.id = id;
      this.wNap = wNap;
      this.db = db;
      new Thread(this).start();
   }

   public void run() {
      int napping;
      while (true) {
         napping = 1 + (int) random(wNap);
         System.out.println("age=" + age() + ", " + getName()
            + " napping for " + napping + " ms");
         nap(napping);
         System.out.println("age=" + age() + ", " + getName()
            + " wants to write");
         db.startWrite(id);
         napping = 1 + (int) random(wNap);
         System.out.println("age=" + age() + ", " + getName()
            + " writing for " + napping + " ms");
         nap(napping);
         db.endWrite(id);
         System.out.println("age=" + age() + ", " + getName()
            + " finished writing");
      }
   }
}

class ReadersWriters extends MyObject {

   public static void main(String[] args) {

      // parse command line arguments, if any, to override defaults
      GetOpt go = new GetOpt(args, "UE:W:e:w:R:");
      go.optErr = true;
      String usage = "Usage: -E numR -W numW -e rNap -w wNap -R runTime";
      int ch = -1;
      int numReaders = 3;
      int numWriters = 2;
      int rNap = 2;       // defaults
      int wNap = 3;       // in
      int runTime = 60;   // seconds
      while ((ch = go.getopt()) != go.optEOF) {
         if      ((char)ch == 'U') {
            System.out.println(usage);  System.exit(0);
         }
         else if ((char)ch == 'E')
            numReaders = go.processArg(go.optArgGet(), numReaders);
         else if ((char)ch == 'W')
            numWriters = go.processArg(go.optArgGet(), numWriters);
         else if ((char)ch == 'e')
            rNap = go.processArg(go.optArgGet(), rNap);
         else if ((char)ch == 'w')
            wNap = go.processArg(go.optArgGet(), wNap);
         else if ((char)ch == 'R')
            runTime = go.processArg(go.optArgGet(), runTime);
         else {
            System.err.println(usage);  System.exit(1);
         }
      }
      System.out.println("ReadersWriters: numReaders=" + numReaders
         + ", numWriters=" + numWriters
         + ", rNap=" + rNap + ", wNap=" + wNap + ", runTime=" + runTime);

      // create the database to be read/written
      Database db = new Database();

      // create the Readers and Writers (with self-starting threads)
      for (int i = 0; i < numReaders; i++)
         new Reader("Reader", i, rNap*1000, db);
      for (int i = 0; i < numWriters; i++)
         new Writer("WRITER", i, wNap*1000, db);
      System.out.println("All threads started");

      // let them run for a while
      nap(runTime*1000);
      System.out.println("age()=" + age()
         + ", time to stop the threads and exit");
      System.exit(0);
   }
}
