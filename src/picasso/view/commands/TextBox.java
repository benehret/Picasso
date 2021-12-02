package picasso.view.commands;



import java.awt.Color;
import java.awt.Dimension;

import picasso.model.Pixmap;

import picasso.model.Pixmap;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;

//Java program to create a blank text field with a
//given initial text and given number of columns
import java.awt.event.*;
import javax.swing.*;


public class TextBox extends JFrame implements ActionListener, Command<Pixmap> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	// JTextField
	static JTextField t;
	private String result;
	private boolean answer;
	// JFrame
	static JFrame f;
	// JButton
	static JButton b;


	// label to display text
	static JLabel l;
	
	
	
	// default constructor
	public TextBox() {
		answer = false;

	}

	/**
	 * @return the result
	 */
	public final String getResult() {
		return result;
	}

	/**
	 * @return the answer
	 */
	public final boolean getAnswer() {
		return answer;
	}


	public static void frame() {
		// create a new frame to store text field and button
		f=new JFrame("textfield");

		// create a label to display text
		l=new JLabel("nothing entered");

		// create a new button
		b=new JButton("submit");
		
		/**
		 * 
		 */
		TextBox te = new TextBox();
		// addActionListener to button
		b.addActionListener(te);

		// create a object of JTextField with 16 columns and a given initial text
		t=new JTextField("",16);

		// create a panel to add buttons and textfield
		JPanel p = new JPanel();
		
		// add buttons and textfield to panel
		p.add(t);
		p.add(b);
		p.add(l);

		// add panel to frame
		f.add(p);

		// set the size of frame3
		f.setSize(300, 300);
		f.setVisible(true);

	}

	// if the button is pressed
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("submit")) {
			// set the text of the label to the text of the field
			l.setText(t.getText());
			result = t.getText();
			// set the text of field to blank
			t.setText("  ");
			answer = true;
		}
	}
	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) {
		frame();
		//if (answer == true) {
			System.out.println("123");
			// create the expression to evaluate just once
			ExpressionTreeNode expr = createExpression(result);
			// evaluate it for each pixel
			Dimension size = target.getSize();
			for (int imageY = 0; imageY < size.height; imageY++) {
				double evalY = imageToDomainScale(imageY, size.height);
				for (int imageX = 0; imageX < size.width; imageX++) {
					double evalX = imageToDomainScale(imageX, size.width);
					Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
					target.setColor(imageX, imageY, pixelColor);
				}
			}
		//}
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}

	/**
	 * 
	 * A place holder for a more interesting way to build the expression.
	 */
	private ExpressionTreeNode createExpression(String test) {
		// Note, when you're testing, you can use the ExpressionTreeGenerator to
		// generate expression trees from strings, or you can create expression
		// objects directly (as in the commented statement below).
		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		return expTreeGen.makeExpression(test);

		// return new Multiply( new X(), new Y() );
	}


}

// Code shamelessly stolen from:
//https://www.geeksforgeeks.org/java-swing-jtextfield/

import java.awt.event.*;
import javax.swing.*;
class TextBox extends JFrame implements ActionListener {
 // JTextField
 static JTextField t;
 // JFrame
 static JFrame f;
 // JButton
 static JButton b;
 // label to display text
 static JLabel l;

 // boolean to check if an answer is ready or not
 boolean answer = false;
 
 
 // default constructor
 TextBox()
 {
 }

 // main class
 public void frame()
 {
     // create a new frame to store text field and button
     f = new JFrame("textfield");
     // create a label to display text
     l = new JLabel("nothing entered");
     // create a new button
     b = new JButton("submit");
      
     // create a object of the text class
     TextBox te = new TextBox();

     // addActionListener to button
     b.addActionListener(te);

     // create a object of JTextField with 16 columns
     t = new JTextField(16);

     // create a panel to add buttons and textfield
     JPanel p = new JPanel();

     // add buttons and textfield to panel
     p.add(t);
     p.add(b);
     p.add(l);

     // add panel to frame
     f.add(p);

     // set the size of frame
     f.setSize(300, 300);

     f.show();
 }

 	// if the button is pressed
 	public void actionPerformed(ActionEvent e)
 	{
     String s = e.getActionCommand();
     if (s.equals("submit")) {
         // set the text of the label to the text of the field
         l.setText(t.getText());

         // set the text of field to blank
         t.setText("  ");
         
         answer = true;
         
         
         }
    }
 	
 	public String getTextInput()
 	{
 		//return t.getText();
 		return "ceil(x)";
 	}
 }

