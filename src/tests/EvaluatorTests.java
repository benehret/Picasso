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
	public void testCeilEvealuator()
	/*
	{
		ExpressionTreeNode e = parser.makeExpression("ceil(x)");
		for (int i = 0; i <= 1; i ++)
		{
			assertEquals(new RGBColor (1, 1, 1), e.evaluate(i, i));
		}
		
	}
	*/
	{
		ExpressionTreeNode e = parser.makeExpression("ceil(x)");
		Ceil c = new Ceil(e);
		assertEquals(new RGBColor (-1,-1,-1), c.evaluate(-1, -1));
		assertEquals(new RGBColor (0, 0, 0), c.evaluate(0, 0));
		assertEquals(new RGBColor (1, 1, 1), c.evaluate(1, 1));
		
	}
	// TODO: More tests of evaluation

}
