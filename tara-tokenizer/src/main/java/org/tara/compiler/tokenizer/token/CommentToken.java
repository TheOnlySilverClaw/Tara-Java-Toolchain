package org.tara.compiler.tokenizer.token;

public class CommentToken extends Token<String> {

	public CommentToken(String comment) {
		super(comment);
	}
	
	@Override
	public boolean isComment() {
		return true;
	}
}
