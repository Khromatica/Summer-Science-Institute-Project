package com.patel.matrices.main;

import java.util.HashSet;
import java.util.Set;

public class PermTest {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String str = "abcdef";
        StringBuilder strBuf = new StringBuilder(str);
        doPerm(strBuf,str.length());

        //System.out.println(generatePerm("abcdef"));
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
    }

    private static void doPerm(StringBuilder str, int index){

        if(index <= 0) {
            System.out.println(str);
        }else { //recursively solve this by placing all other chars at current first pos
            doPerm(str, index - 1);
            int currPos = str.length()-index;
            for (int i = currPos+1; i < str.length(); i++) {//start swapping all other chars with current first char
                swap(str,currPos, i);
                doPerm(str, index-1);
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