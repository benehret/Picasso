package picasso.parser.tokens.operations;

import picasso.parser.tokens.Token;

/**
 * An  interface for operators to implement
 * @author John
 * 
 */
public interface OperationInterface {
	static final int CONSTANT = 6;
	static final int GROUPING = 5; // parentheses
	static final int UNARY = 4;
	static final int EXPONENTIAL=3;
	 static final int MULTIPLY_OR_DIVIDE = 2;
	static final int ADD_OR_SUBTRACT = 1;
	 static final int ASSIGN = 0;
	 static final boolean RIGHT_ASSOCIATIVE= true;
	 static final boolean LEFT_ASSOCIATIVE= false;
	
	/**
	 * @return the order of operation precedence of the token
	 */
	public int getOrderOP();
	/**
	 * @return if the token is right-associative 
	 */
	public boolean isRightAssociate();
}
