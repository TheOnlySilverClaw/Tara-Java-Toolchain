package org.tara.util.text;

public class TextCursor {

	private final CharSequence text;
	
	private TextPosition marker;
	
	private int index;
	private int line;
	private int column;
	
	
	public TextCursor(CharSequence text) {
		
		this.marker = null;

		this.text = text;
		this.index = 0;
		this.line = 0;
		this.column = 0;
	}

	public char getCurrentChar() {
		return text.charAt(index);
	}
	
	public char getNextChar() {
		return text.charAt(index + 1);
	}
	
	public TextPosition getPosition() {
		return new TextPosition(index, line, column);
	}
	
	protected void newLine() {
		line++;
		column = 0;
	}
	
	public boolean hasCurrent() {
		return text.length() > index;
	}
	
	public boolean hasNext() {
		return text.length() > index + 1;
	}
	
	public void mark() throws IllegalStateException {
		
		if(marker != null) {
			throw new IllegalStateException();
		}
		
		marker = getPosition();
	}
	
	public void unmark() throws IllegalStateException {
		
		if(marker == null) {
			throw new IllegalStateException();
		}
		
		marker = null;
	}
	
	public TextRange getMarkedRange() throws IllegalStateException {
		
		if(marker == null) {
			throw new IllegalStateException();
		}
		
		return new TextRange(marker, getPosition());
	}
	
	public String getMarkedText() {
		return getText(getMarkedRange()).toString();
	}
	
	public CharSequence getText(TextRange range) {
		
		return text.subSequence(
				range.getStart().getIndex(),
				range.getEnd().getIndex() + 1
				);
	}
	
	public void advance() {
		
		if(getCurrentChar() == '\n') {
			newLine();
		} else {
			column++;
		}
		
		index++;
	}
	
	public void skipWhiteSpace() {
		
		while(hasCurrent()) {
			char current = getCurrentChar();
			if(Character.isWhitespace(current)) {
				advance();
			} else {
				return;
			}
		}
	}
	
	public void skipLine() {
		
		int oldLine = line;
		while(hasNext() && oldLine == line) {
			advance();
		}
	}
}
