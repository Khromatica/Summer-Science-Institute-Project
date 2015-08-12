package com.patel.matrices.main.tools;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.*;

public class BinarySequencer {

	public static int counter = 0;
	
	public static int[][] getSequences(int sequenceLength, int numOfOnes) {
		// an arrayList for all possible sequences in string format.
		HashSet<String> seq1 = new LinkedHashSet<String>();

		// creates an ordered list starting with 0s and ending in 1s with the
		// correct number of 1s for the sequence.
		String initialSequence = "";

		// the array of integer arrays for which all sequences will be held
		// the inner arrays represent the first row of the matrix.
		int[][] sequences = new int[new Long(CombinatoricsUtils.binomialCoefficient(sequenceLength,numOfOnes)).intValue()][sequenceLength];

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

		for (int i = 0; i < seq1.size(); i++) {
			sequences[i] = intStringToArray(seq1, i);
		}

		return sequences;

	}

	// a shortened version of the createPermutations function with a smaller
	// number of inputs.
	public static void permutation(String str, HashSet<String> array) {
		createPermutations("", str, array);
	}

	private static void createPermutations(String prefix, String str, HashSet<String> array) {
		int n = str.length();
		if (n == 0) {
			array.add(prefix);
		} else {
			for (int i = 0; i < n; i++)
				createPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), array);
		}
	}


	// takes a String arrayList and adds each element in the String arrayList to
	// the int[] integer
	// format from the string.
	public static int[] intStringToArray(HashSet<String> strings, int index) {
        Object[] stringsArray = strings.toArray();
		int[] intSequence = new int[stringsArray[index].toString().length()];
		String stringSequence = stringsArray[index].toString();

		for (int i = 0; i < stringSequence.length(); i++) {
			intSequence[i] = Character.getNumericValue(stringSequence.charAt(i));
		}

		return intSequence;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	
	
	public static int[][] getPermutations(int sequenceLength, int numOfOnes) {
		// an arrayList for all possible sequences in string format.
		HashSet<String> seq1 = new LinkedHashSet<String>();

		// creates an ordered list starting with 0s and ending in 1s with the
		// correct number of 1s for the sequence.
		String initialSequence = "";

		// the array of integer arrays for which all sequences will be held
		// the inner arrays represent the first row of the matrix.
		int[][] sequences = new int[new Long(CombinatoricsUtils.binomialCoefficient(sequenceLength,numOfOnes)).intValue()][sequenceLength];

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
		StringBuilder strBuf = new StringBuilder(initialSequence);
        doPerm(strBuf,initialSequence.length(),seq1);
		permutation(initialSequence, seq1);

		for (int i = 0; i < seq1.size(); i++) {
			sequences[i] = intStringToArray(seq1, i);
		}

		return sequences;

	}
	
	
	@SuppressWarnings("unused")
	private static void doPerm(StringBuilder str, int index, HashSet<String> array){
        if(index <= 0) {
        	counter++;
            array.add(str.toString());
        }else { //recursively solve this by placing all other chars at current first pos
            doPerm(str, index - 1, array);
            int currPos = str.length()-index;
            for (int i = currPos+1; i < str.length(); i++) {//start swapping all other chars with current first char
                swap(str,currPos, i);
                doPerm(str, index-1, array);
                swap(str,i, currPos);//restore back my string buffer
            }
        }
    }

    private  static void swap(StringBuilder str, int pos1, int pos2){
        char t1 = str.charAt(pos1);
        str.setCharAt(pos1, str.charAt(pos2));
        str.setCharAt(pos2, t1);
    }

}
