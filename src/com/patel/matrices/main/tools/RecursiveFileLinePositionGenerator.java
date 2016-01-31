package com.patel.matrices.main.tools;

import java.util.ArrayList;
import java.util.List;

public class RecursiveFileLinePositionGenerator {
    public int[][] returnCombinations(int[][] sums, int length) {
    	int[][] result;
    	int[] array = new int[sums[0].length];
    	
    	for(int i = 0; i < sums[0].length; i++) {
    		array[i] = (int) Binomial.binomial(sums[1].length,sums[0][i]) - 1;
    	}
    	
    	List<int[]> tempCombos = generateCombinations(array, array.length-1);
    	result = new int[tempCombos.size()][];
    	
    	for(int i = 0; i < result.length; i++) {
    		result[i] = tempCombos.get(i);
    	}
    	
    	return result;
    }

    List<int[]> generateCombinations(int[] array, int length){
        List<int[]> output = new ArrayList<int[]>();
        if(length == 0){
            for(int i = array[length]; i>=0; i--){
                output.add(RecursiveFileLinePositionGenerator.createArray(i));
            }
        }else{
            List<int[]> partialOutput = generateCombinations(array, length - 1);
            for (int j = array[length]; j>=0; j--){
                for (int[] arr : partialOutput){
                    output.add(RecursiveFileLinePositionGenerator.concatenateArray(arr, j));
                }
            }
        }
        return output;
    }

    public static int[] concatenateArray(int[] original, int addition){
        int[] newArray = new int[original.length + 1];
        for(int i = 0 ; i < original.length; i++){
            newArray[i] = original[i];
        }
        newArray[original.length] = addition;
        return newArray;
    }

    public static int[] createArray(int num){
        return new int[] {num};
    }


}