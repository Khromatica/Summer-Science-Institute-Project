package com.patel.extra;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursiveFileLinePositionGenerator {

    public static void main(String[] args){
        int[] myArray = {20,2,2,3,2,4};
        RecursiveFileLinePositionGenerator r = new RecursiveFileLinePositionGenerator();
        List<int[]> output =  r.generateCombinations(myArray, 5);
        for(int[] resArr : output){
            System.out.println(Arrays.toString(resArr));
        }
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