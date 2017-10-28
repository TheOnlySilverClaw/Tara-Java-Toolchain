package org.tara.tools.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;
import org.tara.compiler.tokenizer.token.Token;


public class TokenizerStringTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	
	@Test
	public void testEmpty() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"\"", token.getContent());
		assertEquals(2, match.getRange().size());
	}
	
	@Test
	public void testSingleLetter() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"a\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"a\"", token.getContent());
		assertEquals(3, match.getRange().size());
	}
	
	@Test
	public void testMultipleLetters() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"aBcDefgH\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"aBcDefgH\"", token.getContent());
		assertEquals(10, match.getRange().size());
	}

	@Test
	public void testDigits() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"9842385\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"9842385\"", token.getContent());
		assertEquals(9, match.getRange().size());
	}
	
	@Test
	public void testMixed() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"aö123?!\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"aö123?!\"", token.getContent());
		assertEquals(9, match.getRange().size());
	}
	
	@Test
	public void testSpaces() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"a b  8  ö ?\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"a b  8  ö ?\"", token.getContent());
		assertEquals(13, match.getRange().size());
	}
	
	@Test
	public void testTabs() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"abc	123\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"abc	123\"", token.getContent());
		assertEquals(9, match.getRange().size());
	}
	
	@Test
	public void testEscapedWhitespace() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\"abc\\t123\\nnext\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"abc\\t123\\nnext\"", token.getContent());
		assertEquals(16, match.getRange().size());
	}
	
	@Test
	public void testEscapedQuotationMark() {
		
		List<TokenMatch> matches = tokenizer.tokenize(
				"\"He said \\\"Hey!\\\"\"");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		Token<?> token = match.getToken();
		assertEquals("\"He said \\\"Hey!\\\"\"", token.getContent());
		assertEquals(18, match.getRange().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTrailing() {
		
		tokenizer.tokenize("\"");
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUnclosedLetters() {
		
		tokenizer.tokenize("\"abc");
		fail();
	}
}
