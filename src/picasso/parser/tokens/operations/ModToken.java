package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the Mod sign token
 * 
 */
public class ModToken extends CharToken implements OperationInterface {
	public ModToken() {
		super(CharConstants.MOD);
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
