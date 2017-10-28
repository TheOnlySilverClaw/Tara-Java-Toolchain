package org.tara.compiler.tokenizer.token;

import org.tara.keywords.Keyword;

public class KeywordToken extends Token<Keyword> {

	public KeywordToken(Keyword keyword) {
		super(keyword);
	}
	
	@Override
	public boolean isKeyword() {
		return true;
	}
}
