import Utilities.*;
import Synchronization.*;

class Message { public int number, id;
   public Message(int number, int id) { this.number = number; this.id = id;}
}

class Node extends MyObject implements Runnable {

   private static final int MAIN = 0, REQUESTS = 1, REPLIES = 2;
   private int whichOne = 0;

   private int id = -1;
   private int numNodes = -1;
   private int napOutsideCS = 0; // both are in
   private int napInsideCS = 0;  // milliseconds
   private MessagePassing[] requestChannel = null;
   private MessagePassing[] replyChannel = null;
   private MessagePassing requestsToMe = null;
   private MessagePassing repliesToMe = null;
   private int number = 0;
   private int highNumber = 0;
   private boolean requesting = false;
   private int replyCount = 0;
   private BinarySemaphore s = new BinarySemaphore(1);
   private BinarySemaphore wakeUp = new BinarySemaphore(0);
   private boolean[] deferred = null;

   public Node(String name, int id, int numNodes,
         int napOutsideCS, int napInsideCS,
         MessagePassing[] requestChannel, MessagePassing replyChannel[],
         MessagePassing requestsToMe, MessagePassing repliesToMe) {
      super(name + " " + id);
      this.id = id;
      this.numNodes = numNodes;
      this.napOutsideCS = napOutsideCS;
      this.napInsideCS = napInsideCS;
      this.requestChannel = requestChannel;
      this.replyChannel = replyChannel;
      this.requestsToMe = requestsToMe;
      this.repliesToMe = repliesToMe;
      deferred = new boolean[numNodes];
      for (int i = 0; i < numNodes; i++) deferred[i] = false;
      System.out.println(getName() + " is alive, napOutsideCS="
         + napOutsideCS + ", napInsideCS=" + napInsideCS);
      new Thread(this).start();
   }

   public void run() { // start three different threads in the same object
      int meDo = whichOne++;
      if (meDo == MAIN) {
         new Thread(this).start();
         main();
      } else if (meDo == REQUESTS) {
         new Thread(this).start();
         handleRequests();
      } else if (meDo == REPLIES) {
         handleReplies();
      }
   }

   private void chooseNumber() {
      P(s);
      requesting = true;
      number = highNumber + 1;
      V(s);
   }

   private void sendRequest() {
      replyCount = 0;
      for (int j = 0; j < numNodes; j++) if (j != id)
         send(requestChannel[j], new Message(number, id));
   }

   private void waitForReply() {
      P(wakeUp);
   }

   private void replyToDeferredNodes() {
      P(s);
      requesting = false;
      V(s);
      for (int j = 0; j < numNodes; j++) {
         if (deferred[j]) {
            deferred[j] = false;
            send(replyChannel[j], id);
         }
      }
   }

   private void outsideCS() {
      int napping;
      napping = ((int) random(napOutsideCS)) + 1;
      System.out.println("age()=" + age() + ", " + getName()
         + " napping outside CS for " + napping + " ms");
      nap(napping);
   }

   private void insideCS() {
      int napping;
      napping = ((int) random(napInsideCS)) + 1;
      System.out.println("age()=" + age() + ", " + getName()
         + " napping inside CS for " + napping + " ms");
      nap(napping);
   }

   private void main() {
      while (true) {
         outsideCS();
         System.out.println("age()=" + age() + ", node " + id
            + " wants to enter its critical section");
         chooseNumber();               // PRE-PROTOCOL
         sendRequest();                //      "
         waitForReply();               //      "
         insideCS();
         System.out.println("age()=" + age() + ", node " + id
            + " has now left its critical section");
         replyToDeferredNodes();       // POST-PROTOCOL
      }
   }

   private void handleRequests() {
      while (true) {
         Message m = (Message) receive(requestsToMe);
         int receivedNumber = m.number;
         int receivedID = m.id;
         highNumber = Math.max(highNumber, receivedNumber);
         P(s);
         boolean decideToDefer = requesting && (number < receivedNumber
            || (number == receivedNumber && id < receivedID));
         if (decideToDefer) deferred[receivedID] = true;
         else send(replyChannel[receivedID], id);
         V(s);
      }
   }

   private void handleReplies() {
      while (true) {
         int receivedID = receiveInt(repliesToMe);
         replyCount++;
         if (replyCount == numNodes - 1) V(wakeUp);
      }
   }
}

class DistributedMutualExclusion extends MyObject {

   public static void main(String[] args) {

      // parse command line options, if any, to override defaults
      GetOpt go = new GetOpt(args, "Un:R:");
      String usage = "Usage: -n numNodes -R runTime"
         + " napOutsideCS[i] napInsideCS[i] i=0,1,...";
      go.optErr = true;
      int ch = -1;
      int numNodes = 5;
      int runTime = 60;      // seconds
      while ((ch = go.getopt()) != go.optEOF) {
         if      ((char)ch == 'U') {
            System.out.println(usage);  System.exit(0);
         }
         else if ((char)ch == 'n')
            numNodes = go.processArg(go.optArgGet(), numNodes);
         else if ((char)ch == 'R')
            runTime = go.processArg(go.optArgGet(), runTime);
         else {
            System.err.println(usage);  System.exit(1);
         }
      }
      System.out.println("DistributedMutualExclusion: numNodes="
         + numNodes + ", runTime=" + runTime);

      // process non-option command line arguments
      int[] napOutsideCS = new int[numNodes];
      int[] napInsideCS = new int[numNodes];
      int argNum = go.optIndexGet();
      for (int i = 0; i < numNodes; i++) {
         napOutsideCS[i] = go.tryArg(argNum++, 8);
         napInsideCS[i] = go.tryArg(argNum++, 2);
      }
      // create communication channels
      MessagePassing[] requestChannel = null, replyChannel = null,
         requestChannelS = null, requestChannelR = null,
         replyChannelS = null, replyChannelR = null;
      requestChannel = new MessagePassing[numNodes];
      replyChannel = new MessagePassing[numNodes];
      requestChannelS = new MessagePassing[numNodes];
      replyChannelS = new MessagePassing[numNodes];
      requestChannelR = new MessagePassing[numNodes];
      replyChannelR = new MessagePassing[numNodes];
      for (int i = 0; i < numNodes; i++) {
         requestChannel[i] = new AsyncMessagePassing();
         replyChannel[i] = new AsyncMessagePassing();
         requestChannelS[i] = new MessagePassingSendOnly(requestChannel[i]);
         replyChannelS[i] = new MessagePassingSendOnly(replyChannel[i]);
         requestChannelR[i] = new MessagePassingReceiveOnly(requestChannel[i]);
         replyChannelR[i] = new MessagePassingReceiveOnly(replyChannel[i]);
      }

      // create the Nodes (they start their own threads)
      for (int i = 0; i < numNodes; i++)
         new Node("Node", i, numNodes,
            napOutsideCS[i]*1000, napInsideCS[i]*1000,
            requestChannelS, replyChannelS,
            requestChannelR[i], replyChannelR[i]);
      System.out.println("All Nodes created");

      // let the Nodes run for a while
      nap(runTime*1000);
      System.out.println("age()=" + age()
         + ", time to stop the threads and exit");
      System.exit(0);
   }
}

/* ............... Example compile and run(s)

D:\>javac dimu.java

D:\>java DistributedMutualExclusion -R20
DistributedMutualExclusion: numNodes=5, runTime=20
Node 0 is alive, napOutsideCS=8000, napInsideCS=2000
Node 1 is alive, napOutsideCS=8000, napInsideCS=2000
Node 2 is alive, napOutsideCS=8000, napInsideCS=2000
Node 3 is alive, napOutsideCS=8000, napInsideCS=2000
Node 4 is alive, napOutsideCS=8000, napInsideCS=2000
age()=170, Node 1 napping outside CS for 2719 ms
age()=170, Node 2 napping outside CS for 279 ms
All Nodes created
age()=170, Node 3 napping outside CS for 2355 ms
age()=220, Node 0 napping outside CS for 2393 ms
age()=220, Node 4 napping outside CS for 8 ms
age()=220, node 4 wants to enter its critical section
age()=330, Node 4 napping inside CS for 911 ms
age()=440, node 2 wants to enter its critical section
age()=1260, node 4 has now left its critical section
age()=1260, Node 4 napping outside CS for 4042 ms
age()=1260, Node 2 napping inside CS for 183 ms
age()=1480, node 2 has now left its critical section
age()=1480, Node 2 napping outside CS for 7335 ms
age()=2530, node 3 wants to enter its critical section
age()=2530, Node 3 napping inside CS for 741 ms
age()=2580, node 0 wants to enter its critical section
age()=2860, node 1 wants to enter its critical section
age()=3300, node 3 has now left its critical section
age()=3300, Node 3 napping outside CS for 6849 ms
age()=3300, Node 0 napping inside CS for 1710 ms
age()=5000, node 0 has now left its critical section
age()=5000, Node 0 napping outside CS for 5253 ms
age()=5000, Node 1 napping inside CS for 1694 ms
age()=5330, node 4 wants to enter its critical section
age()=6700, node 1 has now left its critical section
age()=6700, Node 1 napping outside CS for 3063 ms
age()=6700, Node 4 napping inside CS for 397 ms
age()=7140, node 4 has now left its critical section
age()=7140, Node 4 napping outside CS for 3687 ms
age()=8790, node 2 wants to enter its critical section
age()=8790, Node 2 napping inside CS for 102 ms
age()=8900, node 2 has now left its critical section
age()=8900, Node 2 napping outside CS for 1174 ms
age()=9780, node 1 wants to enter its critical section
age()=9780, Node 1 napping inside CS for 1617 ms
age()=10110, node 2 wants to enter its critical section
age()=10160, node 3 wants to enter its critical section
age()=10270, node 0 wants to enter its critical section
age()=10820, node 4 wants to enter its critical section
age()=11430, node 1 has now left its critical section
age()=11430, Node 1 napping outside CS for 5326 ms
age()=11430, Node 2 napping inside CS for 628 ms
age()=12090, node 2 has now left its critical section
age()=12090, Node 2 napping outside CS for 4970 ms
age()=12090, Node 3 napping inside CS for 545 ms
age()=12630, node 3 has now left its critical section
age()=12630, Node 3 napping outside CS for 7989 ms
age()=12630, Node 0 napping inside CS for 904 ms
age()=13510, node 0 has now left its critical section
age()=13510, Node 0 napping outside CS for 4162 ms
age()=13510, Node 4 napping inside CS for 1440 ms
age()=15000, node 4 has now left its critical section
age()=15000, Node 4 napping outside CS for 2578 ms
age()=16750, node 1 wants to enter its critical section
age()=16750, Node 1 napping inside CS for 123 ms
age()=16860, node 1 has now left its critical section
age()=16860, Node 1 napping outside CS for 3709 ms
age()=17030, node 2 wants to enter its critical section
age()=17030, Node 2 napping inside CS for 97 ms
age()=17140, node 2 has now left its critical section
age()=17140, Node 2 napping outside CS for 7901 ms
age()=17580, node 4 wants to enter its critical section
age()=17580, Node 4 napping inside CS for 1695 ms
age()=17690, node 0 wants to enter its critical section
age()=19280, node 4 has now left its critical section
age()=19280, Node 4 napping outside CS for 3751 ms
age()=19280, Node 0 napping inside CS for 869 ms
age()=20160, node 0 has now left its critical section
age()=20160, Node 0 napping outside CS for 6489 ms
age()=20160, time to stop the threads and exit
                                            ... end of example run(s)  */
