package picasso.parser.tokens;

public class StringToken extends Token {

	private final String value;
	public StringToken(String value) {
		super("String Token");
		this.value = value;
	}


	public boolean equals(Object o) {
		if( o == this ) {
			return true;
		}
		if (!(o instanceof StringToken)) {
			return false;
		}
		StringToken oStrToken = (StringToken) o;
		return value.equals(oStrToken.value);
	}
	
	public String getValue() {
		return value;
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
