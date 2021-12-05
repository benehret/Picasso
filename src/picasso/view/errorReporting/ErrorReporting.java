package picasso.view.errorReporting;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import picasso.parser.ParseException;
import picasso.view.Frame;

public class ErrorReporting {
    
	private static JFrame myFrame;
	
    public ErrorReporting(JFrame frame)
    {
    	myFrame = frame;
    }
    
    public static void reportException(Exception e)
    {
		JOptionPane.showMessageDialog(myFrame,
    		    e.toString());
	}
}
