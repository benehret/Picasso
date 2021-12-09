package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.StringToken;
import picasso.parser.tokens.operations.*;

/**
 * Tests of creating an expression tree from a string expression. Will have
 * compiler errors until some code is created.
 * 
 * @author Sara Sprenkle
 * 
 */
public class ParsedExpressionTreeTests {

	private ExpressionTreeGenerator parser;

	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}

	@Test
	public void constantExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("[1,-1, 1]");
		assertEquals(new RGBColor(1, -1, 1), e);
	}

	@Test
	public void variableExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x");
		assertEquals(new X(), e);
	}

	@Test
	public void additionExpressionTests() {
		ExpressionTreeNode e1 = parser.makeExpression("x + y");
		assertEquals(new Addition(new X(), new Y()), e1);

		// no spaces!
		ExpressionTreeNode e = parser.makeExpression("y+x");
		assertEquals(new Addition(new Y(), new X()), e);

		e = parser.makeExpression("[1,.3,-1] + y");
		assertEquals(new Addition(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x + y + [ -.51, 0, 1]");
		assertEquals(new Addition(new Addition(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	@Test
	public void SubstractionExpressionTests() {
		ExpressionTreeNode e1 = parser.makeExpression("x - y");
		assertEquals(new Substraction(new X(), new Y()), e1);

		// no spaces!
		ExpressionTreeNode e = parser.makeExpression("y-x");
		assertEquals(new Substraction(new Y(), new X()), e);

		e = parser.makeExpression("[1,.3,-1] - y");
		assertEquals(new Substraction(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x - y - [ -.51, 0, 1]");
		assertEquals(new Substraction(new Substraction(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	@Test
	public void ExponentiationExpressionTests() {
		ExpressionTreeNode e1 = parser.makeExpression("x^y");
		assertEquals(new Exponentiation(new X(), new Y()), e1);

		// no spaces!
		ExpressionTreeNode e = parser.makeExpression("y^x");
		assertEquals(new Exponentiation(new Y(), new X()), e);

		e = parser.makeExpression("[1,.3,-1] ^ y");
		assertEquals(new Exponentiation(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x ^ y ^ [ -.51, 0, 1]");
		assertEquals(new Exponentiation(new Exponentiation(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	@Test
	public void ModuloExpressionTests() {
		ExpressionTreeNode e1 = parser.makeExpression("x % y");
		assertEquals(new Modulo(new X(), new Y()), e1);

		// no spaces!
		ExpressionTreeNode e = parser.makeExpression("y%x");
		assertEquals(new Modulo(new Y(), new X()), e);

		e = parser.makeExpression("[1,.3,-1] % y");
		assertEquals(new Modulo(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x % y %[ -.51, 0, 1]");
		assertEquals(new Modulo(new Modulo(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	@Test
	public void DivisionExpressionTests() {
		ExpressionTreeNode e1 = parser.makeExpression("x / y");
		assertEquals(new Division(new X(), new Y()), e1);

		// no spaces!
		ExpressionTreeNode e = parser.makeExpression("y/x");
		assertEquals(new Division(new Y(), new X()), e);

		e = parser.makeExpression("[1,.3,-1] / y");
		assertEquals(new Division(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x / y / [ -.51, 0, 1]");
		assertEquals(new Division(new Division(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	@Test
	public void MultiplicationExpressionTests() {
		ExpressionTreeNode e1 = parser.makeExpression("x * y");
		assertEquals(new Multiplication(new X(), new Y()), e1);

		// no spaces!
		ExpressionTreeNode e = parser.makeExpression("y*x");
		assertEquals(new Multiplication(new Y(), new X()), e);

		e = parser.makeExpression("[1,.3,-1] * y");
		assertEquals(new Multiplication(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x * y *[ -.51, 0, 1]");
		assertEquals(new Multiplication(new Multiplication(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}
	@Test
	public void parenthesesExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("( x + y )");
		assertEquals(new Addition(new X(), new Y()), e);

		e = parser.makeExpression("( x + (y + [ 1, 1, 1] ) )");
		assertEquals(new Addition(new X(), new Addition(new Y(), new RGBColor(1, 1, 1))), e);
	}

	@Test
	public void floorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("floor( x )");
		assertEquals(new Floor(new X()), e);

		e = parser.makeExpression("floor( x + y )");
		assertEquals(new Floor(new Addition(new X(), new Y())), e);
	}

	@Test
	public void tanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("tan( x )");
		assertEquals(new Tan(new X()), e);

		e = parser.makeExpression("tan( x + y )");
		assertEquals(new Tan(new Addition(new X(), new Y())), e);
	}

	@Test
	public void CosFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("cos( x )");
		assertEquals(new Cos(new X()), e);
		e = parser.makeExpression("cos( y )");
		assertEquals(new Cos(new Y()), e);
		e = parser.makeExpression("cos( x + y )");
		assertEquals(new Cos(new Addition(new X(), new Y())), e);
	}

	@Test
	public void CeilFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("ceil( x )");
		assertEquals(new Ceil(new X()), e);
		e = parser.makeExpression("ceil( y )");
		assertEquals(new Ceil(new Y()), e);
		e = parser.makeExpression("ceil( x + y )");
		assertEquals(new Ceil(new Addition(new X(), new Y())), e);
	}
	
	@Test
	public void WrapFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("wrap( x )");
		assertEquals(new Wrap(new X()), e);
		e = parser.makeExpression("wrap( y )");
		assertEquals(new Wrap(new Y()), e);
		e = parser.makeExpression("wrap( x + y )");
		assertEquals(new Wrap(new Addition(new X(), new Y())), e);
		e = parser.makeExpression("wrap( x + x )");
		assertEquals(new Wrap(new Addition(new X(), new X())), e);
	}
	
	@Test
	public void ImageWrapFunctionTests() throws IOException {
		ExpressionTreeNode e = parser.makeExpression("imageWrap( \"vortex.jpg\", x,y+y )");
		assertEquals(new ImageWrap("vortex.jpg",new X(),new Addition(new Y(), new Y())), e);
		e = parser.makeExpression("imageWrap( \"vortex.jpg\", x+x,y )");
		assertEquals(new ImageWrap("vortex.jpg",new Addition(new X(), new X()), new Y()), e);
	}
}
