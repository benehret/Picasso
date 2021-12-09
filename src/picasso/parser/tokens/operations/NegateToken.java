package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the Negate sign token
 * 
 */
public class NegateToken extends CharToken implements OperationInterface {
	public NegateToken() {
		super(CharConstants.BANG);
	}

	@Override
	public final int getOrderOP() {
		return UNARY;
	}
	@Override
	public boolean isRightAssociate() {
		return LEFT_ASSOCIATIVE;
	}
	
	
}
