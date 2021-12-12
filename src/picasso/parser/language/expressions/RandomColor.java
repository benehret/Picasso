package picasso.parser.language.expressions;
import java.util.Random;

/**
 * Represents the Random function in the Picasso language.
 * 
 * @author John Adekola
 * 
 */
public class RandomColor extends NoParameterFunction {
	/**
	 * Create a random color expression that takes as a parameter the given expression
	 * 
	 */
	
	public RandomColor() {
	}

	/**
	 * creates a random color
	 * 
	 * @return a random rgb value
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		Random random = new Random();
		double red = -1.0 + (1.0 - -1.0) * random.nextDouble();
		double green = -1.0 + (1.0 - -1.0) * random.nextDouble();
		double blue = -1.0 + (1.0 - -1.0) * random.nextDouble();
		return new RGBColor(red, green, blue);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Random)) {
			return false;
		}
		return true;
	}
}
