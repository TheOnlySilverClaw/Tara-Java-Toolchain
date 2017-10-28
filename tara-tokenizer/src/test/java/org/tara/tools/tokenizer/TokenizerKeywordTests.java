package org.tara.tools.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;
import org.tara.compiler.tokenizer.token.KeywordToken;
import org.tara.compiler.tokenizer.token.Token;
import org.tara.keywords.Keyword;


public class TokenizerKeywordTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	@Test
	public void testIf() {
		
		List<TokenMatch> matches = tokenizer.tokenize("if");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		
		assertThat(token, instanceOf(KeywordToken.class));
		assertTrue(token.isKeyword());
		assertEquals(Keyword.IF, token.getContent());
		assertEquals(2, match.getRange().size());
	}
	
	@Test
	public void testWhile() {
		
		List<TokenMatch> matches = tokenizer.tokenize("while");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		
		assertThat(token, instanceOf(KeywordToken.class));
		assertTrue(token.isKeyword());
		assertEquals(Keyword.WHILE, token.getContent());
		assertEquals(5, match.getRange().size());
	}
	
	@Test
	public void testPackage() {
		
		List<TokenMatch> matches = tokenizer.tokenize("package");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		
		assertThat(token, instanceOf(KeywordToken.class));
		assertTrue(token.isKeyword());
		assertEquals(Keyword.PACKAGE, token.getContent());
		assertEquals(7, match.getRange().size());
	}
}
