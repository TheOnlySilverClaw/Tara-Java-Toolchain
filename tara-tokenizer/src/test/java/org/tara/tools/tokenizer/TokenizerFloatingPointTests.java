package org.tara.tools.tokenizer;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;
import org.tara.compiler.tokenizer.token.FloatingPointLiteralToken;
import org.tara.compiler.tokenizer.token.Token;

public class TokenizerFloatingPointTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	@Test
	public void testSingleDigits() {
		
		List<TokenMatch> matches = tokenizer.tokenize("1.0");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertThat(token, instanceOf(FloatingPointLiteralToken.class));
		assertTrue(token.isFloatingPointLiteral());
		assertEquals("1.0", token.getContent());
		
		assertEquals(3, match.getRange().size());
	}

	@Test
	public void testMultipleDigits() {
		
		List<TokenMatch> matches = tokenizer.tokenize("12.34");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertThat(token, instanceOf(FloatingPointLiteralToken.class));
		assertTrue(token.isFloatingPointLiteral());
		assertEquals("12.34", token.getContent());
		
		assertEquals(5, match.getRange().size());
	}
	
	@Test
	public void testNegativeNumber() {
		
		List<TokenMatch> matches = tokenizer.tokenize("-12.3");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertThat(token, instanceOf(FloatingPointLiteralToken.class));
		assertTrue(token.isFloatingPointLiteral());
		assertEquals("-12.3", token.getContent());
		
		assertEquals(5, match.getRange().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTwoConsecutiveFloatingPoints() {
		
		tokenizer.tokenize("-12..3");
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTwoSpreadFloatingPoints() {
		
		tokenizer.tokenize("-12.3.7");
		fail();
	}
}
