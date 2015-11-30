package com.patel.matrices.main;

import java.util.ArrayList;

import com.patel.matrices.main.tools.BinarySequencer;
import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.FileReporter;
import com.patel.matrices.main.tools.Operations;
import com.patel.matrices.matrix.Matrix;

@SuppressWarnings("unused")
public class Main {
	static int[][] startingSums = FileHandler.getSums(FileHandler.loadFile("res/vec.txt"));
	static int[][] correctedSums;
	static int numOfRows = startingSums[1].length;
    static int numOfColumns = startingSums[1].length;
    static int lastValueInRowSums = startingSums[0][startingSums[0].length - 1];

	public static void main(String[] args) {

		Matrix matrix = new Matrix(1,1);
		
		ArrayList<String> information = new ArrayList<String>();    //print information
		ArrayList<Matrix> solutionMatrices = new ArrayList<Matrix>();    //where the solutions go
		
		Operations.sort(startingSums);
		correctedSums = matrix.fixTrivialCases(startingSums);
		
		System.out.println("Here are our rowSums and colSums");

        long startTime = System.currentTimeMillis();
        long totalMemory = Runtime.getRuntime().totalMemory() / 1000000;
        long initialMemory = Runtime.getRuntime().freeMemory() / 1000000;
        
        Operations.print2DArray(correctedSums);
		
        //printInformation(information, startTime, totalMemory, initialMemory);
		//FileReporter.writeReport(information,  solutionMatrices);
	}
    private static void printInformation(ArrayList<String> information, long startTime, long totalMemory, long initialMemory) {
        long afterMemory = Runtime.getRuntime().freeMemory() / 1000000;
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        information.add("Total Memory: " + totalMemory + " MB");
        information.add("Free Memory Before: " + initialMemory + " MB");
        information.add("Row Sums: " + Operations.intArrayToString(startingSums[0]));
        information.add("Column Sums: " + Operations.intArrayToString(startingSums[1]));
        information.add("Free Memory After: " + afterMemory + " MB");
        information.add("Free Memory Used: " + (initialMemory - afterMemory) + " MB");
        information.add("Time Taken: " + (timeTaken) + " milliseconds");
    }

}
