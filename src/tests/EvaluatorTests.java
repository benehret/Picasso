/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

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
	public void testCosineXEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("cosine(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.cos(i),Math.cos(i),Math.cos(i)), e.evaluate(i,i));
		}
	}
	@Test
	public void testCosineYEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("cosine(y)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.cos(i),Math.cos(i),Math.cos(i)), e.evaluate(i,i));
		}
	}
	@Test
	public void testPlusEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("x+y");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i+i,i+i,i+i), e.evaluate(i,i));
		}
	public void testSineXEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("sine(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.sin(i),Math.sin(i),Math.sin(i)), e.evaluate(i,i));
		}
	}
	
	@Test
	public void testSineYEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("sine(y)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.sin(i),Math.sin(i),Math.sin(i)), e.evaluate(i,i));
		}
	}

	@Test
	public void testCeilEvealuator()
	{
		ExpressionTreeNode e = parser.makeExpression("ceil(x)");
		Ceil c = new Ceil(e);
		assertEquals(new RGBColor (-1,-1,-1), c.evaluate(-1, -1));
		assertEquals(new RGBColor (0, 0, 0), c.evaluate(0, 0));
		assertEquals(new RGBColor (1, 1, 1), c.evaluate(1, 1));
		

	}
	// TODO: More tests of evaluation

}
