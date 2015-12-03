package com.patel.matrices.main.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class RecursiveFileLinePositionGenerator {

    public static void main(String[] args){
        int[] myArray = {252,252,252};
        RecursiveFileLinePositionGenerator r = new RecursiveFileLinePositionGenerator();
        List<int[]> output =  r.generateCombinations(myArray, 2);
        int counter = 0;
        for(int[] resArr : output){
            System.out.println(Arrays.toString(resArr));
            counter++;
        }
        System.out.println(counter);
    }
    
    public int[][] returnCombinations(int[][] sums, int length) {
    	int[][] result;
    	int[] array = new int[sums[0].length];
    	
    	for(int i = 0; i < sums[0].length; i++) {
    		array[i] = (int) CombinatoricsUtils.binomialCoefficient(sums[1].length,sums[0][i]) - 1;
    	}
    	
    	List<int[]> tempCombos = generateCombinations(array, length);
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