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
    	// slice off the stacktrace part of the exception message
    	int colon = e.toString().lastIndexOf(":");
        String exceptionMessage = e.toString().substring(colon+1);
		JOptionPane.showMessageDialog(myFrame,
    		    exceptionMessage);
	}
}
