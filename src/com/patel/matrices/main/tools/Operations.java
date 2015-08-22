package com.patel.matrices.main.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Operations {

	public static boolean checkRowSumsAndColSumsAreEqual(int[][] sums) {
		boolean equal = false;
		int sum1 = 0;
		int sum2 = 0;

		for (int i = 0; i < sums[0].length; i++) {
			sum1 += sums[0][i];
		}

		for (int j = 0; j < sums[1].length; j++) {
			sum2 += sums[1][j];
		}

		if (sum1 == sum2) {
			equal = true;
		}

		return equal;
	}

	public static int countOfOnes(int[] array) {
		int num = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				num += 1;
			}
		}

		return num;
	}

	public static void sort(int[][] array) {
		MergeSorter sorter = new MergeSorter();
		sorter.sort(array[0]);
		sorter.sort(array[1]);
	}

	public static void printSums(int[][] sums) {
		System.out.print("Row Sums: ");

		printArray(sums[0]);

		System.out.print("Column Sums: ");

		printArray(sums[1]);

		if (Operations.checkRowSumsAndColSumsAreEqual(sums)) {
			System.out.println("Matrix is not impossible");
		} else {
			System.out.println("Matrix is impossible");
			System.exit(0);
		}
	}

	public static void printCombos(int[][] combos) {
		System.out.println("All Row Combinations: ");
		for (int i = 0; i < combos.length; i++) {
			for (int j = 0; j < combos[i].length; j++) {
				System.out.print(combos[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static void print2DArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			printArray(array[i]);
		}
	}

	public static String intArrayToString(int[] array) {
		String string = "";

		for (int i = 0; i < array.length; i++) {
			if (i != array.length - 1) {
				string += array[i] + " ";
			} else {
				string += array[i];
			}
		}

		return string;
	}
	
	public static int[] stringToIntArray(String string) {
		int[] array = new int[(string.length()+1)/2];
		for (int i = 0; i < string.length(); i+=2) {
			array[i/2] = Integer.parseInt(Character.toString(string.charAt(i)));
		}
		
		return array;
	}

	public static void removeDuplicates() {

	}
	
	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}

}
