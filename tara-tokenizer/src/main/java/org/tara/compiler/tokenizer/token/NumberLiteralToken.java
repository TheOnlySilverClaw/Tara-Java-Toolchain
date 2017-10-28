package org.tara.compiler.tokenizer.token;

public abstract class NumberLiteralToken extends LiteralToken {

	public NumberLiteralToken(String literal) {
		super(literal);
	}

	@Override
	public boolean isNumberLiteral() {
		return true;
	}
}
