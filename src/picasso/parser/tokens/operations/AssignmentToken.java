package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the assignment sign token
 * 
 */
public class AssignmentToken extends CharToken implements OperationInterface {
	public AssignmentToken() {
		super(CharConstants.EQUAL);
	}

	@Override
	public int getOrderOP() {
		return ASSIGN;
	}

	@Override
	public boolean isRightAssociate() {
		return RIGHT_ASSOCIATIVE;
	}
}