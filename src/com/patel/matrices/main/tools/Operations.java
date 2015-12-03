package com.patel.matrices.main.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;

import com.patel.matrices.matrix.Matrix;

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

	public static int[][] fileTo2DArray(File file) {
		String[] fileName = file.getName().split("C");
		int n = Integer.parseInt(fileName[0]);
		int k = Integer.parseInt(fileName[1].split(Pattern.quote("."))[0]);
		
		int lengthOfFile = (int) CombinatoricsUtils.binomialCoefficient(n, k);
		
		int[][] result = new int[lengthOfFile][n];
		
		FileInputStream fis = null;
	    BufferedInputStream bis = null;

	    try {
	      fis = new FileInputStream(file);
	 
	      bis = new BufferedInputStream(fis);
	      
	      BufferedReader br = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8));
	      
	      for (int i = 0; i < result.length; i++) {
	    	  result[i] = Operations.stringToIntArray(br.readLine());
	      }
	      
	      br.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return result;
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
	
	public static int countLines(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		return lines;
	}

	public static int[][] buildFileLinePositions(ArrayList<File> files){
		int n = 1;
		
		int[] linesInEachFile = new int[files.size()];
		
		for (int i = 0; i < linesInEachFile.length; i++) {
			try {
				linesInEachFile[i] = Operations.countLines(files.get(i));
				n *= linesInEachFile[i];
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		int[][] fileLinePositions = new int[n][files.size()];
		
		for (int i = 0; i < fileLinePositions.length; i++) {
		}
		
		return fileLinePositions;
	}
	
	public int[] buildFilePosition(int[] linesInEachFile ){
		return null;
	}

	public static Matrix getMatrixFromFile(ArrayList<File> chooseFiles, int[] fileLinePositions, int[] rowSums, int[] colSums) {
		int numRows = rowSums.length;
		int numCols = colSums.length;
		
		Matrix result = new Matrix(numRows, numCols);
		
		for (int i = 0; i < fileLinePositions.length; i++) {
			try {
				for (int j = 0; j < chooseFiles.size(); j++) {
					result.setRow(i, Operations.stringToIntArray(FileUtils.readLines(chooseFiles.get(j)).get(fileLinePositions[i])));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
