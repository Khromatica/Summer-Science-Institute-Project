package com.patel.matrices.matrix;

public class MatrixCell {

	private boolean immutable;
	private double value;

	private Index originalIndex;
	
	public MatrixCell(int row, int column) {
		immutable = false;
		value = 0;
		
		setOriginalIndex(new Index(row, column));
	}

	public void setValue(double value) {
		if (!immutable) {
			this.value = value;
		}
	}

	public void setImmutable(boolean immutable) {
		this.immutable = immutable;
	}

	public boolean isImmutable() {
		return immutable;
	}

	public double getValue() {
		return value;
	}

	public Index getOriginalIndex() {
		return originalIndex;
	}

	public void setOriginalIndex(Index originalIndex) {
		this.originalIndex = originalIndex;
	}

}
