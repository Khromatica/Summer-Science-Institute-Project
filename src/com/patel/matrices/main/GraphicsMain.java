package com.patel.matrices.main;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.Operations;
import com.patel.matrices.matrix.DoubleMatrix;
import com.patel.matrices.matrix.Matrix;
import com.patel.matrices.matrix.MatrixGenerator;

public class GraphicsMain implements ActionListener{
	static MatrixGenerator mg = new MatrixGenerator();
	
	int [][] initialSums;
	
	public String rowSumString, colSumString;
	
	private final static String i0 = " An Algorithm to Generate Binary Matrix Solutions With Given Row and Column Sums";
	private final static String i1 = " Programmed by: Kishan Patel";
	private final static String i2 = "---------------------------------------------------------------------------------------------------------------------------------------------------------";
	private final static String i3 = " Input a set of row sums and column sums in the form 1234... The algorithm will then output an average matrix";
	private final static String i4 = " once \"Generate Average Matrix\" is clicked. The percentage output will be shown in the bottom text area.";
	private final static String i5 = " To save all matrix solutions to a text file, select the \"File Output\" checkbox and select a folder destination";
	private final static String i6 = " for the output. Restart the program in between new sets of sums to avoid any errors.";
	private final static String newLine = "\n";
	
	public JFrame frame;
	
	public JTextArea instructions;
	public JTextArea matrixOutput;
	
	public JFormattedTextField sumsInput1;
	public JFormattedTextField sumsInput2;
	public JFormattedTextField fileOutput;
	
	public JCheckBox file;
	
	public JButton generate;
	public JButton close;
	public JButton restart;
	public JButton openDirec;
	public JButton saveFile;
	
	public JFileChooser chooser;
	
	public boolean inputClicked = false;
	public boolean saveClicked = false;
	public boolean generateRun = false;
	
	public JLabel rowSums;
	public JLabel columnSums;
	public JLabel matrixLabel;
	public JLabel checkBox;
	
	public JLabel invisibleLabel1;
	public JLabel invisibleLabel2;
	public JLabel instructionLabelInvis;
	
	public JPanel mainPanel;
	public JPanel instructionPanel;
	public JPanel outputPanel;
	
	public String outputFileDirectory;
	
	public static final int WIDTH = 800, HEIGHT = 700;
	
	public Matrix[] solutions;
	public DoubleMatrix avg;
	
	public GraphicsMain() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Binary Matrix Solution Generator");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		instructionPanel = new JPanel();
		outputPanel = new JPanel();

		
		
		instructions = new JTextArea(5,1);

		rowSums = new JLabel(" Row Sums:   ");
		columnSums = new JLabel(" Column Sums:");
		matrixLabel = new JLabel("Average Matrix Output:");
		
		sumsInput1 = new JFormattedTextField();
		sumsInput2 = new JFormattedTextField();
		
		fileOutput = new JFormattedTextField();
		file = new JCheckBox("File Output  ");
		
		invisibleLabel1 = new JLabel(" ");
		invisibleLabel2 = new JLabel(" ");
		instructionLabelInvis = new JLabel(" ");
		
		generate = new JButton("Generate Average Matrix");
		close = new JButton("Exit Program");
		restart = new JButton("Restart");
		openDirec = new JButton("Save to...");
		saveFile = new JButton("Save File");
		
		matrixOutput = new JTextArea();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		instructionLabelInvis.setPreferredSize(new Dimension(3, 13));
		
		instructions.setEditable(false);
		instructions.setBackground(Color.WHITE);
		instructions.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		instructions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		instructions.setPreferredSize(new Dimension(WIDTH - 32, 160));
		
		instructions.append(i0 + newLine);
		instructions.append(i1 + newLine);
		instructions.append(i2 + newLine);
		instructions.append(i3 + newLine);
		instructions.append(i4 + newLine);
		instructions.append(i5 + newLine);
		instructions.append(i6);

		rowSums.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		columnSums.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		matrixLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		
		rowSums.setPreferredSize(new Dimension(111, 13));
		columnSums.setPreferredSize(new Dimension(111, 13));
		
		sumsInput1.addActionListener(this);
		sumsInput1.setEditable(true);
		sumsInput1.setBackground(Color.WHITE);
		sumsInput1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sumsInput1.setPreferredSize(new Dimension(WIDTH - 140, 25));
		
		sumsInput2.addActionListener(this);
		sumsInput2.setEditable(true);
		sumsInput2.setBackground(Color.WHITE);
		sumsInput2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sumsInput2.setPreferredSize(new Dimension(WIDTH - 140, 25));
		
		fileOutput.setEditable(false);
		fileOutput.setBackground(Color.WHITE);
		fileOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fileOutput.setPreferredSize(new Dimension(WIDTH - 240, 25));
		
		file.setSelected(false);
		file.setPreferredSize(new Dimension(111, 25));
		file.addActionListener(this);
		file.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		
		invisibleLabel1.setPreferredSize(new Dimension(187, 13));
		invisibleLabel2.setPreferredSize(new Dimension(WIDTH, 5));
		
		openDirec.addActionListener(this);
		openDirec.setEnabled(false);
		openDirec.setPreferredSize(new Dimension(95, 36));
		openDirec.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		
		generate.addActionListener(this);
		generate.setPreferredSize(new Dimension(199, 36));
		generate.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		
		saveFile.addActionListener(this);
		saveFile.setPreferredSize(new Dimension(199,36));
		saveFile.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		saveFile.setEnabled(false);
		
		close.addActionListener(this);
		close.setPreferredSize(new Dimension(199,36));
		close.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		
		restart.addActionListener(this);
		restart.setPreferredSize(new Dimension(199,36));
		restart.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		
		matrixOutput.setEditable(false);
		matrixOutput.setBackground(Color.WHITE);
		matrixOutput.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		matrixOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		matrixOutput.setPreferredSize(new Dimension(WIDTH - 30, 265));
		
		frame.getContentPane().add(mainPanel);

		mainPanel.add(instructionPanel);
		mainPanel.add(outputPanel);
		
		instructionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		instructionPanel.add(instructionLabelInvis);
		instructionPanel.add(instructions);
		
		instructionPanel.setPreferredSize(new Dimension(WIDTH, 220));
		
		instructionPanel.add(rowSums);
		instructionPanel.add(sumsInput1);
		instructionPanel.add(columnSums);
		instructionPanel.add(sumsInput2);
		instructionPanel.add(file);
		instructionPanel.add(openDirec);
		instructionPanel.add(fileOutput);
		instructionPanel.add(invisibleLabel1);
		instructionPanel.add(generate);
		instructionPanel.add(saveFile);
		
		outputPanel.add(matrixLabel);
		outputPanel.add(matrixOutput);
		outputPanel.add(invisibleLabel2);
		outputPanel.add(restart);
		outputPanel.add(close);
		
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GraphicsMain();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generate) {
			if (sumsInput1.getText() != "" && sumsInput2.getText() != "") {
				rowSumString = sumsInput1.getText();
				colSumString = sumsInput2.getText();
				inputClicked = true;
			}
			
			if (inputClicked) {
				matrixOutput.setText(null);
				double[][] tempArray;
				String lineToOutput = "";
				String[] tempSums = new String[2];
				String[] matrixArray;
				boolean checkSums = false;
				boolean trivial = false;
				
				tempSums[0] = rowSumString;
				tempSums[1] = colSumString;
				
				initialSums = Operations.stringArrayto2DIntArray(tempSums);
				
				checkSums = Operations.checkSums(initialSums);
				trivial = Operations.checkTrivial(initialSums);
				
				
				
				if(!checkSums) {
					matrixOutput.setText(null);
					matrixOutput.setForeground(Color.RED);
					matrixOutput.setText("Invalid Sums");
				} else if (trivial){
					matrixOutput.setText(null);
					matrixOutput.setForeground(Color.RED);
					matrixOutput.setText("Unmanageable Case");
				} else {
					try {
						solutions = mg.returnSolutions(initialSums);
						avg = mg.returnAverage(solutions);					
						matrixArray = new String[avg.getNumRows()];
						tempArray = avg.getMatrixArray();
						
						for(int i = 0; i < matrixArray.length; i++) {
							matrixArray[i] = Operations.doubleArrayToString(tempArray[i]);
						}
					
						for(int i = 0; i < avg.getNumRows(); i++) {
							lineToOutput = matrixArray[i];
							matrixOutput.append(lineToOutput + newLine);
						}
					
						inputClicked = false;
						saveFile.setEnabled(true);
					} catch (Exception exp) {
						System.err.println("Error with sums");
					}
				}
				
			}
		}
		
		if (e.getSource() == close) {
			System.exit(0);
		}
		
		if (e.getSource() == restart) {
			frame.dispose();
			new GraphicsMain();
		}
		
		if (e.getSource() == file) {
			if (file.isSelected()) {
				openDirec.setEnabled(true);
			} else {
				openDirec.setEnabled(false);
				fileOutput.setText(null);
			}
		}
		
		if (e.getSource() == openDirec) {
			chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setDialogTitle("Choose file");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    
		    chooser.setAcceptAllFileFilterUsed(false);
		    
		    if (chooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) { 
		    	fileOutput.setText(chooser.getSelectedFile().getPath());
		    	outputFileDirectory = chooser.getSelectedFile().getPath() + "\\solutions.txt";
		    	saveClicked = true;
		    }
		}

		if (e.getSource() == saveFile) {
			if (saveClicked) {
				Operations.outputMatrixTextFile(solutions, outputFileDirectory, initialSums);
				try {
					Desktop.getDesktop().open(new File(outputFileDirectory));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				matrixOutput.setForeground(Color.GREEN);
				matrixOutput.append(newLine);
				matrixOutput.append("Solutions outputted successfully!");
			}
		}
	}
	
	
}
