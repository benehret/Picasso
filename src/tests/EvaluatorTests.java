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
	public void testManipulationEvaluation() {
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
	public void testNegateEvaluator(){
		ExpressionTreeNode e = parser.makeExpression("!x");
		Negation c = new Negation(e);
		assertEquals(new RGBColor (-1,-1,-1), c.evaluate(-1, -1));
		assertEquals(new RGBColor (0, 0, 0), c.evaluate(0, 0));
		assertEquals(new RGBColor (1,1, 1), c.evaluate(1, 1));

	}

	@Test
	public void testTanXEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("tan(x)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.tan(i),Math.tan(i),Math.tan(i)), e.evaluate(i,i));
		}
	}

	@Test
	public void testTanYEvaluation() {
		ExpressionTreeNode e = parser.makeExpression("tan(y)");
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.tan(i),Math.tan(i),Math.tan(i)), e.evaluate(i,i));
		}
	}
	
}
