package org.tara.compiler.tokenizer.token;

public class StringLiteralToken extends LiteralToken {

	public StringLiteralToken(String literal) {
		super(literal);
	}
	
	@Override
	public boolean isStringLiteral() {
		return true;
	}
}
