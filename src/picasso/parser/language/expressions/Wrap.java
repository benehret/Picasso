package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Wrap function in the Picasso language.
 * 
 * @author Bennett Ehret
 *
 */

public class Wrap extends UnaryFunction {
	/**
	 * Create a Wrap expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to wrap
	 */
	public Wrap(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the wrap of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the wrap of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = wrap(result.getRed());
		double green = wrap(result.getGreen());
		double blue = wrap(result.getBlue());

		return new RGBColor(red, green, blue);
	}

	public static double wrap(double color) {
		double tempcolor = color;
		while (tempcolor < -1 || tempcolor > 1) {
			if (tempcolor < -1) {
				tempcolor = Math.abs(-1 - tempcolor);
				tempcolor = 1 - tempcolor;
			} else if (tempcolor > 1) {
				tempcolor = Math.abs(tempcolor - 1);
				tempcolor = -1 + tempcolor;
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
		if (!(obj instanceof Wrap)) {
			return false;
		}
		Wrap w = (Wrap) obj;
		return param.equals(w.param);
	}
}
