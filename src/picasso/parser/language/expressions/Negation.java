package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Negation operator in the Picasso language.
 * @author John Adekola
 *
 */

public class Negation extends UnaryFunction{
	/**
	 * Create a Negation expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to Negate
	 */
	public Negation(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the Negation of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the Negation operator of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		return new RGBColor(-result.getRed(), -result.getGreen(), -result.getBlue());
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
		if (!(obj instanceof Negation)) {
			return false;
		}
		Negation t = (Negation) obj;
		return param.equals(t.param);
	}
}
