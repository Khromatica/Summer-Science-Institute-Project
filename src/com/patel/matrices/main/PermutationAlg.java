package com.patel.matrices.main;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

public class PermutationAlg {
	public static void main (String[] args) {
		PermutationAlg pa = new PermutationAlg();
		pa.test();
	}
	
	public void test(){
	    int n = 5;
	    int x = 2;

	    Set<BigInteger> result = new LinkedHashSet<>();
	    for (int j = x; j > 0; j--) {
	        Set<BigInteger> a = new LinkedHashSet<>();

	        for (int i = 0; i < n - j + 1; i++) {
	            if (j == x) {
	                a.add(BigInteger.ZERO.flipBit(i));
	            } else {
	                for (BigInteger num : result) {
	                    if (num != null && !num.testBit(i) && (i >= (n - j) || num.getLowestSetBit() >= i-1))
	                        a.add(num.setBit(i));
	                }
	            }
	        }
	        result = a;
	    }

	    String zeros = new String(new char[n]).replace("\0", "0");
	    for (BigInteger i : result) {
	        String binary = i.toString(2);
	        System.out.println(zeros.substring(0, n - binary.length()) + binary);
	    }
	    System.out.println(result.size());
	}
}
