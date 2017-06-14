import java.io.*;

public class Receiver extends Thread {

   // to write Objects
   private ObjectOutputStream oos;
   private ObjectInputStream  ois;

   // to write raw bytes
   private InputStream        is;
   private OutputStream       os;

   public Receiver ( InputStream       is,  OutputStream       os,
                     ObjectInputStream ois, ObjectOutputStream oos ) {
      this.is=is;   this.os=os;
      this.ois=ois; this.oos=oos;
   } // end CONSTRUCTOR

   public void run ( ) {
      System.out.println( "Receiver starting execution." );
      try {

            // create the message
            Message m = new Message();
            m.theMessage = "Hey there!";
            String[] s = { "uno", "dos", "tres" };
            m.someLines = s;
            m.someNumber= 64;

            System.out.println( "Receiver sends: " );
            System.out.println( m );

            // let objects be transmitted across the pipe
            oos = new ObjectOutputStream( os );
            oos.writeObject( m );

            int curr = 0;
            while ( curr != 255 ) {
               os.write( 10 );

               String msg = " ";
               do {
                  curr = is.read();
                  if ( curr != '\r' && curr !='\n' )
                     msg += (char) curr;
               } while ( is.available() != 0 );
               System.out.println( "Receiver reads: " + msg );

            } // end WHILE

      } // end TRY
      catch ( Exception exc ) {
            System.out.println ( "Error Receiver: " + exc );
      } // end CATCH

   } // end METHOD run

} // end CLASS Receiver
