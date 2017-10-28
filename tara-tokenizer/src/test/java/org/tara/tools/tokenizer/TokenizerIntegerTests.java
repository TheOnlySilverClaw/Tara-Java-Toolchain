package org.tara.tools.tokenizer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;
import org.tara.compiler.tokenizer.token.IntegerLiteralToken;
import org.tara.compiler.tokenizer.token.Token;

public class TokenizerIntegerTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	@Test
	public void testSingleDigit() {
		
		List<TokenMatch> matches = tokenizer.tokenize("1");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertThat(token, instanceOf(IntegerLiteralToken.class));
		assertTrue(token.isIntegerLiteral());
		assertEquals("1", token.getContent());
		
		assertEquals(1, match.getRange().size());
	}

	@Test
	public void testMultipleDigits() {
		
		List<TokenMatch> matches = tokenizer.tokenize("123");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertThat(token, instanceOf(IntegerLiteralToken.class));
		assertTrue(token.isIntegerLiteral());
		assertEquals("123", token.getContent());
		
		assertEquals(3, match.getRange().size());
	}
	
	@Test
	public void testNegativeNumber() {
		
		List<TokenMatch> matches = tokenizer.tokenize("-123");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertThat(token, instanceOf(IntegerLiteralToken.class));
		assertTrue(token.isIntegerLiteral());
		assertEquals("-123", token.getContent());
		
		assertEquals(4, match.getRange().size());
	}
}
