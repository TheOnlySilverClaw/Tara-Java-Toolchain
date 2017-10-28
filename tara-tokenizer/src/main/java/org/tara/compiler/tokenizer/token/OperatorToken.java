package org.tara.compiler.tokenizer.token;

import org.tara.types.Operator;

public class OperatorToken extends Token<Operator> {
	
	public OperatorToken(Operator operator) {
		super(operator);
	}
	
	@Override
	public boolean isOperator() {
		return true;
	}
}
