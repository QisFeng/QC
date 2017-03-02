import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class EditMenuHandler implements ActionListener {
   JFrame jframe;
   public EditMenuHandler (JFrame jf) {
      jframe = jf;
   }
   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Find"))
         JOptionPane.showMessageDialog(null,"You clicked on Find"); 
      else if (menuName.equals("Edit"))
          JOptionPane.showMessageDialog(null,"You clicked on Edit"); 
   } //actionPerformed
}