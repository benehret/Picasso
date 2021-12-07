package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Negation;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the Negate function.
 * 
 * @author John Adekola 
 * 
 */
public class NegateAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the Negate token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		return new Negation(SemanticAnalyzer.getInstance().generateExpressionTree(tokens));
	}

}
