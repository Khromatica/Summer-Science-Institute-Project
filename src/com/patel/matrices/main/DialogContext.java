package com.patel.matrices.main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogContext extends JFrame{
	JPanel mainPanel;
	JLabel error;
	
	private static final long serialVersionUID = 1L;

	public DialogContext() {
		this.setTitle("Error");
		this.setSize(410, 60);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		mainPanel = new JPanel();
		error = new JLabel("Place \"Combination Resources\" folder on Desktop");
		
		error.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		error.setForeground(Color.RED);
		
		this.getContentPane().add(mainPanel);
		mainPanel.add(error);
		
		this.setVisible(true);
	}
	
}
