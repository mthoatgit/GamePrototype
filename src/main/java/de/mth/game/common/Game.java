package de.mth.game.common;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

import de.mth.game.collision.*;
import de.mth.game.gameobject.*;

public class Game extends AbstractGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 1600;
	public static int HEIGHT = 800;

	public static boolean DEBUG = false;

	private GameModel gm;

	private CollisionResolver collisionResolver; 
	
	private GarbageCollector garbageCollector;

	private Stage stage = Stage.GAME;

	public enum Stage {
		MENU, GAME
	}

	protected void init() {
		gm = new GameModel();

		collisionResolver = new CollisionResolver(gm);
		
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

			for(GameObject gameObject : gameObjects) {
				gameObject.setVelocity();
			}
			
			ArrayList<GameObject> allCollidableGameObjects = gm.getAllCollidableGameObjects();
			Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allCollidableGameObjects);
			collisionResolver.resolveCollisions(objectsToResolveMap);

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

						Rectangle nextStep = tempObject.getNextStep();
//						g.setColor(Color.RED);
//						g.drawRect((int) nextStep.getX(), (int) nextStep.getY(), (int) nextStep.getWidth(), (int) nextStep.getHeight());

					}
				}

			}
		}
		return gm.getGameObjects().size();
	}

	public boolean isVisible(GameObject go) {
		Camera cam = gm.getCamera();
		Rectangle r = new Rectangle((int) (-cam.getX()), (int) (-cam.getY()), Window.WIDTH + 32, Window.HEIGHT + 32);
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
