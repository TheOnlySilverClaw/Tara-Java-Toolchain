package org.tara.tools.tokenizer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;
import org.tara.compiler.tokenizer.token.CommentToken;
import org.tara.compiler.tokenizer.token.Token;


public class TokenizerCommentTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	
	@Test
	public void testEmpty() {
		
		List<TokenMatch> matches = tokenizer.tokenize("//");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		Token<?> token = match.getToken();

		assertThat(token, instanceOf(CommentToken.class));
		assertTrue(token.isComment());
		assertEquals("//", token.getContent());
		
		assertEquals(2, match.getRange().size());
	}
	
	@Test
	public void testOneWord() {
		
		List<TokenMatch> matches = tokenizer.tokenize("// Blah");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		Token<?> token = match.getToken();

		assertThat(token, instanceOf(CommentToken.class));
		assertTrue(token.isComment());
		assertEquals("// Blah", token.getContent());
		
		assertEquals(7, match.getRange().size());
	}
}
