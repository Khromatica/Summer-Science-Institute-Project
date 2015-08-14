package com.patel.matrices.main.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class PermutationGenerator {
	public static void main (String [] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sequenceLength = 0, numOfOnes = 0;
		String fileName = null;
		
        /*
        System.out.print("Enter Sequence Length");
        try {
        	sequenceLength = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.print("Enter Number of Ones:");
        
        try{
        	numOfOnes = Integer.parseInt(br.readLine());
        } catch(IOException e){
        	e.printStackTrace();
        }*/
        
        
        int startingNumber = 0, endingNumber = 0;
        
        System.out.print("Enter Number You Want to Start Generating Sequences of:");
        
        try {
        	startingNumber = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.print("Enter Number You Want to End Generating Sequences of:");
        
        try{
        	endingNumber = Integer.parseInt(br.readLine());
        } catch(IOException e){
        	e.printStackTrace();
        }
        
        for (int i = startingNumber; i <= endingNumber; i++) {
        	for (int j = 1; j <= i; j++) {
        		fileName = i + "C" + j;
        		
        		int[][] sequences = getIterativePermutations(i, j);
        		
        		writeReport(sequences, fileName);        		
        	}
        }
        
        
	}
	
	public static int iterativePermutationsCounter = 0;
	
	public static int[] intStringToArray(HashSet<String> strings, int index) {
        Object[] stringsArray = strings.toArray();
		int[] intSequence = new int[stringsArray[index].toString().length()];
		String stringSequence = stringsArray[index].toString();

		for (int i = 0; i < stringSequence.length(); i++) {
			intSequence[i] = Character.getNumericValue(stringSequence.charAt(i));
		}

		return intSequence;
	}
	
	public static int[][] getIterativePermutations(int sequenceLength, int numOfOnes) {
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
        permute(initialSequence,seq1);

		for (int i = 0; i < seq1.size(); i++) {
			sequences[i] = intStringToArray(seq1, i);
		}

		return sequences;

	}
    
    public static void permute(String s, HashSet<String> array) {
        if(null==s || s.isEmpty()) {
            return;
        }

        // List containing words formed in each iteration 
        List<String> strings = new LinkedList<String>();
        strings.add(String.valueOf(s.charAt(0))); // add the first element to the list

         // Temp list that holds the set of strings for 
         //  appending the current character to all position in each word in the original list
        List<String> tempList = new LinkedList<String>(); 

        for(int i=1; i< s.length(); i++) {

            for(int j=0; j<strings.size(); j++) {
                tempList.addAll(merge(s.charAt(i), strings.get(j)));
                            }
            strings.removeAll(strings);
            strings.addAll(tempList);

            tempList.removeAll(tempList);

        }

        for(int i=0; i<strings.size(); i++) {
            //System.out.println(strings.get(i));
        	iterativePermutationsCounter++;
            array.add(strings.get(i));
        }
    }

    /**
     * helper method that appends the given character at each position in the given string 
     * and returns a set of such modified strings 
     * - set removes duplicates if any(in case a character is repeated)
     */
    private static Set<String> merge(Character c,  String s) {
        if(s==null || s.isEmpty()) {
            return null;
        }

        int len = s.length();
        StringBuilder sb = new StringBuilder();
        Set<String> list = new HashSet<String>();

        for(int i=0; i<= len; i++) {
            sb = new StringBuilder();
            sb.append(s.substring(0, i) + c + s.substring(i, len));
            list.add(sb.toString());
        }

        return list;
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
    
    public static void writeReport(int[][] sequences, String fileName) {

    	String trueFileName = "fileReports/" + fileName;
    	
		try {
			File file = new File(trueFileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < sequences.length; i++) {
				bw.write(intArrayToString(sequences[i]));
				bw.newLine();
			}
			
			bw.close();
			fw.close();
			
			System.out.println("File Written Successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
