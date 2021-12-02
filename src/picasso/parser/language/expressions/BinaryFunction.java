package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes two arguments.
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 *@author John
 */
public abstract class BinaryFunction extends ExpressionTreeNode {

	ExpressionTreeNode param;
	ExpressionTreeNode param2;
	/**
	 * 
	 * @param param
	 */
	public BinaryFunction(ExpressionTreeNode param,ExpressionTreeNode param2) {
		this.param = param;
		this.param2=param2;
	}

	/**
	 * Returns the string representation of the function in the format "<ClassName>:
	 * <parameter>"
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String classname = this.getClass().getName();
		return classname.substring(classname.lastIndexOf(".")) + "(" + param + ")" + "("+ param2 + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof BinaryFunction)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		BinaryFunction uf = (BinaryFunction) o;

		// check if their parameters are equal
		if (!this.param.equals(uf.param)) {
			return false;
		}
		if (!this.param2.equals(uf.param2)) {
			return false;
		}
		return true;
	}

}
