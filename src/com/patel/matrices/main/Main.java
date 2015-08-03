package com.patel.matrices.main;

import java.util.ArrayList;

import com.patel.matrices.main.tools.BinarySequencer;
import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.FileReporter;
import com.patel.matrices.main.tools.Operations;
import com.patel.matrices.matrix.Matrix;

public class Main {
	static int[][] startingSums = FileHandler.getSums(FileHandler.loadFile("res/vec.txt"));
    static int numOfColumns = startingSums[1].length;
    static int lastValueInRowSums = startingSums[0][startingSums[0].length - 1];

	public static void main(String[] args) {

		ArrayList<String> information = new ArrayList<String>();    //print infomation
		ArrayList<Matrix> solutionMatrices = new ArrayList<Matrix>();    //where the solutions go
		
		Operations.sort(startingSums);
		System.out.println("Here are our rowSums and colSums"+ startingSums);

        long startTime = System.currentTimeMillis();
        long totalMemory = Runtime.getRuntime().totalMemory() / 1000000;
        long initialMemory = Runtime.getRuntime().freeMemory() / 1000000;
		
		int[][] columnCombos = BinarySequencer.getSequences(numOfColumns, lastValueInRowSums);
        System.out.println("Time taken to fine columnCombos: " + (System.currentTimeMillis() - startTime));
		int numOfCombos = columnCombos.length;

		for (int i = 0; i < columnCombos.length; i++) {
			Matrix m = new Matrix(startingSums[0], startingSums[1]);
            m.initialCheck();
            m.setMatrixRow(startingSums[0].length - 1, columnCombos[i]);
            solutionMatrices.add(m);
		}

		solutionMatrices.get(0).print();
		System.out.println(solutionMatrices.get(0).getNumRows());
		System.out.println(solutionMatrices.get(0).getNumColumns());

        printInformation(information, startTime, totalMemory, initialMemory, numOfCombos);
		FileReporter.writeReport(information, columnCombos, solutionMatrices);
	}

    private static void printInformation(ArrayList<String> information, long startTime, long totalMemory, long initialMemory, int numOfCombos) {
        long afterMemory = Runtime.getRuntime().freeMemory() / 1000000;
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        information.add("Total Memory: " + totalMemory + " MB");
        information.add("Free Memory Before: " + initialMemory + " MB");
        information.add("Row Sums: " + Operations.intArrayToString(startingSums[0]));
        information.add("Column Sums: " + Operations.intArrayToString(startingSums[1]));
        information.add("Number of Combinations: " + numOfCombos);
        information.add("Free Memory After: " + afterMemory + " MB");
        information.add("Free Memory Used: " + (initialMemory - afterMemory) + " MB");
        information.add("Time Taken: " + (timeTaken) + " milliseconds");
    }

}
