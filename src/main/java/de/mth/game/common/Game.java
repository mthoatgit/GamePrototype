package de.mth.game.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import de.mth.game.collision.QuadTree;
import de.mth.game.gameobject.Bullet;
import de.mth.game.gameobject.GameObject;

public class Game extends AbstractGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 1600;
	public static int HEIGHT = 800;

	public static boolean DEBUG = false;

	private GameModel gm;

	private GarbageCollector garbageCollector;

	private Stage stage = Stage.GAME;

	public enum Stage {
		MENU, GAME
	}

	protected void init() {
		gm = new GameModel();

		garbageCollector = new GarbageCollector();

		this.addKeyListener(gm.getInput());
		this.addMouseListener(gm.getInput());
		this.addMouseMotionListener(gm.getInput());

		this.requestFocus();
	}

	protected void input() {

		Input input = gm.getInput();

		Game.DEBUG = input.isToggleD();

		switch (stage) {
		case GAME:
			gm.getPlayer().input(gm);
			break;
		case MENU:
			break;
		default:
			break;

		}

	}

	protected void update() {
		switch (stage) {
		case GAME:
			gm.getCamera().tick(gm.getPlayer());

			ArrayList<GameObject> gameObjects = gm.getGameObjects();

			// //TODO: Trennung von Konfiguration, Collision und Update
			// for (int i = 0; i < gameObjects.size(); i++) {
			// GameObject tempObject = gameObjects.get(i);
			// tempObject.setV;
			//
			// }

			checkCollision();

			for (int i = 0; i < gameObjects.size(); i++) {
				GameObject tempObject = gameObjects.get(i);
				tempObject.update();

			}
			break;
		case MENU:
			break;

		default:
			break;

		}

	}

	protected void render() {
		switch (stage) {

		case GAME:

			BufferStrategy bs = this.getBufferStrategy();
			if (bs == null) {
				this.createBufferStrategy(2);
				return;
			}
			Graphics g = bs.getDrawGraphics();
			// //////////////////
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());

			Graphics2D g2d = (Graphics2D) g;
			Camera cam = gm.getCamera();
			g2d.translate(cam.getX(), cam.getY());

			renderTerrain(g);
			renderObjects(g);

			g.drawRect((int) cam.getBounds().getX(), (int) cam.getBounds().getY(), (int) cam.getBounds().getWidth(), (int) cam.getBounds().getHeight());
			g2d.translate(-cam.getX(), -cam.getY());
			// /////////////
			g.dispose();
			bs.show();
			break;

		case MENU:
			break;

		default:
			break;

		}
	}

	@Override
	protected void collectGarbage() {

		switch (stage) {
		case GAME:
			for (int i = 0; i < gm.getGameObjects().size(); i++) {
				GameObject tempObject = gm.getGameObjects().get(i);
				if (!isVisible(tempObject) && tempObject instanceof Bullet) {
					tempObject.setGarbage(true);
				}

				if (tempObject.isGarbage()) {

					gm.getGameObjects().remove(tempObject);
				}
			}
			break;

		case MENU:
			break;

		default:
			break;

		}
	}

	public void checkCollision() {
		ArrayList<GameObject> collidedObjects = new ArrayList<GameObject>();
		
		ArrayList<GameObject> allObjects = new ArrayList<GameObject>();

		addCollidableGameObjects(allObjects);
		addCollidableTerrain(allObjects);

		QuadTree quad = new QuadTree(0, new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT));

		quad.clear();
		for (int i = 0; i < allObjects.size(); i++) {
			quad.insert(allObjects.get(i));
		}

		List<GameObject> listWithCollidableObjects = new ArrayList<GameObject>();
		for (int i = 0; i < allObjects.size(); i++) {

			// Return all objects that could collide with the given object
			listWithCollidableObjects.clear();
			GameObject checkObject = allObjects.get(i);
			quad.retrieve(listWithCollidableObjects, checkObject);

			for (int j = 0; j < listWithCollidableObjects.size(); j++) {

				// getNextStep
				Rectangle returnBounds = listWithCollidableObjects.get(j).getNextStep();
				Rectangle allObjectsBounds = checkObject.getNextStep();

				if (returnBounds.intersects(allObjectsBounds)) {
					// Same?
					if (!checkObject.equals(listWithCollidableObjects.get(j))) {

						GameObject collidedObject = listWithCollidableObjects.get(j);
						
						collidedObjects.add(collidedObject);
						
						// allObjects.get(i).resolveCollision(returnObjects.get(j));
						checkObject.resolveCollision(listWithCollidableObjects.get(j));
//						listWithCollidableObjects.get(j).resolveCollision(checkObject);

					}
				}
			}
		}
		
		
		

	}

	private void addCollidableTerrain(ArrayList<GameObject> allObjects) {
		GameObject[][] terrain = gm.getTerrain();
		for (int col = 0; col < terrain.length; col++) {
			for (int row = 0; row < terrain[0].length; row++) {
				GameObject gameObject = terrain[col][row];
				if (gameObject.isCollidable()) {
					allObjects.add(gameObject);
				}
			}
		}
	}

	private void addCollidableGameObjects(ArrayList<GameObject> allObjects) {
		for (int i = 0; i < gm.getGameObjects().size(); i++) {
			GameObject gameObject = gm.getGameObjects().get(i);
			if (gm.getGameObjects().get(i).isCollidable()) {
				allObjects.add(gameObject);
			}
		}
	}

	private int renderTerrain(Graphics g) {
		GameObject[][] terrain = gm.getTerrain();

		for (int col = 0; col < terrain.length; col++) {
			for (int row = 0; row < terrain[0].length; row++) {
				GameObject t = terrain[col][row];
				if (t != null) {
					if (isVisible(t)) {
						t.render(g);
					}
					if (Game.DEBUG) {
						if (t.isCollidable()) {
							Rectangle h = t.getBounds();
							g.setColor(Color.BLACK);
							g.drawRect((int) h.getX(), (int) h.getY(), (int) h.getWidth(), (int) h.getHeight());
						}
					}
				}
			}
		}
		return terrain[0].length * terrain.length;

	}

	private int renderObjects(Graphics g) {
		for (int i = 0; i < gm.getGameObjects().size(); i++) {
			GameObject tempObject = gm.getGameObjects().get(i);
			if (isVisible(tempObject)) {
				tempObject.render(g);

				if (Game.DEBUG) {
					if (tempObject.isCollidable()) {
						Rectangle h = tempObject.getBounds();
						g.setColor(Color.BLACK);
						g.drawRect((int) h.getX(), (int) h.getY(), (int) h.getWidth(), (int) h.getHeight());

						g.setColor(Color.RED);
						Rectangle nextStep = tempObject.getNextStep();
						g.drawRect((int) nextStep.getX(), (int) nextStep.getY(), (int) nextStep.getWidth(), (int) nextStep.getHeight());

					}
				}

			}
		}
		return gm.getGameObjects().size();
	}

	public boolean isVisible(GameObject go) {
		Camera cam = gm.getCamera();
		Rectangle r = new Rectangle((int) (-cam.getX()), (int) (-cam.getY()), Main.WIDTH + 32, Main.HEIGHT + 32);
		if (r.intersects(go.getBounds())) {
			return true;
		}
		return false;
	}

	public GarbageCollector getGarbageCollector() {
		return garbageCollector;
	}

	public void setGarbageCollector(GarbageCollector garbageCollector) {
		this.garbageCollector = garbageCollector;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public GameModel getGm() {
		return gm;
	}

	public void setGm(GameModel gm) {
		this.gm = gm;
	}

}
