package com.patel.matrices.main;

import java.util.ArrayList;

import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.Operations;
import com.patel.matrices.main.tools.RecursiveFileLinePositionGenerator;
import com.patel.matrices.matrix.AverageCalculator;
import com.patel.matrices.matrix.DoubleMatrix;
import com.patel.matrices.matrix.Matrix;
import com.patel.matrices.matrix.MatrixGenerator;

@SuppressWarnings("unused")
public class Main {
	static int[][] startingSums = FileHandler.getSums(FileHandler.loadFile("res/vec.txt"));

	public static void main(String[] args) {

		Matrix matrix = new Matrix(1,1);
		MatrixGenerator mg = new MatrixGenerator();
		RecursiveFileLinePositionGenerator rflpg = new RecursiveFileLinePositionGenerator();
		AverageCalculator avgCalc = new AverageCalculator();
		
		int[][] correctedSums = matrix.fixTrivialCases(startingSums);
		int[][] linePositions = rflpg.returnCombinations(correctedSums, correctedSums[1].length - 1);
		
		Matrix[] solutions = mg.generateSolutions(correctedSums, linePositions);
		
		int counter = 0;
		for(int i = 0; i < solutions.length; i++) {
			solutions[i].print();
			System.out.println();
			counter++;
		}
		System.out.println(counter);
		System.out.println();
		System.out.println("Average Matrix:");
		
		DoubleMatrix avg = avgCalc.average(solutions);
		
		avg.print();
		
		ArrayList<String> information = new ArrayList<String>();    //print information
		ArrayList<Matrix> solutionMatrices = new ArrayList<Matrix>();    //where the solutions go

        long startTime = System.currentTimeMillis();
        long totalMemory = Runtime.getRuntime().totalMemory() / 1000000;
        long initialMemory = Runtime.getRuntime().freeMemory() / 1000000;
		
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
