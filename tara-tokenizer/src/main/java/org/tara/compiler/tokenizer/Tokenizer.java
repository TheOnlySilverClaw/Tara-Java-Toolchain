package org.tara.compiler.tokenizer;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tara.compiler.tokenizer.token.CommentToken;
import org.tara.compiler.tokenizer.token.FloatingPointLiteralToken;
import org.tara.compiler.tokenizer.token.IdentifierToken;
import org.tara.compiler.tokenizer.token.IntegerLiteralToken;
import org.tara.compiler.tokenizer.token.KeywordToken;
import org.tara.compiler.tokenizer.token.NumberLiteralToken;
import org.tara.compiler.tokenizer.token.OperatorToken;
import org.tara.compiler.tokenizer.token.StringLiteralToken;
import org.tara.compiler.tokenizer.token.Token;
import org.tara.keywords.Keyword;
import org.tara.types.Operator;
import org.tara.util.text.TextCursor;
import org.tara.util.text.TextRange;

public class Tokenizer {

	private final Logger logger = LoggerFactory.getLogger(Tokenizer.class);
	
	private final Map<String, Keyword> keywords;
	
	public Tokenizer() {
		
		this.keywords = new HashMap(Keyword.values().length);
		for(Keyword keyword : Keyword.values()) {
			keywords.put(keyword.name().toLowerCase(Locale.ENGLISH), keyword);
		}
	}
	
	public List<TokenMatch> tokenize(CharSequence source) {

		logger.trace("Tokenizing:\n{}", source);
		
		TextCursor cursor = new TextCursor(source);
		
		int estimate = Math.max(16, source.length() / 4);
		List<TokenMatch> tokens = new ArrayList<>(estimate);
		
		cursor.skipWhiteSpace();
		
		while(cursor.hasCurrent()) {
			
			cursor.mark();
			Token<?> token = matchToken(cursor);
			TextRange range = cursor.getMarkedRange();
			if(token == null) {
				String text = cursor.getText(range).toString();
				throw new IllegalArgumentException("Unable to match '" + text + "'");
			}
			tokens.add(new TokenMatch(range, token));
			cursor.unmark();
			cursor.advance();
			cursor.skipWhiteSpace();
		}
		
		return tokens;
	}

	private Token<?> matchToken(TextCursor cursor) {

		char start = cursor.getCurrentChar();
		
		if(start == '=') {
			if(cursor.hasNext()) {
				char next = cursor.getNextChar();
				if(next == '=') {
					cursor.advance();
					return new OperatorToken(Operator.EQUALS);
				}
			}
			return new OperatorToken(Operator.ASSIGN);
		}
		
		if(start == '+') {
			if(cursor.hasNext()) {
				char next = cursor.getNextChar();
				if(next == '+') {
					cursor.advance();
					return new OperatorToken(Operator.INCREMENT);
				}
			}
			return new OperatorToken(Operator.ADD);
		}
		
		if(start == '-') {
			if(cursor.hasNext()) {
				char next = cursor.getNextChar();
				if(next == '-') {
					cursor.advance();
					return new OperatorToken(Operator.DECREMENT);
				}
				
				if(Character.isDigit(next)) {
					return matchNumber(false, cursor);
				}
			}
			return new OperatorToken(Operator.SUBTRACT);
		}
		
		if(start == '*') {
			return new OperatorToken(Operator.MULTIPLY);
		}
		
		if(start == '/') {
			
			if(cursor.hasNext()) {
				char next = cursor.getNextChar();
				
				if(next == '/') {
					cursor.skipLine();
					String comment = cursor.getMarkedText();
					return new CommentToken(comment);
				}
			}
			return new OperatorToken(Operator.DIVIDE);
		}
		
		if(Character.isLetter(start)) {
			
			IdentifierToken identifierToken = matchIdentifier(cursor);
			
			String identifier = identifierToken.getContent();
			
			if(keywords.containsKey(identifier)) {
				Keyword keyword = keywords.get(identifier);
				return new KeywordToken(keyword);
			} else {
				return identifierToken;
			}
		}
		
		if(Character.isDigit(start)) {
			return matchNumber(true, cursor);
		}
		
		if(start == '"' ) {
			return matchString(cursor);
		}
		
		return null;
	}
	
	protected IdentifierToken matchIdentifier(TextCursor cursor) {
		
		while(cursor.hasNext()) {
			char next = cursor.getNextChar();
			if(next == '_'
					|| Character.isLetter(next)
					|| Character.isDigit(next)
					) {
				cursor.advance();
			} else {
				break;
			}
		}
		
		String identifier = cursor.getMarkedText();
		return new IdentifierToken(identifier);
	}
	
	protected NumberLiteralToken matchNumber(boolean positive, TextCursor cursor) {
		
		boolean floatingPoint = false;
		while(cursor.hasNext()) {
			char next = cursor.getNextChar();
			if(Character.isDigit(next)) {
				cursor.advance();
			} else if(next == '.') {
				if(floatingPoint == true) {
					throw new IllegalArgumentException("Two floating points!");
				} else {
					floatingPoint = true;
					cursor.advance();
				}
			} else {
				break;
			}
		}
		String literal = cursor.getMarkedText();
		if(floatingPoint) {
			return new FloatingPointLiteralToken(literal);
		} else {
			return new IntegerLiteralToken(literal);
		}
	}
	
	protected StringLiteralToken matchString(TextCursor cursor) {
	
		if(!cursor.hasNext()) {
			throw new IllegalArgumentException("Unclosed string literal");
		}
		
		while(cursor.hasNext()) {
			
			cursor.advance();
			
			char current = cursor.getCurrentChar();
			
			if(current == '\\') {
				cursor.advance();
				continue;
			}
			
			if(current == '"') {
				String literal = cursor.getMarkedText();
				return new StringLiteralToken(literal);
			}
		}
		
		throw new IllegalArgumentException(
				"Unclosed string literal " + cursor.getMarkedText());
	}
}
