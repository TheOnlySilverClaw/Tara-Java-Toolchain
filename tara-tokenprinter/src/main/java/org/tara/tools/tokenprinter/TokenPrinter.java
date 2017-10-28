package org.tara.tools.tokenprinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;
import org.tara.compiler.tokenizer.token.Token;
import org.tara.util.text.TextPosition;
import org.tara.util.text.TextRange;

public class TokenPrinter {

	public static void main(String[] args) throws IOException {
		
		String name = args[0];
		Path path = Paths.get(name);
		
		String content = String.join("\n", Files.readAllLines(path));
				
		Tokenizer tokenizer = new Tokenizer();
		List<TokenMatch> matches = tokenizer.tokenize(content);
		
		for(TokenMatch match : matches) {

			Token<?> token = match.getToken();
			TextRange range = match.getRange();
			TextPosition start = range.getStart();
			TextPosition end = range.getEnd();
			
			System.out.printf("%-16s %-12s from %d (line %d, column %d) to %d (line  %d, column %d)\n",
					token.getClass().getSimpleName(),
					token.getContent(),
					start.getIndex(),
					start.getLine(),
					start.getColumn(),
					end.getIndex(),
					end.getLine(),
					end.getColumn()
					);
		}
	}
}
