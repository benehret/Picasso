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

		// TODO Auto-generated method stub

		return ASSIGN;
	}

	@Override
	public boolean isRightAssociate() {
		// TODO Auto-generated method stub
		return RIGHT_ASSOCIATIVE;
	}
}