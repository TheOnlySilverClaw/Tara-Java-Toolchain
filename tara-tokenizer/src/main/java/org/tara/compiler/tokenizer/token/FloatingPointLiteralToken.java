package org.tara.compiler.tokenizer.token;

public class FloatingPointLiteralToken extends NumberLiteralToken {

	public FloatingPointLiteralToken(String literal) {
		super(literal);
	}

	@Override
	public boolean isFloatingPointLiteral() {
		return true;
	}
}
