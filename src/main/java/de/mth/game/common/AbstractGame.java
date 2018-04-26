package de.mth.game.common;

import java.awt.Canvas;

public abstract class AbstractGame extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;
	private Thread thread;

	public synchronized void start() {
		if (isRunning()) {
			return;
		}
		setRunning(true);
		thread = new Thread(this);
		thread.start();
	}

	abstract protected void init();

	abstract protected void input();

	abstract protected void update();

	abstract protected void render();

	abstract protected void collectGarbage();

	@Override
	public void run() {
		init();

		long lastTime = System.nanoTime();
		double amountOfUpdates = 60.0;
		double ns = 1000000000 / amountOfUpdates;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (isRunning()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			input();

			collectGarbage();
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " Updates " + updates);
				frames = 0;
				updates = 0;
			}

			this.requestFocus();
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
