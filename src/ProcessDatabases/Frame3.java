package ProcessDatabases;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame3 extends JFrame  
{  
      Frame3()   
      {  
        setBounds(100,100,300,100);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setVisible(true);   
      }  
       
      public static void main(String args[]) {
         Runnable run = new Runnable()
         {
              public void run()
              {
                  new Frame3();
              }
         };  
         SwingUtilities.invokeLater(run);
         System.out.println("You are here");  
    }  
}