package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the plus or "addition function".
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class AssignmentAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the assignment token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		ExpressionTreeNode expression =SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);

		// just pop the token out of the token stack instead of evaluating it
		String variable = tokens.pop().toString();
		// substring so that we just get the a part instead of the whole
		// Variable Token: part
		variable = variable.substring(variable.length() - 1);
				
		return new Assignment(variable, expression);
	}

}
