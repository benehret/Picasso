package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exponentiation;
import picasso.parser.tokens.Token;

/**
 * Handles parsing for the Exponentiation function".
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class ExponentiateAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove minus  token
		// the parameters are the next tokens on the stack.
		ExpressionTreeNode param1=SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode param2=SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
				
		return new Exponentiation(param2,param1);
	}

}
