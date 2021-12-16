package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.tokens.StringToken;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the  for the image wrap function
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class ImageWrapAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove imageWrap  token
		// the parameters are the next tokens on the stack.
		ExpressionTreeNode param1=SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode param2=SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		if (!(tokens.peek() instanceof StringToken)) {
			throw new ParseException("Input for image is not valid.");
		}
		StringToken image = (StringToken)tokens.pop();
		String imagename = image.getValue();
		return new ImageWrap(imagename,param2,param1);
	}

}