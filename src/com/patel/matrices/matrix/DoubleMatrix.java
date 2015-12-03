package com.patel.matrices.matrix;

public class DoubleMatrix {

	private int numRows, numColumns;

	private DoubleMatrixCell[][] matrixCellArray;
	
	public DoubleMatrix(int numRows, int numCols) {
		this.numRows = numRows;
		this.numColumns = numCols;
		matrixCellArray = new DoubleMatrixCell[numRows][numColumns];
		for (int i = 0; i < this.getNumRows(); i++) {
			for (int j = 0; j < this.getNumColumns(); j++) {
				this.matrixCellArray[i][j] = new DoubleMatrixCell(i, j);
			}
		}
	}
	
	public void setCellValue(int a, int b, double value) {
		matrixCellArray[a][b].setValue(value);
	}
	
	public void print() {
		for (int i = 0; i < this.matrixCellArray.length; i++) {
			for (int j = 0; j < this.matrixCellArray[i].length; j++) {
				System.out.print(this.matrixCellArray[i][j].getValue() + " ");
			}
			System.out.println();
		}
	}
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public double getCellValue(int a, int b) {
		return this.matrixCellArray[a][b].getValue();
	}
}
