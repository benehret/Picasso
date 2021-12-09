package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the times sign token
 * 
 */
public class TimesToken extends CharToken implements OperationInterface {
	public TimesToken() {
		super(CharConstants.STAR);
	}
	@Override
	public final int getOrderOP() {
		return MULTIPLY_OR_DIVIDE;
	}
	@Override
	public boolean isRightAssociate() {
	
		return LEFT_ASSOCIATIVE;
	}
}
