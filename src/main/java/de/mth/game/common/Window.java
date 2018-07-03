package de.mth.game.common;

import java.awt.*;
import java.io.*;

import javax.swing.*;

public class Window {

	public static int WIDTH = 1024;
	public static int HEIGHT = 800;

	public Window(int w, int h, String title, AbstractGame game) {

		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));

		JFrame frame = new JFrame(title);
		// frame.setUndecorated(true);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		game.start();
	}

	public static void main(String[] args) throws IOException {

		// Writer writer = new Writer();

		new Window(WIDTH, HEIGHT, "Prototype", new Game());

	}

}
