package com.benthejunebug.funkyButton;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Run {
	
	
	public static JFrame frame;
	
	public static void main(String[] args) {
		
		frame = new JFrame("Funky Button!");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		Thread button = new Thread(new Button());
		Thread button2 = new Thread(new Button());
		button.start();
		button2.start();
	}

}
