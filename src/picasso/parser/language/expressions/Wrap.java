package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Wrap function in the Picasso language.
 * @author Bennett Ehret
 *
 */

public class Wrap extends UnaryFunction{
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
		
		double tempx = x;
		double tempy = y;

		if(x<-1) {
			tempx = Math.abs(-1 - x);
			tempx = 1- tempx;
		} else if(x>1) {
			tempx = Math.abs(x-1);
			tempx = -1 + tempx;
		}
		if(y<-1) {
			tempy = Math.abs(-1 - y);
			tempy = 1- tempy;
		} else if(y>1) {
			tempy = Math.abs(y-1);
			tempy = -1 + tempy;
		}
		RGBColor result = param.evaluate(tempx, tempy);

		return result;
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

