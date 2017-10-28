package org.tara.tools.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;


public class TokenizerIdentifierTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	@Test
	public void testSingleLowercaseLetter() {
		
		List<TokenMatch> matches = tokenizer.tokenize("a");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertEquals("a", match.getToken().getContent());
		assertEquals(1, match.getRange().size());
	}
	
	@Test
	public void testSingleUppercaseLetter() {
		
		List<TokenMatch> matches = tokenizer.tokenize("A");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertEquals("A", match.getToken().getContent());
		assertEquals(1, match.getRange().size());
	}
	
	@Test
	public void testMultipleLetters() {
		
		List<TokenMatch> matches = tokenizer.tokenize("abc");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertEquals("abc", match.getToken().getContent());
		assertEquals(3, match.getRange().size());
	}
	
	@Test
	public void testLetterDigit() {
		
		List<TokenMatch> matches = tokenizer.tokenize("a1");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertEquals("a1", match.getToken().getContent());
		assertEquals(2, match.getRange().size());
	}
	
	@Test
	public void testLetterDigitMix() {
		
		List<TokenMatch> matches = tokenizer.tokenize("a1b2c3");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertEquals("a1b2c3", match.getToken().getContent());
		assertEquals(6, match.getRange().size());
	}
	
	@Test
	public void testLetterUnderScore() {
		
		List<TokenMatch> matches = tokenizer.tokenize("a_");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertEquals("a_", match.getToken().getContent());
		assertEquals(2, match.getRange().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUnderScoreOnly() {
		
		tokenizer.tokenize("_");
		fail();
	}
	
	@Test
	public void testAll() {
		
		List<TokenMatch> matches = tokenizer.tokenize("aA_1_b_3");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertEquals("aA_1_b_3", match.getToken().getContent());
		assertEquals(8, match.getRange().size());
	}
}
