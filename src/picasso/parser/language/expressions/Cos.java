package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Cosine function in the Picasso language.
 * 
 * @author John Adekola
 * 
 */
public class Cos extends UnaryFunction {
	/**
	 * Create a Cosine expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to Cosine
	 */
	public Cos(ExpressionTreeNode param) {
		super(param);
	}
	/**
	 * Evaluates this expression at the given x,y point by evaluating the floor of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the floor of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);	
		// convert to cosine 
		double red = Math.cos(result.getRed());
		double green = Math.cos(result.getGreen());
		double blue = Math.cos(result.getBlue());
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
		if (!(obj instanceof Cos)) {
			return false;
		}
		Cos f = (Cos) obj;
		return param.equals(f.param);
	}
}
