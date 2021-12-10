package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.language.expressions.Variable;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.CharToken;
import picasso.view.errorReporting.ErrorReporting;

/**
 * Handles parsing the equals or "assignment function".
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class AssignmentAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		System.out.println("TOKENS B4 POP: " + tokens);
		tokens.pop(); // Remove the assignment token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		ExpressionTreeNode expression =SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);

		// just pop the token out of the token stack instead of evaluating it
		// TODO Make sure that it's an identifier token if not throw an error
		Token variable = tokens.pop();
		if (!(variable instanceof IdentifierToken)|| variable.isConstant() || variable.isFunction() || variable.toString().equals( "Variable Token: x") || variable.toString().equals( "Variable Token: y"))
		{
			IllegalArgumentException e = new IllegalArgumentException("Can't rename preexisting variables/functions.");
			ErrorReporting.reportException(e);
			return null;
		}
		else {
			String assignedVariable = variable.toString();
			// substring so that we just get the a part instead of the whole
			// Variable Token: part
			assignedVariable = assignedVariable.substring(assignedVariable.length() - 1);

			return new Assignment(assignedVariable, expression);
		}
	}

}