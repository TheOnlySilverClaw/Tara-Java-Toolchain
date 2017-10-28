package org.tara.compiler.tokenizer.token;

public abstract class Token<C> {

	protected final C content;
	
	public Token(C content) {
		this.content = content;
	}

	public final C getContent() {
		return content;
	}
	
	public boolean isOperator() {
		return false;
	}
	
	public boolean isIdentifier() {
		return false;
	}
	
	public boolean isLiteral() {
		return false;
	}
	
	public boolean isNumberLiteral() {
		return false;
	}
	
	public boolean isIntegerLiteral() {
		return false;
	}
	
	public boolean isFloatingPointLiteral() {
		return false;
	}
	
	public boolean isStringLiteral() {
		return false;
	}
	
	public boolean isComment() {
		return false;
	}

	public boolean isKeyword() {
		return false;
	}
}
