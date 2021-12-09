package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the minus sign token
 * 
 */
public class MinusToken extends CharToken implements OperationInterface {
	public MinusToken() {
		super(CharConstants.MINUS);
	}

	@Override
	public final int getOrderOP() {
		return ADD_OR_SUBTRACT;
	}
	@Override
	public boolean isRightAssociate() {
		return LEFT_ASSOCIATIVE;
	}
}
