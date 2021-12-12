package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
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
import picasso.parser.language.expressions.Assignment;
import picasso.parser.IdentifierAnalyzer;

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
	private ExpressionTreeNode expr;
	public static List<String> history = new ArrayList<String>(); 
	public static int historyPosition = 0;

	public EvaluatorInput(JTextField field) {
		myTextField = field;
	}

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) throws NullPointerException {
		expr = createExpression();
		// If the user entered nothing, throw an error message
		if (expr == null) 
		{
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
		
		history.add(input);
		System.out.println("HISTORY: " + history);
		// Increment the historyPosition by 1 so we're at the end
		historyPosition = history.size() - 1;
		System.out.println("SIZE: " + history.size());
		historyPosition++;
		
		// check if the input we got is in the dictionary
		for (String key : IdentifierAnalyzer.getMap().keySet()) {
			// https://www.geeksfor					EvaluatorInput.history.get(EvaluatorInput.historyPosition - 1);
			// geeks.org/compare-two-strings-in-java/#:~:text=Using%20String.,match%2C%20then%20it%20returns%20false.
			// If it's in the dictionary, return the value (Which is an ExpressionTreeNode)
			if (input.equals(key)) {
				return IdentifierAnalyzer.getMap().get(key);
			}
			
		}
		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		// ExpressionTreeNode expression = expTreeGen.makeExpression(input);
		try {
			return expTreeGen.makeExpression(input);
		}
		// https://stackoverflow.com/questions/3495926/can-i-catch-multiple-java-exceptions-in-the-same-catch-clause
		catch (ParseException e) {
			ErrorReporting.reportException(e);
			// How can I do this without having this return statement here?
			return expTreeGen.makeExpression(input);
		}
	}

}