package com.patel.matrices.main.tools;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class BinarySequencer {

	public static int[][] getSequences(int sequenceLength, int numOfOnes) {
		// an arrayList for all possible sequences in string format.
		ArrayList<String> seq1 = new ArrayList<String>();

		// creates an ordered list starting with 0s and ending in 1s with the
		// correct number of 1s for the sequence.
		String initialSequence = "";

		// the array of integer arrays for which all sequences will be held
		// the inner arrays represent the first row of the matrix.
		int[][] sequences = new int[binomial(sequenceLength, numOfOnes)][sequenceLength];

		// the next two for loops create the initial sequence with an
		// appropriate number of 1s.
		for (int i = 0; i < sequenceLength - numOfOnes; i++) {
			initialSequence += "0";
		}

		for (int i = 0; i < numOfOnes; i++) {
			initialSequence += "1";
		}

		// takes the initial sequence and permutes it in every way, and inserts
		// each permutation
		// into the seq1 String array.

		permutation(initialSequence, seq1);
		// removes repeated strings found in the seq1 String array due to the
		// overcounting in permutation();
		checkRepeats(seq1);

		for (int i = 0; i < seq1.size(); i++) {
			sequences[i] = intStringToArray(seq1, i);
		}

		return sequences;

	}

	// the factorial function is used in calculating the factorial of a number,
	// usually used
	// for the binomial function.
	public static int factorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			n = n * factorial(n - 1);
		}

		return n;
	}

	// gives the number of ways "b" items can be chosen from a set of "a" items.
	public static int binomial(int a, int b) {
		int value = 0;

		if (a >= b) {
			value = (factorial(a) / (factorial(b) * factorial(a - b)));
			return value;
		} else {
			return 0;
		}
	}

	// a shortened version of the createPermutations function with a smaller
	// number of inputs.
	public static void permutation(String str, ArrayList<String> array) {
		createPermutations("", str, array);
	}

	private static void createPermutations(String prefix, String str, ArrayList<String> array) {
		int n = str.length();
		if (n == 0) {
			array.add(prefix);
		} else {
			for (int i = 0; i < n; i++)
				createPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), array);
		}
	}

	public static void permutation(String str) {
		createPermutations("", str);
	}

	// a method that generates permutations of a given string, and adds them to
	// the array specified
	// in the input field.
	private static void createPermutations(String prefix, String str) {
		int n = str.length();
		if (n == 0) {
			System.out.println(prefix);
			System.out.println("Hello");
		} else {
			for (int i = 0; i < n; i++)
				createPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
		}
	}

	// takes a String arrayList and adds each element in the String arrayList to
	// the int[] integer
	// format from the string.
	public static int[] intStringToArray(ArrayList<String> strings, int index) {
		int[] intSequence = new int[strings.get(index).length()];
		String stringSequence = strings.get(index);

		for (int i = 0; i < stringSequence.length(); i++) {
			intSequence[i] = Character.getNumericValue(stringSequence.charAt(i));
		}

		return intSequence;
	}

	public static void checkRepeats(ArrayList<String> array) {
		Set<String> hs = new LinkedHashSet<>();
		hs.addAll(array);
		array.clear();
		array.addAll(hs);
	}

}
