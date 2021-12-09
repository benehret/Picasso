package picasso.parser.tokens.operations;

import picasso.parser.tokens.Token;

/**
 * A marker interface
 * 
 */
public interface OperationInterface {
	 static final int CONSTANT = 0;
	 static final int GROUPING = 1; // parentheses
	 static final int UNARY = 2;
	 static final int EXPONENTIAL=3;
	 static final int MULTIPLY_OR_DIVIDE = 4;
	 static final int ADD_OR_SUBTRACT = 5;
	 static final int ASSIGN=6;
	/**
	 * @return the orderOP
	 */
	public int getOrderOP();
	
}
