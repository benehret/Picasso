package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Multiplication operator in the Picasso language.
 * 
 * @author John Adekola
 * 
 */
public class Multiplication extends BinaryFunction {

	/**
	 * Create a Multiplication expression that takes as a parameter two  given expressions
	 * 
	 * @param param the first expression
	 * @param2 param2 the second expression
	 */
	public Multiplication(ExpressionTreeNode param, ExpressionTreeNode param2) {
		super(param,param2);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the Multiplication of
	 * the function's parameters.
	 * 
	 * @return the color from evaluating the Multiplication of the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		RGBColor result2 = param2.evaluate(x, y);
		
		double red = result.getRed()* result2.getRed();
		double green = result.getGreen()* result2.getGreen();
		double blue = result.getBlue()* result2.getBlue();

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
		if (!(obj instanceof Multiplication)) {
			return false;
		}
		Multiplication f = (Multiplication) obj;
		if( !param.equals(f.param)) {
			return false;
		}
		return param2.equals(f.param2);
	}

}
