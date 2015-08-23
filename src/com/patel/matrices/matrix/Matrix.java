package com.patel.matrices.matrix;

import org.apache.commons.lang3.ArrayUtils;

import com.patel.matrices.main.tools.MergeSorter;
import com.patel.matrices.main.tools.Operations;

@SuppressWarnings("unused")
public class Matrix {
	private MergeSorter sorter;

	private int setRowRuns;

	private int[] rowSums, columnSums;
	private int numRows, numColumns;

	private MatrixCell[][] matrixCellArray;

	private boolean[] givenRows;
	private boolean[] givenColumns;

	public Matrix(int[][]sums) {
		setRowRuns = 0;

		givenRows = new boolean[sums[0].length];
		givenColumns = new boolean[sums[1].length];

		sorter = new MergeSorter();

		this.rowSums = sums[0];
		this.columnSums = sums[1];
		numRows = sums[0].length;
		numColumns = sums[1].length;
		matrixCellArray = new MatrixCell[numRows][numColumns];

		sorter.sort(this.rowSums);
		sorter.sort(this.columnSums);

		for (int i = 0; i < this.getNumRows(); i++) {
			for (int j = 0; j < this.getNumColumns(); j++) {
				this.matrixCellArray[i][j] = new MatrixCell(i, j);
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

	public static int[][] removeTrivialCases(int[][] startingSums) {
		int[][] correctedSums = startingSums;
		
		Operations.print2DArray(correctedSums);
		
		int counter = 0;
		
		int numRows = correctedSums[0].length;
		int numCols = correctedSums[1].length;
		
		boolean valueEqualsDimension = true;
		
		for (int i = 0; i < correctedSums.length; i++) {
			for (int j = 0; j < correctedSums[i].length; j++) {
				if (correctedSums[i][j] == 0) {
					correctedSums[i] = ArrayUtils.removeElement(correctedSums[i], j);
					j--;
					if (i == 0) {
						numRows = correctedSums[0].length;
					} else if (i == 1) {
						numCols = correctedSums[1].length;
					}
				}
			}
		}
		
		while(valueEqualsDimension) {
			for (int i = 0; i < correctedSums[0].length; i++) {
				if (correctedSums[0][i] == numCols) {
					correctedSums[0] = ArrayUtils.removeElement(correctedSums[0], i);
					numRows = correctedSums[0].length;
					i--;
					for (int j = 0; j < correctedSums[0].length; j++) {
						correctedSums[0][j]--;
					}
					
					valueEqualsDimension = false;
					
					for (int h = 0; h < correctedSums[0].length; h++) {
						if (correctedSums[0][h] == numCols) {
							valueEqualsDimension = true;
						}
					}
				}
			}
			
			for (int i = 0; i < correctedSums[1].length; i++) {
				if (correctedSums[1][i] == numRows) {
					correctedSums[1] = ArrayUtils.removeElement(correctedSums[1], i);
					numCols = correctedSums[1].length;
					i--;
					for (int j = 0; j < correctedSums[1].length; j++) {
						correctedSums[1][j]--;
				    }
				}    

				valueEqualsDimension = false;
				
				for (int h = 0; h < correctedSums[1].length; h++) {
					if (correctedSums[1][h] == numRows) {
						valueEqualsDimension = true;
					}
				}
			}
		}
		return correctedSums;
	}
	
	public void initialCheck() {
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

	public String toString(){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < this.getNumRows(); i++){
			s.append("[ ");
			for(int j = 0; j < this.getNumColumns(); j++){
				s.append(" " +this.matrixCellArray[i][j].getValue() +" ");
			}
			s.append(" ]\n");
		}

		return s.toString();
	}

}
