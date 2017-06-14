import java.io.*;

public class Sender extends Thread {

   // to write Objects
   private ObjectOutputStream oos;
   private ObjectInputStream  ois;

   // to write raw bytes
   private InputStream        is;
   private OutputStream       os;

   public Sender ( InputStream       is,  OutputStream       os,
                   ObjectInputStream ois, ObjectOutputStream oos ) {
      this.is=is;   this.os=os;
      this.ois=ois; this.oos=oos;
   } // end CONSTRUCTOR

   public void run ( ) {
      System.out.println( "Sender starting execution." );

      try {
            // open the file to be read & sent
            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                       new FileInputStream(
                                          "Sender.java" )));

            // receive and display the message

            // readUnshared reads an object that is a complete
            // duplicate, in no way attached to the original
            // however, readUnshared doesn't work on older JVMs
            // like on forbin so its been replaced with readObject()
            ois = new ObjectInputStream ( is );
            Message m = (Message) ois.readObject();

            System.out.println("Sender receives: " );
            System.out.println(m);

            int next = 0;
            int read = 0;
            do {
               read = is.read();
               String msg = "";
               for ( int i = 0 ; i < read ; i++ ) {
                     next = br.read();
                     if ( next != '\r' && next != '\n' )
                        msg += (char) next;
                     os.write( next );
                     if ( next == -1 ) break;
               } // end FOR(i)
               System.out.println( "\nSender Sent: " + msg );

            } while( read != 0  && next != -1 );

            os.close();

      } // end TRY
      catch ( Exception exc ) {
            System.out.println
                  ( "Error Sender: " + exc );
      } // end CATCH

   } // end METHOD run

} // end CLASS Sender
