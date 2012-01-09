package com.benthejunebug.funkyButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

class Button implements Runnable {
	private int tickX;
	private int tickY;
	private int wHTick;
	public JButton btn;

	private boolean running = false;

	private boolean wHTickSub;
	private boolean stopTickX;
	private boolean stopTickY;
	public boolean tickYPositive;
	public boolean tickXPositive;
	public boolean horizontal;
	public boolean vertical;
	public int btnHeight;
	public int btnWidth;
	public int btnSize = 100;
	public String name;
	public static boolean difficultyEasy = true;

	public Button(int sizeOfButton, String btnName) {
		name = btnName;
		btnSize = sizeOfButton;
		setRandomConfig();
	}

	public Button() {
		name = "button";
		btnSize = 100;
		setRandomConfig();
	}

	public void setRandomConfig() {
		tickYPositive = new Random().nextBoolean();
		tickXPositive = new Random().nextBoolean();
		horizontal = new Random().nextBoolean();
		vertical = new Random().nextBoolean();
	}

	public void yPositiveAction() {
		if (tickYPositive) {
			tickYPositive = false;
			Run.yPos.setText("Y Positive");

		} else {
			tickYPositive = true;
			Run.yPos.setText("Y Negative");
		}
		Run.options.pack();
	}

	public void xPositiveAction() {
		if (tickXPositive) {
			tickXPositive = false;
			Run.xPos.setText("X Positive");

		} else {
			tickXPositive = true;
			Run.xPos.setText("X Negative");
		}
		Run.options.pack();
	}

	public void vertAction() {
		if (Run.vert.isSelected()) {
			vertical = true;
		} else {
			vertical = false;
		}
	}

	public void horAction() {
		if (Run.hor.isSelected()) {
			horizontal = true;
		} else {
			horizontal = false;
		}
	}

	public String xPos() {
		if (tickXPositive) {
			return "X Poxitive";
		} else {
			return "X Negative";
		}

	}

	public String yPos() {
		if (tickYPositive) {
			return "Y Poxitive";
		} else {
			return "Y Negative";
		}

	}

	@Override
	public void run() {
		btn = new JButton(name);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (difficultyEasy) {
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
				} else {
					setRandomConfig();
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
				Run.hor.setSelected(true);
				Run.vert.setSelected(true);
				stopTickX = false;
				stopTickY = false;
				btnWidth = wHTick;
				btnHeight = wHTick;
			} else if (!horizontal && !vertical) {
				Run.hor.setSelected(false);
				Run.vert.setSelected(false);
				stopTickX = true;
				stopTickY = true;
				btnWidth = btnSize;
				btnHeight = btnSize;
			} else if (!horizontal && vertical) {
				Run.hor.setSelected(false);
				Run.vert.setSelected(true);
				stopTickX = true;
				stopTickY = false;
				btnWidth = btnSize;
				btnHeight = wHTick;
			} else if (horizontal && !vertical) {
				Run.hor.setSelected(true);
				Run.vert.setSelected(false);
				stopTickX = false;
				stopTickY = true;
				btnWidth = wHTick;
				btnHeight = btnSize;
			}

			btn.setBounds(tickX, tickY, btnWidth, btnHeight);

			Run.frame.getContentPane().repaint();
			try {
				Thread.sleep(5);
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

			if (wHTick > btnSize) {
				wHTickSub = true;
			} else if (wHTick < btnSize / 2) {
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