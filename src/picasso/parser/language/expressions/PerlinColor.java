package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.model.ImprovedNoise;
/**
 * Represents the PerlinColor function in the Picasso language.
 * 
 * @author John Adekola
 * 
 */
public class PerlinColor extends BinaryFunction {

	/**
	 * Create a PerlinColor  expression that takes two parameters for the given expression
	 * 
	 * @param param an expression to PerlinColor
	 * @param param2 an expression to PerlinColor
	 */
	public PerlinColor(ExpressionTreeNode param, ExpressionTreeNode param2) {
		super(param,param2);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the PerlinColor of
	 * the function's parameters
	 * 
	 * @return the color from evaluating the PerlinColor of the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor left = param.evaluate(x, y);
		RGBColor right = param2.evaluate(x, y);
		
		double red = ImprovedNoise.noise(left.getRed() + 0.3, right.getRed() + 0.3, 0);
		double blue = ImprovedNoise.noise(left.getBlue() + 0.1, right.getBlue() + 0.1, 0);
		double green = ImprovedNoise.noise(left.getGreen() - 0.8, right.getGreen() - 0.8, 0);
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
		if (!(obj instanceof PerlinColor)) {
			return false;
		}
		PerlinColor f = (PerlinColor) obj;
		if( !param.equals(f.param)) {
			return false;
		}
		return param2.equals(f.param2);
	}

}
