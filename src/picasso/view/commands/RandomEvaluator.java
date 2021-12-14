package picasso.view.commands;

import java.util.Random;
import java.awt.Color;

import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
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
 * Evaluate a random expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 * @author Sara Sprenkle
 * @author Danish
 */
public class RandomEvaluator implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	private ExpressionTreeNode expr;

	public static List<String> xExpression = new ArrayList<String>(
			List.of("random()", "perlinBW(x,y)", "perlinColor(x,y)", "x", "cos(x)", "sin(x)", "tan(x)", "ceil(x)",
					"atan(x)", "clamp(x)", "wrap(x)", "log(x)", "floor(x)", "abs(x)"));

	public static List<String> yExpression = new ArrayList<String>(
			List.of("random()", "perlinBW(y,x)", "perlinColor(x,y)", "y", "cos(y)", "sin(y)", "tan(y)", "ceil(y)",
					"atan(y)", "abs(y)", "atan(y)", "clamp(y)", "log(y)", "floor(y)"));

	public static List<String> operator = new ArrayList<String>(List.of("-", "+", "/", "*", "%"));

	/**
	 * Outputs a randomly generated expression
	 * 
	 * @return randomExpression
	 */
	public static String generateRandom() {

		/**
		 * int indexForX = (int) (Math.random() * xExpression.size()); int indexForY =
		 * (int) (Math.random() * xExpression.size()); int indexForOperator = (int)
		 * (Math.random() * operator.size());
		 * 
		 * String op = operator.get(indexForOperator); String x =
		 * xExpression.get(indexForX); String y = yExpression.get(indexForY);
		 * 
		 * String randomExp = x + op + y;
		 */
		String expression = "";
		int numOfExpression = (int) (Math.random() * 10 + 1);
		for (int i = 0; i < numOfExpression; i++) {
			Collections.shuffle(xExpression);
			Collections.shuffle(yExpression);
			Collections.shuffle(operator);

			String rand = xExpression.get(1) + operator.get(1) + yExpression.get(1);

			expression += rand;
			if (i != numOfExpression) {
				Collections.shuffle(operator);
				expression += operator.get(1);
			}
		}

		String randomExpression = (String) expression.subSequence(0, expression.length() - 1);

		return randomExpression;

	}

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) throws NullPointerException {
		expr = createExpression();
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
		String input = generateRandom();
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