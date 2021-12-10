package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the Exponentiate sign token
 * 
 */
public class ExponentiateToken extends CharToken implements OperationInterface {
	public ExponentiateToken() {
		super(CharConstants.CARET);
		
	}

	@Override
	public final int getOrderOP() {
		return EXPONENTIAL;
	}
	@Override
	public boolean isRightAssociate() {
		return RIGHT_ASSOCIATIVE;
	}
}
