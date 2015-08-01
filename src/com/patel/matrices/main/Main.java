package com.patel.matrices.main;

import java.util.ArrayList;

import com.patel.matrices.main.tools.BinarySequencer;
import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.FileReporter;
import com.patel.matrices.main.tools.Operations;
import com.patel.matrices.matrix.Matrix;

public class Main {
	static int[][] sums = FileHandler.getSums(FileHandler.loadFile("res/vec.txt"));

	public static void main(String[] args) {
		Operations.sort(sums);
		ArrayList<String> information = new ArrayList<String>();
		ArrayList<Matrix> matrices = new ArrayList<Matrix>();
		// ArrayList<ArrayList<Integer>> matrixSums =
		// Operations.duplicate2DArray(sums);
		// Operations.removeGivens(matrixSums);
		// int[][] updatedSums = Operations.intArrayListToArray(matrixSums);

		// TODO: verify this memory usage
		long startTime = System.currentTimeMillis();
		long totalMemory = Runtime.getRuntime().totalMemory() / 1000000;
		long initialMemory = Runtime.getRuntime().freeMemory() / 1000000;

		/*
		
		
		
		
		
		
		
		
		
		
		
		
		
		*/

		int[][] columnCombos = BinarySequencer.getSequences(sums[1].length, (sums[0][sums[0].length - 1]));
		int numOfCombos = columnCombos.length;

		for (int i = 0; i < columnCombos.length; i++) {
			matrices.add(new Matrix(sums[0], sums[1], true));
			matrices.get(i).initialCheck(sums);
			// matrices.get(i).setMatrixRow(sums[0].length - 1,
			// columnCombos[i]);
		}

		/*
		
		
		
		
		
		
		
		
		
		
		
		
		
		*/

		long afterMemory = Runtime.getRuntime().freeMemory() / 1000000;

		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;

		information.add("Total Memory: " + totalMemory + " MB");
		information.add("Free Memory Before: " + initialMemory + " MB");
		information.add("Row Sums: " + Operations.intArrayToString(sums[0]));
		information.add("Column Sums: " + Operations.intArrayToString(sums[1]));
		information.add("Number of Combinations: " + numOfCombos);
		information.add("Free Memory After: " + afterMemory + " MB");
		information.add("Free Memory Used: " + (initialMemory - afterMemory) + " MB");
		information.add("Time Taken: " + (timeTaken) + " milliseconds");

		FileReporter.writeReport(information, columnCombos, matrices);

		endTime = System.currentTimeMillis();
		timeTaken = endTime - startTime;

		System.out.println(timeTaken);
	}

	public static void println() {
		System.out.println();
	}
}
