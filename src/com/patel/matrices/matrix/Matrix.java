package com.patel.matrices.matrix;

import java.util.ArrayList;

import com.patel.matrices.main.tools.MergeSorter;

@SuppressWarnings("unused")
public class Matrix {
	private MergeSorter sorter;

	private int setRowRuns;

	private int[] rowSums, columnSums;
	private int numRows, numColumns;

	private ArrayList<Integer> completedRows, completedColumns;

	private MatrixCell[][] matrixCellArray;

	public Matrix(int[] rowSums, int[] columnSums, boolean sort) {
		setRowRuns = 0;

		sorter = new MergeSorter();

		this.rowSums = rowSums;
		this.columnSums = columnSums;
		numRows = rowSums.length;
		numColumns = columnSums.length;
		matrixCellArray = new MatrixCell[numRows][numColumns];

		if (sort) {
			sorter.sort(rowSums);
			sorter.sort(columnSums);
		}

		for (int i = 0; i < this.getNumRows(); i++) {
			for (int j = 0; j < this.getNumColumns(); j++) {
				this.matrixCellArray[i][j] = new MatrixCell();
			}
		}
	}

	public void print() {
		for (int i = 0; i < this.matrixCellArray.length; i++) {
			for (int j = 0; j < this.matrixCellArray[i].length; j++) {
				System.out.print(this.matrixCellArray[i][j].getValue() + " ");
			}
			System.out.println();
		}
	}

	public void setMatrixRow(int rowPos, int[] row) {
		for (int i = 0; i < row.length; i++) {
			this.matrixCellArray[rowPos][i].setValue(row[i]);
		}
		setRowRuns += 1;
	}

	public Matrix createSubMatrix() {
		int[] newRowSums = getNewRowSums();
		int[] newColumnSums = getNewColumnSums();

		return new Matrix(newRowSums, newColumnSums, false);
	}

	public int[] getNewRowSums() {
		int[] newRowSums = new int[this.getNumRows() - setRowRuns];
		for (int i = 0; i < newRowSums.length; i++) {
			newRowSums[i] = this.getRowSums()[i];
		}

		return newRowSums;
	}

	public int[] getNewColumnSums() {
		int[] newColumnSums = new int[this.getNumColumns()];
		int[] currentColSums = currentColSums();

		for (int i = 0; i < newColumnSums.length; i++) {
			newColumnSums[i] = this.getColumnSums()[i] - currentColSums[i];
		}

		return newColumnSums;
	}

	public int[] currentColSums() {
		int[] columnSums = new int[this.getNumColumns()];

		for (int i = 0; i < this.getNumColumns(); i++) {
			for (int j = 0; j < this.getNumRows(); j++) {
				columnSums[i] += this.get(j, i);
			}
		}

		return columnSums;

	}

	public void initialCheck(int[][] sums) {
		for (int i = 0; i < getRowSums().length; i++) {
			if (this.getNumColumns() == this.getRowSums()[i]) {
				for (int j = 0; j < getNumColumns(); j++) {
					this.setCellValue(i, j, 1);
					this.setImmutable(i, j, true);
				}
			}
		}

		for (int j = 0; j < getColumnSums().length; j++) {
			if (this.getNumRows() == this.getColumnSums()[j]) {
				for (int i = 0; i < getNumRows(); i++) {
					this.setCellValue(i, j, 1);
					this.setImmutable(i, j, true);
				}
			}
		}

		for (int i = 0; i < getRowSums().length; i++) {
			if (this.getRowSums()[i] == 0) {
				for (int j = 0; j < getNumColumns(); j++) {
					this.setCellValue(i, j, 0);
					this.setImmutable(i, j, true);
				}
			}
		}

		for (int j = 0; j < getColumnSums().length; j++) {
			if (this.getColumnSums()[j] == 0) {
				for (int i = 0; i < getNumRows(); i++) {
					this.setCellValue(i, j, 0);
					this.setImmutable(i, j, true);
				}
			}
		}

	}

	public void setCellValue(int a, int b, int value) {
		this.matrixCellArray[a][b].setValue(value);
	}

	public void setImmutable(int a, int b, boolean immutable) {
		this.matrixCellArray[a][b].setImmutable(immutable);
	}

	/*
	 * /////////////////////////////////////////////
	 * 
	 * GETTER METHODS
	 * 
	 * /////////////////////////////////////////////
	 */

	public int[] getRowSums() {
		return this.rowSums;
	}

	public int[] getColumnSums() {
		return this.columnSums;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public MatrixCell[][] getMatrixCellArray() {
		return matrixCellArray;
	}

	public int get(int i, int j) {
		int element = this.getMatrixCellArray()[i][j].getValue();
		return element;
	}

	public int getNumEmptyRows() {
		return (this.getMatrixCellArray().length - setRowRuns);
	}

	public void solutionLoop() {

	}

}
