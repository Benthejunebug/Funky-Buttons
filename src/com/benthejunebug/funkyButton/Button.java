package com.benthejunebug.funkyButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.*;

class Button implements Runnable {
	private  int tickX;
	private int tickY;
	private  int wHTick;
	public JButton btn;

	private boolean running = false;
	public JCheckBox hor;
	public JCheckBox vert;
	public JButton xPos;
	public  JButton yPos;
	public JFrame options;
	private boolean wHTickSub;
	private boolean stopTickX;
	private boolean stopTickY;
	public  boolean tickYPositive = new Random().nextBoolean();
	public  boolean tickXPositive = new Random().nextBoolean();
	public  boolean horizontal = new Random().nextBoolean();
	public boolean vertical = new Random().nextBoolean();
	public int btnHeight;
	public  int btnWidth;
	public static int btnSize = 100;

public Button(){
		
		new Run();
		options = new JFrame("Options");
		options.setAlwaysOnTop(true);
		options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		options.setLayout(new FlowLayout());
		hor = new JCheckBox("Horizontal");
		vert = new JCheckBox("Vertical");
		xPos = new JButton(tickXPositive ? "X Positive" : "X Negative");
		yPos = new JButton(tickYPositive ? "Y Positive" : "Y Negative");
		hor.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (hor.isSelected()) {
					horizontal = true;
				} else {
					horizontal = false;
				}
			}

		});
		vert.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (vert.isSelected()) {
					vertical = true;
				} else {
					vertical = false;
				}
			}

		});
		xPos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tickXPositive) {
					tickXPositive = false;
					xPos.setText("X Positive");

				} else {
				tickXPositive = true;
				xPos.setText("X Negative");
				}
				options.pack();
			}

		});
		yPos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tickYPositive) {
					tickYPositive = false;
					yPos.setText("Y Positive");

				} else {
					tickYPositive = true;
					yPos.setText("Y Negative");
				}
				options.pack();
			}

		});
		
		options.add(hor);
		options.add(vert);
		options.add(xPos);
		options.add(yPos);
		options.pack();
		options.setVisible(true);
}

	@Override
	public void run() {
		btn = new JButton("Button");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (horizontal && vertical) {
					horizontal = false;
				} else if (!horizontal && vertical) {
					horizontal = true;
					vertical = false;
				} else if (horizontal && !vertical) {
					horizontal = false;
				} else if (!horizontal && !vertical) {
					horizontal = true;
					vertical = true;

				}

			}

		});
		btn.setBounds(0, 0, btnSize, btnSize);
		Run.frame.getContentPane().add(btn);
		running = true;
		tickX = 0;
		tickY = 0;
		wHTick = 50;
		while (running) {
			if (tickXPositive) {
				if (tickX > Run.frame.getWidth()) {
					tickX = -btnSize;
				}
			} else {
				if (tickX < -btnSize) {
					tickX = Run.frame.getWidth() + btnSize;
				}
			}
			if (tickYPositive) {
				if (tickY > Run.frame.getHeight()) {
					tickY = -btnSize;
				}
			} else {
				if (tickY < -btnSize) {
					tickY = Run.frame.getHeight() + btnSize;
				}
			}
			if (horizontal && vertical) {
				hor.setSelected(true);
				vert.setSelected(true);
				stopTickX = false;
				stopTickY = false;
				btnWidth = wHTick;
				btnHeight = wHTick;
			} else if (!horizontal && !vertical) {
				hor.setSelected(false);
				vert.setSelected(false);
				stopTickX = true;
				stopTickY = true;
				btnWidth = btnSize;
				btnHeight = btnSize;
			} else if (!horizontal && vertical) {
				hor.setSelected(false);
				vert.setSelected(true);

				stopTickX = true;
				stopTickY = false;
				btnWidth = btnSize;
				btnHeight = wHTick;
			} else if (horizontal && !vertical) {
				hor.setSelected(true);
				vert.setSelected(false);
				stopTickX = false;
				stopTickY = true;
				btnWidth = wHTick;
				btnHeight = btnSize;
			}

			btn.setBounds(tickX, tickY, btnWidth, btnHeight);

			Run.frame.getContentPane().repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!stopTickX) {
				if (tickXPositive) {
					tickX++;
				} else {
					tickX--;
				}
			}
			if (!stopTickY) {
				if (tickYPositive) {
					tickY++;
				} else {
					tickY--;
				}
			}

			if (wHTick > 100) {
				wHTickSub = true;
			} else if (wHTick < 50) {
				wHTickSub = false;
			}
			if (wHTickSub) {
				wHTick--;
			} else {
				wHTick++;
			}

		}
	}
}