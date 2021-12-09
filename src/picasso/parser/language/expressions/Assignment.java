package picasso.parser.language.expressions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Assignment operator in the Picasso language.
 * 
 * @author John Adekola
 */
public class Assignment extends Variable {
	private static Map<String, ExpressionTreeNode> assignment = new HashMap<>();
	private String param;
	private ExpressionTreeNode param2;
	private static JFrame myFrame;

//Enforoce must be an identifier token

	/**
	 * Create an Assignment expression that takes as a parameter two  given expressions
	 * 
	 * @param variable the first expression
	 * @param2 param2 the second expression
	 */
	public Assignment(String variable, ExpressionTreeNode expression) {
		super (variable);
//		this.param = variable;
//		this.param2 = expression;
		JOptionPane.showMessageDialog(myFrame, "Assignment saved!");
		assignment.put(variable, expression);

	}
	
	

	/**
	 * Evaluates this expression at the given x,y point by evaluating the expression of
	 * the function's parameters.
	 * 
	 * @return the color from evaluating the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		// TODO ? make it so that when you first assign, you get the expression back?
		return new RGBColor(1, 1, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Assignment)) {
			return false;
		}
		Assignment f = (Assignment) obj;
		if( !param.equals(f.param)) {
			return false;
		}
		return param2.equals(f.param2);
	}

	public static Map<String, ExpressionTreeNode> getHashMap()
	{
		return assignment;
	}
}
