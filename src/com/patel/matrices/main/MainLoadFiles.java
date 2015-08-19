package com.patel.matrices.main;

import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.Operations;

public class MainLoadFiles {
	public static void main(String[] args) {
		int[][] FIVE_CHOOSE_THREE = FileHandler.getPermutations("res/Binary Sequences/5/5C3.txt");
		for (int i = 0; i < FIVE_CHOOSE_THREE.length; i++) {
			Operations.printArray(FIVE_CHOOSE_THREE[i]);	
		}
	}
}
