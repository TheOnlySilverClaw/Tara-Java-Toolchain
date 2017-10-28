package org.tara.compiler.tokenizer.token;

public abstract class LiteralToken extends Token<String> {

	public LiteralToken(String literal) {
		super(literal);
	}
	
	@Override
	public boolean isLiteral() {
		return true;
	}
}
