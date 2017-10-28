package org.tara.tools.tokenizer;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tara.compiler.tokenizer.TokenMatch;
import org.tara.compiler.tokenizer.Tokenizer;
import org.tara.compiler.tokenizer.token.IdentifierToken;
import org.tara.compiler.tokenizer.token.OperatorToken;
import org.tara.compiler.tokenizer.token.Token;
import org.tara.types.Operator;


public class TokenizerCommonCasesTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	
	@Test
	public void testIncrementVariable() {
		
		List<TokenMatch> matches = tokenizer.tokenize("a++");
		assertEquals(2, matches.size());
		
		TokenMatch firstMatch = matches.get(0);
		Token<?> firstToken = firstMatch.getToken();
		assertThat(firstToken, instanceOf(IdentifierToken.class));
		assertTrue(firstToken.isIdentifier());
		assertEquals("a", firstToken.getContent());
		
		TokenMatch secondMatch = matches.get(1);
		Token<?> secondToken = secondMatch.getToken();
		assertThat(secondToken, instanceOf(OperatorToken.class));
		assertTrue(secondToken.isOperator());
		assertEquals(Operator.INCREMENT, secondToken.getContent());
	}
	
	@Test
	public void testAssignStringLiteral() {
		
		List<TokenMatch> matches = tokenizer.tokenize("s = \"Blub\"");
		assertEquals(3, matches.size());
	}
}
