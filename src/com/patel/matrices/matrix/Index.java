package com.patel.matrices.matrix;

public class Index {
	private int row, column;
	
	public Index(int row, int column) {
			this.setRow(row);
		
			this.setColumn(column);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	
}
