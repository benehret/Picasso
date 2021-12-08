package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import picasso.view.errorReporting.ErrorReporting;

/**
 * Evaluate an expression using userinput for each pixel in a image.
 * 
 * @author Robert C Duvall
 * @author Sara Sprenkle
 * @author Nicolas
 */
public class EvaluatorInput implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	private JTextField myTextField;
	private Map<String, String> assignment = new HashMap<String, String>();

	public EvaluatorInput(JTextField field) {
		myTextField = field;
	}

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) throws NullPointerException {
		ExpressionTreeNode expr = createExpression();
		// If the user entered nothing, throw an error message
		if (expr == null) {
			ErrorReporting.reportException(new IllegalArgumentException("Please enter a valid input."));
			
		}
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
	private ExpressionTreeNode createExpression() {
		// Note, when you're testing, you can use the ExpressionTreeGenerator to
		// generate expression trees from strings, or you can create expression
		// objects directly (as in the commented statement below).

		// String input = whatever we get form the JTExtBox
		String input = myTextField.getText();
		/**for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '=') {
				String key = input.substring(0, i);
				input = input.substring(i + 2);
				assignment.put(key, input);
				// if(input == key) {
				// input = assignment.get(key);
				// }
			}
		}
		for (String key : assignment.keySet()) {
			if (input.contains(key)) {
				 input = assignment.get(key);
				System.out.println(assignment.get(key));
			}
			
		}*/
		System.out.println("INPUT: " + input);
		System.out.println(assignment);
		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		// ExpressionTreeNode expression = expTreeGen.makeExpression(input);
		try {
			return expTreeGen.makeExpression(input);
		}
		// https://stackoverflow.com/questions/3495926/can-i-catch-multiple-java-exceptions-in-the-same-catch-clause
		catch (ParseException e) {
			// System.out.println("Crap");
			ErrorReporting.reportException(e);
			// How can I do this without having this return statement here?
			return expTreeGen.makeExpression(input);
		}
	}

}