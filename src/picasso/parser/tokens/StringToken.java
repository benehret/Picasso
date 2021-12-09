package picasso.parser.tokens;

public class StringToken extends Token {

	public StringToken(String description) {
		super(description);
	}


	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

}
