package picasso.parser.language.expressions;

import java.awt.*;

import picasso.parser.language.ExpressionTreeNode;
/**
 * Represents the image wrap function  in the picasso programming language
 * 
 * @author Bennett 
 * @author John Adekola
 */
public class ImageWrap extends ImageEvaluate {


	public ImageWrap(String image, ExpressionTreeNode param,ExpressionTreeNode param2) {
		super(image,param,param2);
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
		int xval = domainToImageScaleX(redx);
		int yval = domainToImageScaleY(redy);
		//use converted values to get RGB value at image coords
		Color intc = new Color(myImage.getRGB(xval, yval));
	
		RGBColor doublec = new RGBColor(intc);
		
		return doublec;
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
		if (!myImage.equals(uf.myImage)) {
			return false;
				
		}
		return true; 
	}
	

}
