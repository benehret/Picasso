package picasso.parser.language.expressions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import picasso.parser.language.expressions.Wrap;
import picasso.model.Pixmap;
import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;
import picasso.view.commands.Evaluater;

public class ImageWrap extends ExpressionTreeNode {

	ExpressionTreeNode param;
	ExpressionTreeNode param2;
	BufferedImage myImage;
	/**
	 * 
	 * @param param
	 * @throws IOException 
	 */
	public ImageWrap(String image, ExpressionTreeNode param,ExpressionTreeNode param2) {
		this.param = param;
		this.param2=param2;
		try {
			this.myImage = ImageIO.read(new File("./images/"+image));
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

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof ImageWrap)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		ImageWrap uf = (ImageWrap) o;

		// check if their parameters are equal
		if (!this.param.equals(uf.param)) {
			return false;
		}
		if (!this.param2.equals(uf.param2)) {
			return false;
		}
		return true;
	}
	
	/**
	 * for testing purposes
	 * @return myImage
	 */
	public BufferedImage getMyImage() {
		return myImage;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the wrap of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the wrap of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor resultx = param.evaluate(x, y);
		RGBColor resulty = param2.evaluate(x,y);
		double redx = Wrap.wrap(resultx.getRed());
		double redy = Wrap.wrap(resulty.getRed());
		//convert redx and redy to BufferedImage scale
		int xval = domainToImageScale(redx, 2);
		int yval = domainToImageScale(redy, 2);
		//use converted values to get RGB value at image coords
		Color intc = new Color(myImage.getRGB(xval, yval));
		RGBColor doublec = new RGBColor(intc);
		
		return doublec;
	}
	//make method that converts from domain to image 
	public int domainToImageScale(double value, int bounds) {
		double range = myImage.getWidth();
		return (int)((value-(-1)*range)/bounds);
	}
	/*
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}
	*/
	
	//domain to image method, value is double from x,y in evaluate, bounds is dimension of myImage
}
