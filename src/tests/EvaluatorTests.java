/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of the evaluation of x
 * 
 * @author Sara Sprenkle
 * 
 */
public class EvaluatorTests {

	private ExpressionTreeGenerator parser;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}

	@Test
	public void testConstantEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("[1, -1, 1]");
		assertEquals(new RGBColor(1, -1, 1), e);
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(1, -1, 1), e.evaluate(i, i));
		}
	}

	@Test
	public void testXEvaluation() {
		X x = new X();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), x.evaluate(i, i));
		}
	}
	
	@Test
	public void testCosXEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("cos(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.cos(i),Math.cos(i),Math.cos(i)), e.evaluate(i,i));
		}
	}
	@Test
	public void testCosYEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("cos(y)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.cos(i),Math.cos(i),Math.cos(i)), e.evaluate(i,i));
		}
	}
	@Test
	public void testAdditionEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("x+y");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i+i,i+i,i+i), e.evaluate(i,i));
		}
	}
	@Test
	public void testModuloEvaluation() {
		ExpressionTreeNode c = parser.makeExpression("x%y");
		assertEquals(new RGBColor (-1%-1,-1%-1,-1%-1), c.evaluate(-1, -1));
		assertEquals(new RGBColor (0, 0, 0), c.evaluate(0, 0));
		assertEquals(new RGBColor (1%1, 1%1, 1%1), c.evaluate(1, 1));
		
	}
	@Test
	public void testDivisionEvaluation() {
		ExpressionTreeNode c = parser.makeExpression("x/y");
		assertEquals(new RGBColor (-1/-1,-1/-1,-1/-1), c.evaluate(-1, -1));
		assertEquals(new RGBColor (0, 0, 0), c.evaluate(0, 0));
		assertEquals(new RGBColor (1/1, 1/1, 1/1), c.evaluate(1, 1));
	} 
	@Test
	public void testMultiplicationEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("x*y");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i*i,i*i,i*i), e.evaluate(i,i));
		}
	}
	@Test
	public void testExponentiationEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("x^y");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.pow(i, i),Math.pow(i, i),Math.pow(i, i)), e.evaluate(i,i));
		}
	}
	@Test
	public void testSubstractionEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("x-y");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i-i,i-i,i-i), e.evaluate(i,i));
		}
	}
	@Test
	public void testSinXEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("sin(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.sin(i),Math.sin(i),Math.sin(i)), e.evaluate(i,i));
		}
	}
	
	@Test
	public void testSinYEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("sin(y)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.sin(i),Math.sin(i),Math.sin(i)), e.evaluate(i,i));
		}
	}

	@Test
	public void testCeilEvaluator() {
		ExpressionTreeNode e = parser.makeExpression("ceil(x)");
		Ceil c = new Ceil(e);
		assertEquals(new RGBColor (-1,-1,-1), c.evaluate(-1, -1));
		assertEquals(new RGBColor (0, 0, 0), c.evaluate(0, 0));
		assertEquals(new RGBColor (1, 1, 1), c.evaluate(1, 1));
	}
	
	@Test
	public void testAssignmentEvaluator() {
		ExpressionTreeNode e = parser.makeExpression("a = x + y");
		assertEquals(new RGBColor(1,1,1),e.evaluate(1, 0));
		assertEquals(new RGBColor(1,1,1),e.evaluate(0, 0));
		
	}	
	@Test
	public void testNegateEvaluator(){
		ExpressionTreeNode e = parser.makeExpression("!x");
		Negation c = new Negation(e);
		assertEquals(new RGBColor (-1,-1,-1), c.evaluate(-1, -1));
		assertEquals(new RGBColor (0, 0, 0), c.evaluate(0, 0));
		assertEquals(new RGBColor (1,1, 1), c.evaluate(1, 1));

	}
	@Test
	public void testrgbToYCrCbEvaluator(){
		ExpressionTreeNode e = parser.makeExpression("rgbToYCrCb(x)");
		RGBColor f= e.evaluate(0, 0);
		assertEquals(new RGBColor (0, 0, 0), f.evaluate(0, 0));
		RGBColor g= e.evaluate(1, 1);
		assertEquals(new RGBColor (1.0, 9.999999999998899E-5,9.999999999998899E-5), g.evaluate(1,1));
	}
	@Test
	public void testyCrCbtoRGBEvaluator(){
		ExpressionTreeNode e = parser.makeExpression("yCrCbtoRGB(x)");
		RGBColor f= e.evaluate(0, 0);
		assertEquals(new RGBColor (0, 0, 0), f.evaluate(0, 0));
		RGBColor g= e.evaluate(1, 1);
		assertEquals(new RGBColor (2.4021999999999997, -0.06010000000000004, 2.771), g.evaluate(1, 1));
	
	}
	@Test
	public void testPerlinColorEvaluator(){
		ExpressionTreeNode e = parser.makeExpression("perlinColor(x,y)");
		RGBColor f= e.evaluate(0, 0);
	
		assertEquals(new RGBColor (0.02276399999999995, -0.24566505471999994, 0.083736), f.evaluate(0, 0));
		RGBColor g= e.evaluate(1, 1); 
	
		assertEquals(new RGBColor (0.45749417280000004,0.09574400000000001 , 0.19672254720000015), g.evaluate(1, 1));
	
	}
	@Test
	public void testPerlinBWEvaluator(){
		ExpressionTreeNode e = parser.makeExpression("perlinBW(x,y)");
		RGBColor f= e.evaluate(0, 0);
		
		assertEquals(new RGBColor (0, 0, 0), f.evaluate(0, 0));
		RGBColor g= e.evaluate(1, 1); 
	
		assertEquals(new RGBColor  (0, 0, 0), g.evaluate(1, 1));
	
	}
	@Test
	public void testRandomEvaluator(){
		ExpressionTreeNode e = parser.makeExpression("random");
		boolean result=true;
		RGBColor f=e.evaluate(0, 0);
		if (f.getRed()>1||f.getBlue()>1||f.getGreen()>1||f.getBlue()<-1||f.getGreen()<-1||f.getRed()<-1) {
			result=false;
		}
		RGBColor g=e.evaluate(1, 1);
		if (g.getRed()>1||g.getBlue()>1||g.getGreen()>1||g.getBlue()<-1||g.getGreen()<-1||g.getRed()<-1) {
			result=false;
		}
		RGBColor h=e.evaluate(-1,-1);
		if (h.getRed()>1||h.getBlue()>1||h.getGreen()>1||h.getBlue()<-1||h.getGreen()<-1||h.getRed()<-1) {
			result=false;
		}
		assertTrue(result);
	}

	@Test
	public void testTanXEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("tan(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.tan(i),Math.tan(i),Math.tan(i)), e.evaluate(i,i));
		}
	}
	@Test
	public void testAbsEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("abs(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.abs(i),Math.abs(i),Math.abs(i)), e.evaluate(i,i));
		}
	}
	@Test
	public void tesExpEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("exp(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.exp(i),Math.exp(i),Math.exp(i)), e.evaluate(i,i));
		}
	}
	@Test
	public void testLogEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("log(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.log(Math.abs(i)),Math.log(Math.abs(i)),Math.log(Math.abs(i))), e.evaluate(i,i));
		}
	}
	@Test
	public void testTanYEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("tan(y)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.tan(i),Math.tan(i),Math.tan(i)), e.evaluate(i,i));
		}
	}
	
	@Test
	public void testWrapXEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("wrap(x+x)");
		assertEquals(new RGBColor(0,0,0), e.evaluate(-1, -1));
		assertEquals(new RGBColor(0,0,0), e.evaluate(1, 1));
		e = parser.makeExpression("wrap(x+x+x)");
		assertEquals(new RGBColor(-1,-1,-1), e.evaluate(-1, -1));
		assertEquals(new RGBColor(1,1,1), e.evaluate(1, 1));
		e = parser.makeExpression("wrap(x+x+x+x)");
		assertEquals(new RGBColor(0,0,0), e.evaluate(-1, -1));
		assertEquals(new RGBColor(0,0,0), e.evaluate(1, 1));
	}
	@Test
	public void testClampEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("clamp(x+x)");
		assertEquals(new RGBColor(-1,-1,-1), e.evaluate(-1, -1));
		assertEquals(new RGBColor(1,1,1), e.evaluate(1, 1));
		e = parser.makeExpression("clamp(x+x+x)");
		assertEquals(new RGBColor(-1,-1,-1), e.evaluate(-1, -1));
		assertEquals(new RGBColor(1,1,1), e.evaluate(1, 1));
		e = parser.makeExpression("clamp(x+x+x+x)");
		assertEquals(new RGBColor(-1,-1,-1), e.evaluate(-1, -1));
		assertEquals(new RGBColor(1,1,1), e.evaluate(1, 1));
	}
	
	@Test
	public void testImageWrapEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("imageWrap(\"vortex.jpg\",x+x,y)");
		assertEquals(new RGBColor(new Color(((ImageWrap)e).getMyImage().getRGB(((ImageWrap)e).getMyImage().getWidth()/2, ((ImageWrap)e).getMyImage().getHeight()/2))), e.evaluate(-1, 0));
		 e = parser.makeExpression("imageWrap(\"vortex.jpg\",x+x,y)");
		assertEquals(new RGBColor(new Color(((ImageWrap)e).getMyImage().getRGB(((ImageWrap)e).getMyImage().getWidth()/2, ((ImageWrap)e).getMyImage().getHeight()/2))), e.evaluate(1, 0));
	}
	//image clip not working
	@Test
	public void testImageClipEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("imageClip(\"vortex.jpg\",x+x,y)");
		assertEquals(new RGBColor(new Color(((ImageClip)e).getMyImage().getRGB(((ImageClip)e).getMyImage().getWidth()/2, ((ImageClip)e).getMyImage().getHeight()/2))), e.evaluate(-1, 0));
		 e = parser.makeExpression("imageClip(\"vortex.jpg\",x+x,y)");
		assertEquals(new RGBColor(new Color(((ImageClip)e).getMyImage().getRGB(((ImageClip)e).getMyImage().getWidth()/2, ((ImageClip)e).getMyImage().getHeight()/2))), e.evaluate(1, 0));
	}
	@Test
	public void testDomainToImageScaleXImageWrap() {
		ExpressionTreeNode e = parser.makeExpression("imageWrap(\"vortex.jpg\",x+x,y)");
		assertEquals(0,((ImageWrap)e).domainToImageScaleX((double)-1,2));
		assertEquals(255,((ImageWrap)e).domainToImageScaleX((double)1,2));
	}
	
	@Test
	public void testDomainToImageScaleYImageWrap() {
		ExpressionTreeNode e = parser.makeExpression("imageWrap(\"vortex.jpg\",x+x,y)");
		assertEquals(0,((ImageWrap)e).domainToImageScaleY((double)-1,2));
		assertEquals(255,((ImageWrap)e).domainToImageScaleY((double)1,2));
	}
	@Test
	public void testDomainToImageScaleXImageClip() {
		ExpressionTreeNode e = parser.makeExpression("imageClip(\"vortex.jpg\",x+x,y)");
		assertEquals(0,((ImageClip)e).domainToImageScaleX((double)-1,2));
		assertEquals(255,((ImageClip)e).domainToImageScaleX((double)1,2));
	}
	
	@Test
	public void testDomainToImageScaleYImageClip() {
		ExpressionTreeNode e = parser.makeExpression("imageClip(\"vortex.jpg\",x+x,y)");
		assertEquals(0,((ImageClip)e).domainToImageScaleY((double)-1,2));
		assertEquals(255,((ImageClip)e).domainToImageScaleY((double)1,2));
	}
	@Test
	public void testParenthesis() {
		ExpressionTreeNode e = parser.makeExpression("(x+x)*(x+.5)-(y+1)");
		// final answer=1
		int x= 1;
		assertEquals(new RGBColor(x,x,x),e.evaluate(1, 1));
		e = parser.makeExpression("(x+x)*(x+.5)-(y)+(y+1)");
		assertEquals(new RGBColor(x,x,x),e.evaluate(0, 0));
		e = parser.makeExpression("(x+x)*(x+.5)-(y+1)");
		assertEquals(new RGBColor(x,x,x),e.evaluate(-1, -1));
	}
}
