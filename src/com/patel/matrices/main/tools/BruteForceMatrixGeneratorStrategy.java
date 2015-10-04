package com.patel.matrices.main.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.patel.matrices.matrix.Matrix;

@SuppressWarnings("all")
public class BruteForceMatrixGeneratorStrategy {

	static Map<String, String> chooseToFile;
	
	public static void main (String[] args) {
		BruteForceMatrixGeneratorStrategy bf = new BruteForceMatrixGeneratorStrategy();
		
		int[] rowSums = {1,1,1,1};
		int[] colSums = {1,1,1,1};
		
		bf.generate(rowSums,colSums);
	}
	
	public ArrayList<Matrix> generate(int[] rowSums, int[] colSums) {
		
		initHashMap();
		
		ArrayList<Matrix> t = new ArrayList<Matrix>();
		
		ArrayList<File> chooseFiles = new ArrayList<File>();
		
		ArrayList<int [][]> c = new ArrayList<int[][]>();
		
		for(int i = 0; i < rowSums.length; i++){
			File f = new File("res/Binary Sequences/" + colSums.length + "/" + chooseToFile.get(colSums.length + "|" + rowSums[i]));
			chooseFiles.add(f);
		}
		
		for(int i = 0; i < chooseFiles.size(); i++){
			c.add(Operations.fileTo2DArray(chooseFiles.get(i)));
		}
		
		//3: Combine the chooses in every different order, iteratively
		
		return t;
	}

	public static void initHashMap() {
		chooseToFile = new HashMap<String, String>();
		
		for (int i = 1; i < 21; i++) {
			for (int j = 1; j <= i; j++) {
				chooseToFile.put(i + "|" + j, i + "C" + j + ".txt");
			}
		}
	}
}
