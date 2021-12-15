package picasso.view.commands;

import javax.swing.JFileChooser;

import picasso.model.Pixmap;
import picasso.parser.language.ExpressionReader;
import picasso.util.FileCommand;

/**
 * Open the chosen image file and display in the Pixmap target.
 * 
 * @author Robert C Duvall
 */
public class Reader extends FileCommand<Pixmap> {

	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public Reader() {
		super(JFileChooser.OPEN_DIALOG);
	}

	/**
	 * Displays the image file on the given target.
	 */
	public void execute(Pixmap target) {
		String fileName = getFileName();
		if (fileName != null && (fileName.substring(fileName.length() - 4)).equals(".jpg")) {
			target.read(fileName);
		} else if (fileName != null && (fileName.substring(fileName.length() - 4)).equals(".exp")) {
			ExpressionReader expR = new ExpressionReader(fileName);
			Evaluater e = new Evaluater();
			for(int i =0; i< ExpressionReader.getExpressionList().size(); i++ ) {
				if(!ExpressionReader.getExpressionList().get(i).contains("//")) {
					System.out.println(ExpressionReader.getExpressionList());
					e.setExp(ExpressionReader.getExpressionList().get(i));
					e.execute(target);
				}
			}
		}
	}
}
