package org.tara.tools.tokenizer;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;


public class TokenizerEdgeCasesTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	@Test
	public void testEmpty() {
		
		List<TokenMatch> matches = tokenizer.tokenize("");
		assertEquals(0, matches.size());
	}

	@Test()
	public void testSingleNewline() {
		
		List<TokenMatch> matches = tokenizer.tokenize("\n");
		assertEquals(0, matches.size());
	}
}
