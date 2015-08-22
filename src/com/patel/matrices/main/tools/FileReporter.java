package com.patel.matrices.main.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.patel.matrices.matrix.Matrix;

public class FileReporter {

	public static void writeReport(ArrayList<String> info, ArrayList<Matrix> matrices) {

		String dateTime = LocalDateTime.now().toString();
		String date;
		String origTime;
		if (dateTime.length() == 23) {
			date = dateTime.substring(0, 10);
			origTime = dateTime.substring(11, 19);
		} else {
			date = dateTime.substring(0, 10);
			origTime = dateTime.substring(12, 19);
		}

		String time = origTime.replace(":", ".");

		String fileName = "fileReports/Report-" + date + "-" + time + ".txt";

		try {
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(info.get(0));
			bw.newLine();
			bw.write(info.get(1));
			bw.newLine();
			bw.newLine();
			bw.write(info.get(2));
			bw.newLine();
			bw.write(info.get(3));
			bw.newLine();
			bw.newLine();

			bw.write(info.get(4));

			bw.newLine();
			bw.newLine();

			for (int i = 0; i < matrices.size(); i++) {
				for (int a = 0; a < matrices.get(i).getMatrixCellArray().length; a++) {
					for (int b = 0; b < matrices.get(i).getMatrixCellArray()[a].length; b++) {
						bw.write(matrices.get(i).getMatrixCellArray()[a][b].getValue() + " ");
					}
					bw.newLine();
				}
				bw.newLine();
			}

			bw.newLine();

			bw.write(info.get(5));
			bw.newLine();
			bw.write(info.get(6));

			bw.newLine();
			bw.newLine();

			bw.write(info.get(7));

			bw.close();

			System.out.println("File Written Successfully");
			System.out.println(date);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
