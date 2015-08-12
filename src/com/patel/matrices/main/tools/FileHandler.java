package com.patel.matrices.main.tools;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {
	
	//public String[] strings;
	
	public static String[] loadFile(String path) {
		String[] sums = new String[2];
		
		
		File file = new File(path);
		
		FileInputStream fis = null;
	    BufferedInputStream bis = null;
		

	    try {
	      fis = new FileInputStream(file);
	 
	      bis = new BufferedInputStream(fis);
	      
	      BufferedReader br = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8));
	 
	      while (bis.available() != 0) {
	    	 sums[0] = br.readLine();
	    	 sums[1] = br.readLine();
	      }
	 
	      // dispose all the resources after using them.
	      fis.close();
	      bis.close();
	 
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
		return sums;
	}
	
	public static int[][] getSums(String[] strings) {
		int sumRowSums = 0, sumColSums = 0;
		
		String[] rowSumStrings = null;
		String[] columnSumStrings = null;
		rowSumStrings= strings[0].split(",");
		columnSumStrings= strings[1].split(",");
		
		int[][] sums = new int[2][];
		
		int[] rowSums = new int[rowSumStrings.length];
		int[] columnSums = new int[columnSumStrings.length];
		
		for (int i = 0; i < rowSums.length; i++) {
			rowSums[i] = Integer.parseInt(rowSumStrings[i]);
		}
		
		for (int i = 0; i < columnSums.length; i++) {
			columnSums[i] = Integer.parseInt(columnSumStrings[i]);
		}
		
		for (int i = 0; i < rowSums.length; i++) {
			sumRowSums += rowSums[i];
		}
		
		for (int i = 0; i < columnSums.length; i++) {
			sumColSums += columnSums[i];
		}
		
		if (!(sumRowSums == sumColSums)) {
			//System.out.println("Sums do not match");
			//System.exit(0);
		}
		
		sums[0] = rowSums;
		sums[1] = columnSums;
		
		
		
		return sums;
	}
}
