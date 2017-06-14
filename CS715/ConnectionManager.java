import java.io.*;

public class ConnectionManager {

   static private PipedInputStream   pis1;
   static private PipedOutputStream  pos1;

   static private PipedInputStream   pis2;
   static private PipedOutputStream  pos2;

   static private ObjectOutputStream oos;
   static private ObjectInputStream  ois;

   public static void main ( String argv[] ) {
      try {

      // set up a pipe
      System.out.println( "Pipe setup" );
      pos1 = new PipedOutputStream( );
      pis1 = new PipedInputStream ( pos1 );

      pos2 = new PipedOutputStream( );
      pis2 = new PipedInputStream ( pos2 );

      System.out.println( "Object creation" );
      Receiver r = new Receiver( pis2, pos1, ois, oos );
      Sender s   = new Sender  ( pis1, pos2, ois, oos );

      System.out.println( "Thread execution" );
      r.start(); s.start();
      } // end TRY
      catch ( Exception exc ) {
            System.out.println( exc );
      } // end CATCH
   }

} // end CLASS ConnectionManager
