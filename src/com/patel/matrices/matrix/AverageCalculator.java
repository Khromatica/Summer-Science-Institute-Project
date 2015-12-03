package com.patel.matrices.matrix;

public class AverageCalculator {
	public static void main(String [] args) {
		AverageCalculator a = new AverageCalculator();
		Matrix matrix1 = new Matrix(3,3);
		matrix1.setRowValue(0, 0, true);
		matrix1.setRowValue(1, 0, true);
		matrix1.setRowValue(2, 1, true);
		Matrix matrix2 = new Matrix(3,3);
		matrix2.setRowValue(0, 0, true);
		matrix2.setRowValue(1, 1, true);
		matrix2.setRowValue(2, 1, true);
		Matrix[] matrixList = new Matrix[2];
		matrixList[0] = matrix1;
		matrixList[1] = matrix2;
		DoubleMatrix average = a.average(matrixList);
		average.print();
	}
	
	public DoubleMatrix average(Matrix[] matrices) {
		DoubleMatrix avg = new DoubleMatrix(matrices[0].getNumRows(), matrices[0].getNumColumns());
		for (int x = 0; x < avg.getNumRows(); x++) {
			for (int y = 0; y < avg.getNumColumns(); y++) {
				for (int i = 0; i < matrices.length; i++) {
					avg.setCellValue(x, y, avg.getCellValue(x, y) + matrices[i].getCellValue(x, y));
				}
			}
		}
		
		for (int x = 0; x < avg.getNumRows(); x++) {
			for (int y = 0; y < avg.getNumColumns(); y++) {
				avg.setCellValue(x,y, (avg.getCellValue(x,y)/matrices.length));
			}
		}
		
		Matrix tempMatrix = new Matrix(avg.getNumRows(), avg.getNumColumns());
		
		for (int x = 0; x < avg.getNumRows(); x++) {
			for (int y = 0; y < avg.getNumColumns(); y++) {
				int value = ((int)((avg.getCellValue(x, y))*10000));
				tempMatrix.setCellValue(x, y, value);
			}
		}
		
		for (int x = 0; x < avg.getNumRows(); x++) {
			for (int y = 0; y < avg.getNumColumns(); y++) {
				double value = ((double) tempMatrix.getCellValue(x, y))/100;
				avg.setCellValue(x, y, value);
			}
		}
		
		
		return avg;
	}
}
