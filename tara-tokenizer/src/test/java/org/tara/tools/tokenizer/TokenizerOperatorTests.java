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
import org.tara.compiler.tokenizer.token.OperatorToken;
import org.tara.compiler.tokenizer.token.Token;
import org.tara.types.Operator;


public class TokenizerOperatorTests {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer();
	}
	
	@Test
	public void testAssign() {
		
		List<TokenMatch> matches = tokenizer.tokenize("=");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.ASSIGN, match.getToken());
		assertEquals(1, match.getRange().size());
	}
	
	@Test
	public void testEquals() {
		
		List<TokenMatch> matches = tokenizer.tokenize("==");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.EQUALS, match.getToken());
		assertEquals(2, match.getRange().size());
	}
	
	@Test
	public void testAdd() {
		
		List<TokenMatch> matches = tokenizer.tokenize("+");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.ADD, match.getToken());
		assertEquals(1, match.getRange().size());
	}

	@Test
	public void testSubtract() {
		
		List<TokenMatch> matches = tokenizer.tokenize("-");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.SUBTRACT, match.getToken());
		assertEquals(1, match.getRange().size());
	}
	
	@Test
	public void testMultiply() {
		
		List<TokenMatch> matches = tokenizer.tokenize("*");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.MULTIPLY, match.getToken());
		assertEquals(1, match.getRange().size());
	}
	
	@Test
	public void testDivide() {
		
		List<TokenMatch> matches = tokenizer.tokenize("/");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.DIVIDE, match.getToken());
		assertEquals(1, match.getRange().size());
	}
	
	@Test
	public void testIncrement() {
		
		List<TokenMatch> matches = tokenizer.tokenize("++");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.INCREMENT, match.getToken());
		assertEquals(2, match.getRange().size());
	}
	
	
	@Test
	public void testDecrement() {
		
		List<TokenMatch> matches = tokenizer.tokenize("--");
		assertEquals(1, matches.size());
		
		TokenMatch match = matches.get(0);
		
		assertCorrectOperator(Operator.DECREMENT, match.getToken());
		assertEquals(2, match.getRange().size());
	}
	
	private static void assertCorrectOperator(Operator expected, Token<?> token) {
		
		assertThat(token, instanceOf(OperatorToken.class));
		assertTrue(token.isOperator());
		assertEquals(expected, token.getContent());
	}

}
