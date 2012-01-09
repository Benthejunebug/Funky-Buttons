package com.benthejunebug.funkyButton;

public class checkWon implements Runnable {

	private boolean running = false;

	public void run() {
		new Run();
		running = true;
		while (running) {
			Run.checkWon();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
