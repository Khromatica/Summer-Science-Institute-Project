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
		MatrixGenerator mg = new MatrixGenerator();
		
		Operations.print2DArray(startingSums);
		
		Matrix[] solutions = mg.returnSolutions(startingSums);
		DoubleMatrix avg = mg.returnAverage(solutions);
		
		System.out.println(solutions.length + " solutions");
		System.out.println();
		System.out.println("Average Matrix:");
		avg.print();
	}

}
