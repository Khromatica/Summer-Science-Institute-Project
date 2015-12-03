package com.patel.matrices.matrix;

public class DoubleMatrixCell {
	private boolean immutable;
	private double value;
	
	public DoubleMatrixCell(int row, int column) {
		immutable = false;
		value = 0;
		
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
}
