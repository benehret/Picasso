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
	/**
	 * @return the orderOP
	 */
	public final int getOrderOP() {
		return 4;
	}
}
