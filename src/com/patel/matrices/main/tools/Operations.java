package com.patel.matrices.main.tools;

public class Operations {

	public static boolean checkSums(int[][] sums) {
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

	public static int numOnes(int[] array) {
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

		if (Operations.checkSums(sums)) {
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

	public static void removeDuplicates() {

	}

}
