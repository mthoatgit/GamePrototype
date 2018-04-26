package de.mth.game.common;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.mth.game.gameobject.Entrance;
import de.mth.game.gameobject.GameObject;
import de.mth.game.gameobject.Grass;
import de.mth.game.gameobject.House;
import de.mth.game.gameobject.Mountain;
import de.mth.game.gameobject.NPC;
import de.mth.game.gameobject.Player;
import de.mth.game.texture.BufferedImageLoader;

public class GameModel {

	private Player player;

	private ArrayList<GameObject> gameObjects;
	private Input input;
	private GameObject[][] terrain;

	private Camera camera;

	public GameModel() {
		gameObjects = new ArrayList<GameObject>();
		camera = new Camera();
		init();
	}

	public void init() {
		input = Input.getInstance();

		loadGame();

	}

	public Player getPlayer() {

		if (player == null) {
			for (int i = 0; i < gameObjects.size(); i++) {
				GameObject gameObject = gameObjects.get(i);
				if (gameObject instanceof Player) {
					player = (Player) gameObject;
				}
			}
		}
		return player;
	}

	public Camera getCamera() {
		return camera;
	}

	// public static <T> Collection<T> filter(Collection<T> col,
	// Predicate<T> predicate) {
	// Collection<T> result = new ArrayList<T>();
	// for (T element : col) {
	// if (predicate.apply(element)) {
	// result.add(element);
	// }
	// }
	// return result;
	// }

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public ArrayList<Rectangle> getBounds() {
		ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();
		for (int i = 0; i < getGameObjects().size(); i++) {
			bounds.add(getGameObjects().get(i).getBounds());

		}
		return bounds;
	}

	private void loadGame() {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage level = loader.loadImage("/level_terrain.png");
		BufferedImage objects = loader.loadImage("/level_objects.png");

		int w = 200;
		int h = 200;

		// int w = level.getWidth();
		// int h = level.getHeight();

		loadTerrain(w, h, level);
		loadObjects(w, h, objects);

	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	/*
	 * Es muss ein Array sein aus Performance-Gründen. Bitte mach den Fehler
	 * nicht schon wieder! :)
	 */
	private void loadTerrain(int w, int h, BufferedImage level) {
		int count = 0;

		terrain = new GameObject[w][h];
		// TextureLoader textureLoader = TextureLoader.getInstance();
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = level.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 & blue == 0) { // Schwarz
					Grass t = new Grass(xx * 32, yy * 32);
					terrain[xx][yy] = t;
					// gameObjects.add(t);
					count++;
				}
				if (red == 255 && green == 255 & blue == 255) { // Weiß
					Mountain m = new Mountain(xx * 32, yy * 32);
					terrain[xx][yy] = m;
					// gameObjects.add(m);

					// System.out.println(m.getBounds().getX() + " " +
					// m.getBounds().getY());

					count++;
				}
			}
		}
		System.out.println("GameModel.loadTerrain() " + terrain.length + " " + terrain[0].length);
		System.out.println("GameModel.loadTerrain() " + count);

	}

	private void loadObjects(int w, int h, BufferedImage objects) {
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {

				int pixel = objects.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 & blue == 255) { // Blau
					createNPC(xx * 32, yy * 32);

				}
				if (red == 255 && green == 255 & blue == 0) { // Grün
					createNPC(xx * 32, yy * 32);
				}
				if (red == 255 && green == 0 & blue == 0) { // Rot
					// createPlayer(xx * 32, yy * 32);
					Player p = new Player(xx * 32, yy * 32);
					gameObjects.add(p);

				}
				if (red == 0 && green == 255 & blue == 0) { // Grün
					createHouse(xx * 32, yy * 32);
				}

			}
		}

	}

	private void createHouse(int x, int y) {
		House house = new House(x, y);
		gameObjects.add(house);
		gameObjects.add(new Entrance(x + 20, y + 10));
	}

	private void createPlayer(int x, int y) {
		Player p = new Player(x, y);
		gameObjects.add(p);
	}

	private void createNPC(int x, int y) {
		NPC npc = new NPC(x, y);
		gameObjects.add(npc);

	}

	public GameObject[][] getTerrain() {
		return terrain;
	}

}
