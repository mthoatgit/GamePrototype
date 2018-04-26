package de.mth.game.common;

import java.io.IOException;

public class Main {

	public static int WIDTH = 1024;
	public static int HEIGHT = 800;

	public static void main(String[] args) throws IOException {

		// Writer writer = new Writer();

		new Window(WIDTH, HEIGHT, "Prototype", new Game());

	}

}
