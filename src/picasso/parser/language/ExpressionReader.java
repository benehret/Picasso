package picasso.parser.language;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles reading the expression from a file.
 * 
 * @author Bennett Ehret
 * 
 */
public class ExpressionReader {

	private static List<String> expressionsList;
	private static String EXPRESSION_EXP_FILE;
	
	public ExpressionReader(String filename) {
		EXPRESSION_EXP_FILE = filename;
	}

	/**
	 * Get the list of expressions
	 * 
	 * @return the list of expressions
	 */
	public static List<String> getExpressionList() {
		readExpressionFromFile();
		return expressionsList;
	}

	/**
	 * Read the expressions from the expression file
	 */
	private static void readExpressionFromFile() {
		expressionsList = new ArrayList<String>();
		Scanner reader;
		try {
			reader = new Scanner(new File(EXPRESSION_EXP_FILE));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			// TODO: should pass the error up so that the user knows what the
			// problem is.
			return;
		}
		while (reader.hasNextLine()) {
			String function = reader.nextLine();
			function = function.trim();
			expressionsList.add(function);
		}
	}

}