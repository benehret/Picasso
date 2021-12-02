package picasso.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	private JTextField textField = new JTextField(16);
	
	public Frame(Dimension size) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create GUI components
		Canvas canvas = new Canvas(this);
		canvas.setSize(size);

		// add commands to test here
		ButtonPanel commands = new ButtonPanel(canvas);
		commands.add("Open", new Reader());
		commands.add("Evaluate", new ThreadedCommand<Pixmap>(canvas, new Evaluater()));
		commands.add("Save", new Writer());
		commands.add("User Input", new ThreadedCommand<Pixmap>(canvas, new EvaluatorInput(this.textField)));

		//add a JTextBox
		//https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
		commands.add(textField);

		// add our container to Frame and show it
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(commands, BorderLayout.NORTH);
		pack();
	}
	
	public String getText()
	{
		return textField.getText();
	}
}
