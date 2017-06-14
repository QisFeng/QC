package Synchronization;

import Utilities.*;
import java.util.Vector;

// like the multiple producers and multiple consumers with
// an infinite buffer

public final class AsyncMessagePassing extends MessagePassingRoot {

   // if numMessages < 0, then abs(numMessages) is the size
   // of the queue of blocked receivers waiting for a message
   private int numMessages = 0;
   private final Vector messages = new Vector();

   public AsyncMessagePassing() {super();}

   // Can be used to see if there are messages waiting, whether a
   // receive() would block, whether there are waiting receivers.
   // BUT! a race condition is possible in code like
   //   if (numMessages() > 0) receive();
   // so use tryReceive() for that instead.
   public final synchronized int numMessages() {
      return numMessages;
   }

   public final synchronized String toString() {
      return "messages="+messages+", numMessages="+numMessages;
   }

   public final synchronized void send(Object m) {
      if (m == null) {
         System.err.println("null message passed to send()");
         throw new NullPointerException();
      }
      numMessages++;
      messages.addElement(m); // at end
      if (numMessages <= 0) notify();
   }

   public final synchronized Object receive() {
      Object receivedMessage = null;
      numMessages--;
      if (numMessages < 0) {
         while (true) {     // we must be notified not interrupted
            try {
               wait();
               break;       // notify(), so get message
            }
            catch (InterruptedException e) {
               System.err.println
                  ("receive(): InterruptedException, wait again");
               // race condition fix, see Semaphore.java
               if (numMessages >= 0) break;
               else continue;    // no message yet
            }
         }
      }
      receivedMessage = messages.firstElement();
      messages.removeElementAt(0);
      return receivedMessage;
   }

   public final synchronized Object tryReceive()
         throws WouldBlockException {
      if (numMessages > 0) return this.receive();
      else throw new WouldBlockException();
   }
}
