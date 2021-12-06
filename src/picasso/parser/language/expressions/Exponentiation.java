package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Exponentiation operator in the Picasso language.
 * 
 * @author John Adekola
 * 
 */
public class Exponentiation extends BinaryFunction {

	/**
	 * Create a Exponentiation expression that takes as a parameter two  given expressions
	 * 
	 * @param param the first expression
	 * @param2 param2 the second expression
	 */
	public Exponentiation(ExpressionTreeNode param, ExpressionTreeNode param2) {
		super(param,param2);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the Exponentiation of
	 * the function's parameters.
	 * 
	 * @return the color from evaluating the Exponentiation of the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		RGBColor result2 = param2.evaluate(x, y);
		
		double red = Math.pow(result.getRed() ,result2.getRed());
		double green = Math.pow(result.getGreen(), result2.getGreen());
		double blue = Math.pow(result.getBlue(), result2.getBlue());

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
		if (!(obj instanceof Exponentiation)) {
			return false;
		}
		Exponentiation f = (Exponentiation) obj;
		if( !param.equals(f.param)) {
			return false;
		}
		return param2.equals(f.param2);
	}

}
