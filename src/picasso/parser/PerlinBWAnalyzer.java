package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.PerlinBW;
import picasso.parser.tokens.Token;

/**
 * Handles parsing for the perlinbw function
 * 
 * @author John Adekola
 * 
 */
public class PerlinBWAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); 
		ExpressionTreeNode param1=SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode param2=SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
				
		return new PerlinBW(param2,param1);
	}

}
