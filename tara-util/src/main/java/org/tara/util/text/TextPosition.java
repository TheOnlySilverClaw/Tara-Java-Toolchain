package org.tara.util.text;

public class TextPosition {

	private final int index;
	private final int line;
	private final int column;
	
	public TextPosition(int index, int line, int column) {
		this.index = index;
		this.line = line;
		this.column = column;
	}

	public final int getIndex() {
		return index;
	}

	public final int getLine() {
		return line;
	}

	public final int getColumn() {
		return column;
	}
}
