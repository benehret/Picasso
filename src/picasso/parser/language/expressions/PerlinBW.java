package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.model.ImprovedNoise;

/**
 * Represents the PerlinBW function in the Picasso language.
 * 
 * @author John Adekola
 * 
 */
public class PerlinBW extends BinaryFunction {

	/**
	 * Create a PerlinBW  expression that takes two parameters for the given expression
	 * 
	 * @param param an expression to PerlinBW
	 * @param param2 an expression to PerlinBW
	 */
	public PerlinBW(ExpressionTreeNode param, ExpressionTreeNode param2) {
		super(param,param2);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the PerlinBW of
	 * the function's parameters
	 * 
	 * @return the color from evaluating the PerlinBW of the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor left = param.evaluate(x, y);
		RGBColor right = param2.evaluate(x, y);
		
		double grey = ImprovedNoise.noise(left.getRed() + right.getRed(), left.getGreen() + right.getGreen(),
				left.getBlue() + right.getBlue());
		return new RGBColor(grey, grey, grey);
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
		if (!(obj instanceof PerlinBW)) {
			return false;
		}
		PerlinBW f = (PerlinBW) obj;
		if( !param.equals(f.param)) {
			return false;
		}
		return param2.equals(f.param2);
	}

}
