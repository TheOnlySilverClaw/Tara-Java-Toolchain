package org.tara.compiler.tokenizer.token;

public class IdentifierToken extends Token<String> {

	public IdentifierToken(String content) {
		super(content);
	}
	
	@Override
	public boolean isIdentifier() {
		return true;
	}
}
