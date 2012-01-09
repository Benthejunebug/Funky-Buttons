package com.benthejunebug.funkyButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Run {

	public static JFrame frame;
	public static ArrayList<Thread> threads = new ArrayList<Thread>();
	private static int threadIndex = 0;
	private static ArrayList<Button> buttonObject = new ArrayList<Button>();
	public static TextField btnIndexField;
	public static JCheckBox hor;
	public static JCheckBox vert;
	public static JButton xPos;
	public static JButton yPos;
	public static JButton addButton;
	public static JButton removeButton;
	public static JButton difficulty;
	public static JFrame options;
	private static int selectedIndex = 0;
	private static boolean hasWon;

	public static void main(String[] args) {

		frame = new JFrame("Funky Button!");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		new Button();
		frame.getContentPane().setBackground(Color.BLACK);
		options = new JFrame("Options");
		options.setAlwaysOnTop(true);
		options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		options.setLayout(new FlowLayout());
		hor = new JCheckBox("Horizontal");
		vert = new JCheckBox("Vertical");
		xPos = new JButton();
		yPos = new JButton();
		addButton = new JButton("Add Button");
		removeButton = new JButton("Remove Button");
		btnIndexField = new TextField("Enter Number of btn");
		new Button();
		difficulty = new JButton(Button.difficultyEasy ? "HARD MODE"
				: "EASY MODE");
		hor.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				try {
					((Button) (buttonObject.get(Integer.parseInt(btnIndexField
							.getText()) - 1))).horAction();
					setSelectedIndex();
				} catch (Exception e) {
					((Button) (buttonObject.get(selectedIndex))).horAction();
				}
			}

		});
		vert.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				try {
					((Button) (buttonObject.get(Integer.parseInt(btnIndexField
							.getText()) - 1))).vertAction();
					setSelectedIndex();
				} catch (Exception e) {
					((Button) (buttonObject.get(selectedIndex))).vertAction();
				}
			}

		});
		xPos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					((Button) (buttonObject.get(Integer.parseInt(btnIndexField
							.getText()) - 1))).xPositiveAction();
					setSelectedIndex();
				} catch (Exception e) {
					((Button) (buttonObject.get(selectedIndex)))
							.xPositiveAction();
				}
			}

		});
		yPos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					((Button) (buttonObject.get(Integer.parseInt(btnIndexField
							.getText()) - 1))).yPositiveAction();
					setSelectedIndex();
				} catch (Exception e) {
					((Button) (buttonObject.get(selectedIndex)))
							.yPositiveAction();
				}
			}
		});
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					int size = Integer.parseInt(JOptionPane
							.showInputDialog("Enter the size of the button in pixels:"));
					addButton(size);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"You did not enter an integer!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setSelectedIndex();
				removeButton();

			}

		});
		difficulty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (Button.difficultyEasy) {
					Button.difficultyEasy = false;
					difficulty.setText("EASY MODE");
				} else {
					Button.difficultyEasy = true;
					difficulty.setText("HARD MODE");
				}
			}

		});
		btnIndexField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				btnIndexField.selectAll();
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		btnIndexField.selectAll();
		options.add(btnIndexField);
		options.add(hor);
		options.add(vert);
		options.add(xPos);
		options.add(yPos);
		options.add(addButton);
		options.add(removeButton);
		options.add(difficulty);
		options.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		options.setVisible(true);
		frame.setVisible(true);

		addButton(100);
		xPos.setText(buttonObject.get(selectedIndex).tickXPositive ? "X Positive"
				: "X Negative");
		yPos.setText(buttonObject.get(selectedIndex).tickYPositive ? "Y Positive"
				: "Y Negative");
		options.pack();
		(new Thread(new checkWon())).start();
	}

	public static void setSelectedIndex() {
		try {
			selectedIndex = Integer.parseInt(btnIndexField.getText()) - 1;
		} catch (Exception e) {
			selectedIndex = threadIndex - 1;
		}
	}

	@SuppressWarnings("deprecation")
	public static void removeButton() {
		threads.get(selectedIndex).stop();
		frame.remove(((Button) (buttonObject.get(selectedIndex))).btn);
		frame.getContentPane().repaint();

	}

	public static void addButton(int btnSize) {
		buttonObject
				.add(new Button(btnSize, Integer.toString(threadIndex + 1)));
		threads.add(new Thread((Runnable) buttonObject.get(threadIndex)));
		threads.get(threadIndex).start();
		threadIndex++;
	}

	public static void checkWon() {
		hasWon = true;
		for (int i = 0; i <= buttonObject.size() - 1; i++) {
			if (buttonObject.get(i).horizontal || buttonObject.get(i).vertical) {
				hasWon = false;
			}
		}
		if (hasWon) {
			JOptionPane.showMessageDialog(null, "YOU WON!", "You Won!",
					JOptionPane.INFORMATION_MESSAGE);
			for (int i = 0; i <= buttonObject.size() - 1; i++) {
				buttonObject.get(i).setRandomConfig();
			}

		}
	}
}
