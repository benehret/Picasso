package picasso.view.errorReporting;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import picasso.view.Frame;

public class ErrorReporting {
    
	private static JFrame myFrame;
	
    public ErrorReporting(JFrame frame)
    {
    	myFrame = frame;
    }
    
    public static void reportError()
    {
		JOptionPane.showMessageDialog(myFrame,
    		    "Eggs are not supposed to be green.");
    }

}
