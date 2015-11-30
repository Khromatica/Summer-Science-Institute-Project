package com.patel.matrices.matrix;

public class AverageCalculator {
	@SuppressWarnings("static-access")
	public Matrix average(Matrix[] matrices) {
		Matrix avg = new Matrix(matrices[0].getNumRows(), matrices[0].getNumColumns());
		for (int x = 0; x < avg.getNumRows(); x++) {
			for (int y = 0; y < avg.getNumColumns(); y++) {
				for (int i = 0; i < matrices.length; i++) {
					avg.setCellValue(x, y, avg.getCellValue(x, y) + matrices[i].getCellValue(x, y));
				}
			}
		}
		return avg;
	}
}
