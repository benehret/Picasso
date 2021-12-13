package picasso.parser.language.expressions;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.io.*;
import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;
/**
 * Represents the ImageEvaluater abstract class  in the Picasso language that all image related functions should inherit from .
 * 
 * @author John Adekola
 */

public abstract class ImageEvaluate extends ExpressionTreeNode {

	protected ExpressionTreeNode param;
	protected ExpressionTreeNode param2;
	protected static BufferedImage myImage;
	protected static int bounds=2;
	/**
	 * 
	 * @param param first expression
	 * @param param2 second expression
	 * @param image image the function is evaluating
	 * @throws IOException if image is not valid
	 */
	public ImageEvaluate(String image, ExpressionTreeNode param,ExpressionTreeNode param2) {
		this.param = param;
		this.param2=param2;
		try {
		myImage = ImageIO.read(new File("./images/"+image));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ParseException("Image is not valid.");
		}
	}

	/**
	 * Returns the string representation of the function in the format "<ClassName>:
	 * <parameter>"
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String classname = this.getClass().getName();
		return classname.substring(classname.lastIndexOf(".")) + "(" + param + ")" + "("+ param2 + ")";
	}
	/**
	 * for testing purposes
	 * @return myImage
	 */
	public BufferedImage getMyImage() {
		return myImage;
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true; 
		}

		if (!(o instanceof ImageEvaluate)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		ImageEvaluate uf = (ImageEvaluate) o;

		// check if their parameters are equal
		if (!this.param.equals(uf.param)) {
			return false;
		}
		if (!this.param2.equals(uf.param2)) {
			return false;
		}
		if (!myImage.equals(uf.myImage)) {
			return false;
				
		}
		return true; 
	}
	
	

	/**
	 * Evaluates the value to give the pixel
	 * @param value the given value from the evaluations
	 * @return the x pixel
	 */
	 public static int domainToImageScaleX(double value) {
			
			int range = myImage.getWidth();
			if (value == 1) {
				return range-1;
			}
			return (int)((((value - (-1)) * range) / bounds));
		}
	 /**
		 * Evaluates the value to give the pixel
		 * @param value the given value from the evaluations
		 * @return the y pixel
		 */
		public static int domainToImageScaleY(double value) {
			int range = myImage.getHeight();

			if (value == 1) {
				return range-1;
			}

			return (int)((((value - (-1)) * range) / bounds));
		}

	
}
