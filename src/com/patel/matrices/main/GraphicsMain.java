package com.patel.matrices.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.patel.matrices.main.tools.FileHandler;
import com.patel.matrices.main.tools.Operations;
import com.patel.matrices.matrix.DoubleMatrix;
import com.patel.matrices.matrix.Matrix;
import com.patel.matrices.matrix.MatrixGenerator;

public class GraphicsMain implements ActionListener{
	static int[][] startingSums = FileHandler.getSums(FileHandler.loadFile("res/vec.txt"));
	
	static MatrixGenerator mg = new MatrixGenerator();
	
	public String rowSumString, colSumString;
	
	private final static String i0 = "An Algorithm to Generate Binary Matrix Solutions With Given Row and Column Sums";
	private final static String i1 = "Kishan Patel";
	private final static String i2 = "American Heritage School, Plantation, Florida";
	private final static String newLine = "\n";
	
	public JFrame frame;
	
	public JTextArea instructions;
	public JTextArea matrixOutput;
	
	public JFormattedTextField sumsInput1;
	public JFormattedTextField sumsInput2;
	
	public JButton generate;
	public JButton close;
	public JButton restart;
	
	public boolean inputClicked = false;
	
	public JLabel rowSums;
	public JLabel columnSums;
	public JLabel matrixLabel;
	
	public JLabel invisibleLabel1;
	public JLabel invisibleLabel2;
	public JLabel instructionLabelInvis;
	
	public JPanel mainPanel;
	public JPanel instructionPanel;
	public JPanel outputPanel;
	
	public static final int WIDTH = 800, HEIGHT = 510;
	
	public Matrix[] solutions;
	public DoubleMatrix avg;
	
	public GraphicsMain() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Summer Science Fair Institute Project");
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
		
		invisibleLabel1 = new JLabel(" ");
		invisibleLabel2 = new JLabel(" ");
		instructionLabelInvis = new JLabel(" ");
		
		generate = new JButton("Generate Average Matrix");
		close = new JButton("Exit Program");
		restart = new JButton("Restart");
		
		matrixOutput = new JTextArea();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		instructionLabelInvis.setPreferredSize(new Dimension(3, 13));
		
		instructions.setEditable(false);
		instructions.setBackground(Color.WHITE);
		instructions.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		instructions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		instructions.setPreferredSize(new Dimension(WIDTH - 32, 100));
		
		instructions.append(i0 + newLine);
		instructions.append(i1 + newLine);
		instructions.append(i2 + newLine);

		rowSums.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		columnSums.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		matrixLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		
		rowSums.setPreferredSize(new Dimension(111, 13));
		columnSums.setPreferredSize(new Dimension(111, 13));
		
		sumsInput1.addActionListener(this);
		sumsInput1.setEditable(true);
		sumsInput1.setBackground(Color.WHITE);
		sumsInput1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sumsInput1.setPreferredSize(new Dimension(WIDTH - 200, 25));
		
		sumsInput2.addActionListener(this);
		sumsInput2.setEditable(true);
		sumsInput2.setBackground(Color.WHITE);
		sumsInput2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sumsInput2.setPreferredSize(new Dimension(WIDTH - 200, 25));
		
		invisibleLabel1.setPreferredSize(new Dimension(287, 13));
		invisibleLabel2.setPreferredSize(new Dimension(WIDTH, 13));
		
		generate.addActionListener(this);
		generate.setPreferredSize(new Dimension(199, 36));
		generate.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		
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
		matrixOutput.setPreferredSize(new Dimension(WIDTH - 30, 160));
		
		frame.getContentPane().add(mainPanel);

		mainPanel.add(instructionPanel);
		mainPanel.add(outputPanel);
		
		instructionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		instructionPanel.add(instructionLabelInvis);
		instructionPanel.add(instructions);
		
		instructionPanel.add(rowSums);
		instructionPanel.add(sumsInput1);
		instructionPanel.add(columnSums);
		instructionPanel.add(sumsInput2);
		instructionPanel.add(invisibleLabel1);
		instructionPanel.add(generate);
		
		
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
				
				tempSums[0] = rowSumString;
				tempSums[1] = colSumString;
				
				int [][] initialSums = Operations.stringArrayto2DIntArray(tempSums);
				
				checkSums = Operations.checkSums(initialSums);
				
				if(!checkSums) {
					matrixOutput.setText(null);
					matrixOutput.setForeground(Color.RED);
					matrixOutput.setText("Invalid Sums");
				}
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
				} catch (Exception exp) {
					System.err.println("Error with sums");
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

	}
}
