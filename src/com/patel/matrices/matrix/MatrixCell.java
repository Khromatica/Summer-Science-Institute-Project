package com.patel.matrices.matrix;

public class MatrixCell {

	private boolean immutable;
	private int value;

	public MatrixCell() {
		immutable = false;
		value = 0;
	}

	public void setValue(int value) {
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

	public int getValue() {
		return value;
	}

}
