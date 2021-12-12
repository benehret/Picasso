package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the absolute  function in the Picasso language.
 * 
 * @author John Adekola
 */
public class Absolute extends UnaryFunction {

	/**
	 * Create a absolute expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to absolute
	 */
	public Absolute(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the absolute of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the absolute of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.abs(result.getRed());
		double green = Math.abs(result.getGreen());
		double blue = Math.abs(result.getBlue());

		return new RGBColor(red, green, blue);
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
		if (!(obj instanceof Absolute)) {
			return false;
		}
		Absolute f = (Absolute) obj;
		return param.equals(f.param);
	}

}
