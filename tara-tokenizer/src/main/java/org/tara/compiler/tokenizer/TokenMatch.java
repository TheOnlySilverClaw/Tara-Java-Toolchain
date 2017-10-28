package org.tara.compiler.tokenizer;

import org.tara.compiler.tokenizer.token.Token;
import org.tara.util.text.TextRange;

public class TokenMatch {

	private final TextRange range;
	private final Token<?> token;
	
	public TokenMatch(TextRange range, Token<?> token) {
		this.range = range;
		this.token = token;
	}
	
	public final TextRange getRange() {
		return range;
	}
	
	public final Token<?> getToken() {
		return token;
	}
}
