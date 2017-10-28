package org.tara.compiler.tokenizer.token;

public class IntegerLiteralToken extends NumberLiteralToken {

	public IntegerLiteralToken(String literal) {
		super(literal);
	}

	@Override
	public boolean isIntegerLiteral() {
		return true;
	}
}
