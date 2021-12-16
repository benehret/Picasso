package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RandomColor;
import picasso.parser.tokens.Token;
/**
 * Handles parsing the random function.
 * 
 * @author John Adekola
 * 
 */
public class RandomAnalyzer extends UnaryFunctionAnalyzer {
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		return new RandomColor();
	}
}
