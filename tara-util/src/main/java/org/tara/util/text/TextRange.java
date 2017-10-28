package org.tara.util.text;

public class TextRange {

	private final TextPosition start;
	private final TextPosition end;
	
	public TextRange(TextPosition start, TextPosition end) {
		this.start = start;
		this.end = end;
	}

	public final TextPosition getStart() {
		return start;
	}

	public final TextPosition getEnd() {
		return end;
	}
	
	public int size() {
		return 1 + end.getIndex() - start.getIndex();
	}
}
