package picasso.view.commands;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.awt.Dimension;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Scanner;
import java.awt.Dimension;


/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author john
 */
public class EvaluaterInput implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) {
		  TextBox happy= new TextBox();
		 String result= happy.getResult();
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
