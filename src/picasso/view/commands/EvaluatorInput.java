package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import picasso.view.Frame;
import picasso.view.errorReporting.ErrorReporting;
import picasso.Main;
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

	public EvaluatorInput(JTextField field)
	{
		myTextField = field;
	}
	
	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) {
		ExpressionTreeNode expr = createExpression();
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
		
		//String input = whatever we get form the JTExtBox
		String input = myTextField.getText();
		
		System.out.println("INPUT: " + input);
		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		// ExpressionTreeNode expression = expTreeGen.makeExpression(input); 
		try 
		{
			return expTreeGen.makeExpression(input);
		}
		catch (ParseException e)
		{
			//System.out.println("Crap");
			ErrorReporting.reportParseException(e);
			// How can I do this without having this return statement here?
			return expTreeGen.makeExpression(input);
		}
	}

}