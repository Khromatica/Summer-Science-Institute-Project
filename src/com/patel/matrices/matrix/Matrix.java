package com.patel.matrices.matrix;

import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import com.patel.matrices.main.tools.MergeSorter;
import com.patel.matrices.main.tools.Operations;

@SuppressWarnings("all")
public class Matrix {
	private MergeSorter sorter;

	private int setRowRuns;

	private int[] rowSums, columnSums;
	private static int numRows, numColumns;

	private static MatrixCell[][] matrixCellArray;

	public Matrix(int[][]sums) {
		setRowRuns = 0;

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
	
	public Matrix(int numRows, int numCols) {
		matrixCellArray = new MatrixCell[numRows][numColumns];
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

	public void setRow(int rowPos, int[] row) {
		for (int i = 0; i < row.length; i++) {
			this.matrixCellArray[rowPos][i].setValue(row[i]);
		}

		setRowRuns += 1;
	}

	public static void setCellValue(int a, int b, int value) {
		matrixCellArray[a][b].setValue(value);
	}
	
	
	public static void setImmutable(int a, int b, boolean immutable) {
		matrixCellArray[a][b].setImmutable(immutable);
	}
	
	public static void setRowValue(int row, int value, boolean immutable) {
		for (int i = 0; i < numColumns; i++) {
			setCellValue(row, i, value);
			setImmutable(row, i, immutable);
		}
	}
	
	public static void setColumnValue(int column, int value, boolean immutable) {
		for (int i = 0; i < numRows; i++) {
			setCellValue(column, i, value);
			setImmutable(column, i, immutable);
		}
	}

	
	public static int[][] fixTrivialCases(int [][] startingSums) {
		ArrayList[][] mylist = new ArrayList[2][1];
		mylist[0][0] = new ArrayList();
		mylist[1][0] = new ArrayList();
		
		System.out.println(startingSums.length);
		
		for (int i = 0; i < startingSums.length; i++ ) {
			for (int j = 0; j < startingSums[i].length; j++) {
				if (i == 0){
					if (startingSums[i][j] > startingSums[1].length) {
						System.err.println("Invalid sums");
						System.exit(0);
					}
				}
				
				if (i == 1){
					if (startingSums[i][j] > startingSums[0].length) {
						System.err.println("Invalid sums");
						System.exit(0);
					}
				}
			}
		}
		
		
		for (int i = 0; i < startingSums.length; i++) {
			for (int j = 0; j < startingSums[i].length; j++) {
				mylist[i][0].add(startingSums[i][j]);
			}
		}
		
		for (int i = 0; i < mylist.length; i++) {
			for (int j = 0; j < mylist[i][0].size(); j++) {
				if ( (int) mylist[i][0].get(j) == 0) {
					if (i == 0) {
						setRowValue(j, 0, true);
					}
					
					if (i == 1) {
						setColumnValue(j, 0, true);
					}
				}
			}
		}
		
		System.out.println("List1: "+ mylist[0][0]);
		System.out.println("List2: "+ mylist[1][0]);
		
		//Remove the 0's from both the arrays
		mylist[0][0].removeAll(Collections.singleton(0));
		mylist[1][0].removeAll(Collections.singleton(0));
		
		System.out.println("List1: "+ mylist[0][0]);
		System.out.println("List2: "+ mylist[1][0]);
		
		int origRow1Size = mylist[0][0].size();
		int origRow2Size = mylist[1][0].size();	
		
		boolean valueEqualsDimension = true;
		
		while (valueEqualsDimension)
		{
			Iterator iterator1 = mylist[0][0].iterator();
			Iterator iterator2 = mylist[1][0].iterator();
			
			System.out.println("origRow1Size: "+ origRow1Size + "\torigRow2Size: "+ origRow2Size);
			
			while (iterator1.hasNext()){				
				int value1 = (int)iterator1.next();

				//Check if the value of the element is greater-than or equal to
				// the corresponding array
				if(value1 == mylist[1][0].size()){	
					// Remove the element that is > = array size
					iterator1.remove();
					// Reduce the value of all the elements by 1 in the neighboring array 
					int index2 = 0;
					while(iterator2.hasNext()){
						int value2 = (int)iterator2.next();
						mylist[1][0].set(index2, (value2 - 1));
						index2++ ;
					}
				}
			} // inner-while 1
			
						
			while (iterator2.hasNext()){				
				int value2 = (int)iterator2.next();
				//Check if the value of the element is greater-than or equal to
				// the corresponding array
				if(value2 == mylist[0][0].size()){	
					// Remove the element that is > = array size
					iterator2.remove();
					// Reduce the value of all the elements by 1 in the neighboring array
					int index1 = 0;
					while(iterator1.hasNext()){
						int value1 = (int)iterator1.next();
						mylist[0][0].set(index1, (value1 - 1));
						index1++ ;
					}
				}
			} // inner-while 2
			
			//Remove the 0's from both the arrays
			mylist[0][0].removeAll(Collections.singleton(0));
			mylist[1][0].removeAll(Collections.singleton(0));
			
			// Check if the length of the arrays changed after the iteration
			// if the size changed, repeat the while loop
			// else set valueEqualsDimension to false to exit the while loop
			if (origRow1Size != mylist[0][0].size() || origRow2Size != mylist[1][0].size())
			{
				origRow1Size = mylist[0][0].size();
				origRow2Size = mylist[1][0].size();
			}
			else
			{
				valueEqualsDimension = false;
			}
		}		
		
		int[][] correctedSums = new int[mylist.length][];
		correctedSums[0] = new int[mylist[0][0].size()];
		correctedSums[1] = new int[mylist[1][0].size()];
		
		
		for (int i = 0; i < mylist.length; i++) {
			for (int j = 0; j < mylist[i][0].size(); j++) {
				correctedSums[i][j] = (int) mylist[i][0].get(j);
			}
		}
		
		System.out.println(mylist[0][0]);
		System.out.println(mylist[1][0]);
		
		
		return correctedSums;
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
	
	public int getCellValue(int a, int b) {
		return this.matrixCellArray[a][b].getValue();
	}

}
