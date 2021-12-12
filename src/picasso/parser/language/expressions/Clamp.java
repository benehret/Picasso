package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Wrap function in the Picasso language.
 * 
 * @author John Adekola
 */

public class Clamp extends UnaryFunction {
	/**
	 * Create a Clamp expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to clamp
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the clamp of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the clamp of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = clamp(result.getRed());
		double green = clamp(result.getGreen());
		double blue = clamp(result.getBlue());

		return new RGBColor(red, green, blue);
	}

	public static double clamp(double color) {
		double tempcolor = color;
		while (tempcolor < -1 || tempcolor > 1) {
			if (tempcolor < -1) {
				tempcolor = -1.0;
			} else if (tempcolor > 1) {
				tempcolor = 1.0;
			}
		}
		return tempcolor;
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
		if (!(obj instanceof Clamp)) {
			return false;
		}
		Clamp w = (Clamp) obj;
		return param.equals(w.param);
	}
}
