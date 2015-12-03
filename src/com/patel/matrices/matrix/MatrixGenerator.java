package com.patel.matrices.matrix;

import java.util.ArrayList;

import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.Operations;

public class MatrixGenerator {
	
	public Matrix[] generateSolutions(int[][] startingSums, int[][] linePositions) {
		Matrix[] solutions = null;
		Matrix matrix = new Matrix(1,1);
		MatrixGenerator mg = new MatrixGenerator();
		
		Operations.sort(startingSums);
		int[][] correctedSums = matrix.fixTrivialCases(startingSums);
		
		Matrix[] allMatrices = mg.buildFromLinePositions(linePositions, correctedSums);
		Matrix[] checkedMatrices = mg.checkMatrices(allMatrices, correctedSums);
		
		solutions = checkedMatrices;
		
		return solutions;
	}
	
	public Matrix[] checkMatrices(Matrix[] allMatrices, int[][] correctedSums) {
		ArrayList<Matrix> tempMatrices = new ArrayList<Matrix>();
		boolean colsMatch = true;
		
		for (int i = 0; i < allMatrices.length; i++) {
			for (int col = 0; col < allMatrices[i].getNumColumns(); col++) {
				int colSum = 0;
				for (int x = 0; x < allMatrices[i].getNumRows(); x++) {
					colSum += allMatrices[i].getCellValue(x, col);
				}
				if (colSum != correctedSums[0][col]) {
					colsMatch = false;
				}
			}
			if (colsMatch) {
				tempMatrices.add(allMatrices[i]);
			}
			colsMatch = true;
		}
		
		Matrix[] correctedMatrices = new Matrix[tempMatrices.size()];
		
		for (int i = 0; i < correctedMatrices.length; i++) {
			correctedMatrices[i] = tempMatrices.get(i);
		}
		
		return correctedMatrices;
	}

	public Matrix[] buildFromLinePositions(int [][] linePositions, int[][] sums) {
		Matrix[] testMatrices = new Matrix[linePositions.length];
		String path = "res/Binary Sequences/" + sums[1].length + "/" + sums[1].length + "C";
		int[][][] allChooseFiles = new int[sums[1].length][][];
		
		for(int i = 0; i < allChooseFiles.length; i++) {
			int p = i+1;
			allChooseFiles[i] = FileHandler.getPermutations(path + p + ".txt");
		}
		
		for(int i = 0; i < testMatrices.length; i++) {
			testMatrices[i] = new Matrix(sums[0].length, sums[1].length);
		}
		
		for (int i = 0; i < linePositions.length; i++) {
			for (int j = 0; j < linePositions[i].length; j++) {
				testMatrices[i].setRow(j, allChooseFiles[sums[0][j]-1][linePositions[i][j]]);
			}
		}
		return testMatrices;
	}
}
