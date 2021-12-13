package picasso.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;
import picasso.view.errorReporting.ErrorReporting;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	private JTextField textField = new JTextField(16);
	// error for the history scrubbing stuff
	public IndexOutOfBoundsException i = new IndexOutOfBoundsException("Watch out, we're out of bounds!");

	
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
		commands.add("Random Expression",new ThreadedCommand<Pixmap>(canvas, new RandomEvaluator()));
		commands.add("User Input", new ThreadedCommand<Pixmap>(canvas, new EvaluatorInput(this.textField)));

		//add a JTextBox
		//https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
		commands.add(textField);
		
		// Code for the history scrubbing stuff
		// https://stackoverflow.com/questions/286727/unresponsive-keylistener-for-jframe
		textField.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
				{
					EvaluatorInput.historyPosition--;
					try {
						textField.setText(EvaluatorInput.history.get(EvaluatorInput.historyPosition));
					}
					// Throw an error if we've went out of bounds and move it back in bounds
					catch (IndexOutOfBoundsException exception)
					{
						ErrorReporting.reportException(i);
						EvaluatorInput.historyPosition ++;
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					EvaluatorInput.historyPosition++;
					try {
						textField.setText(EvaluatorInput.history.get(EvaluatorInput.historyPosition));
					}
					// Throw an error if we've went out of bounds and move it back in bounds
					catch (IndexOutOfBoundsException exception)
					{
						ErrorReporting.reportException(i);
						EvaluatorInput.historyPosition --;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		});

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
