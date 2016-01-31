package com.patel.matrices.matrix;

import java.util.ArrayList;

import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.Operations;
import com.patel.matrices.main.tools.RecursiveFileLinePositionGenerator;

public class MatrixGenerator {
	public static String OS = System.getProperty("os.name");
	
	public DoubleMatrix returnAverage(Matrix[] solutions) {
		DoubleMatrix avgMatrix = null;
		AverageCalculator avgCalc = new AverageCalculator();
		avgMatrix = avgCalc.average(solutions);
		
		return avgMatrix;
	}
	
	public Matrix[] returnSolutions(int[][] startingSums) {
		Matrix[] solutions = null;
		Matrix matrix = new Matrix(1,1);
		MatrixGenerator mg = new MatrixGenerator();
		RecursiveFileLinePositionGenerator rflpg = new RecursiveFileLinePositionGenerator();

		int[][] correctedSums = matrix.fixTrivialCases(startingSums);
		int[][] linePositions = rflpg.returnCombinations(correctedSums, correctedSums[0].length - 1);
		solutions = mg.generateSolutions(correctedSums, linePositions);
		
		return solutions;
	}
	
	public Matrix[] generateSolutions(int[][] correctedSums, int[][] linePositions) {
		Matrix[] solutions = null;
		MatrixGenerator mg = new MatrixGenerator();
		
		Matrix[] allMatrices = mg.buildFromLinePositions(linePositions, correctedSums);
		solutions = mg.checkMatrices(allMatrices, correctedSums);
		
		return solutions;
	}
	
	public Matrix[] checkMatrices(Matrix[] allMatrices, int[][] correctedSums) {
		ArrayList<Matrix> tempMatrices = new ArrayList<Matrix>();
		boolean colsMatch = true;
		
		for (int i = 0; i < allMatrices.length; i++) {
			for (int col = 0; col < allMatrices[i].getNumColumns(); col++) {
				int colSum = 0;
				for (int row = 0; row < allMatrices[i].getNumRows(); row++) {
					colSum += allMatrices[i].getCellValue(row, col);
				}
				if (colSum != correctedSums[1][col]) {
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
		String path = "";
		
		String username = System.getProperty("user.name");
		
		
		if (Operations.isWindows(OS)) {
			System.out.println("Hello");
			path = "C:/Users/" + username + "/Desktop/Combination Resources/Binary Sequences/" + sums[1].length + "/" + sums[1].length + "C";			
		} else if (Operations.isMac(OS)) {
			path = "/Users/" + username + "/Desktop/Combination Resources/Binary Sequences/" + sums[1].length + "/" + sums[1].length + "C"; 
		}

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
