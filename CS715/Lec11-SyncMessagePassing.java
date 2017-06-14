package Synchronization;

import Utilities.*;

// like bounded buffer multiple producers and multiple
// consumers with one buffer slot except producer must
// wait for the consumer to extract the message

public final class SyncMessagePassing extends MessagePassingRoot {

/*
 * This class implements a rendezvous between the sender and reveiver:
 * they meet at the same time inside an object instantiated from this
 * class and a message object is passed from sender to receiver.  This
 * version works inside a single JVM, that is, it requires shared memory.
 * To send an object from one JVM to another requires object serialization.
 *
 * Implementing send() and receive() with synchronized methods required
 * using notifyAll() in those methods.  But we can use notify() if we
 * relax the synchronization requirement from both methods to only each
 * method with respect to itself.  The notify() is inside the two binary
 * semaphores used.
 */

   private Object theMessage = null;
   private final Object sending = new Object();
   private final Object receiving = new Object();
   private final BinarySemaphore senderIn = new BinarySemaphore(0);
   private final BinarySemaphore receiverIn = new BinarySemaphore(0);

   public SyncMessagePassing() {super();}

   public final String toString() {
      // should synchronize on both sending and receiving but won't
      // because of deadlock dangers
      // synchronized (sending) { synchronized (receiving) {
      return "theMessage="+theMessage+", senderIn="+senderIn
         +", receiverIn="+receiverIn;
      // }}
   }

   public final void send(Object m) {
      if (m == null) {
         System.err.println("null message passed to send()");
         throw new NullPointerException();
      }
      synchronized (sending) {
         theMessage = m;
         V(senderIn);
         P(receiverIn);
      }
   }

   public final Object receive() {
      Object receivedMessage = null;
      synchronized (receiving) {
         P(senderIn);
         receivedMessage = theMessage;
         V(receiverIn);
      }
      return receivedMessage;
   }
}
