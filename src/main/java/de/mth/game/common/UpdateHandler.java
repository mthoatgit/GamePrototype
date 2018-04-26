package de.mth.game.common;

import java.util.ArrayList;

import de.mth.game.gameobject.GameObject;

public class UpdateHandler {

	private GameModel gm;

	public UpdateHandler(GameModel gm) {
		this.gm = gm;
	}

	public void update() {

		tick();

		updateObjects();

	}

	private void tick() {
		Camera cam = gm.getCamera();
		cam.tick(gm.getPlayer());
	}

	private void updateObjects() {
		ArrayList<GameObject> gameObjects = gm.getGameObjects();
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject tempObject = gameObjects.get(i);
			tempObject.update();

			broadPhase();
			narrowPhase();

			// checkCollision(tempObject);

		}
	}

	private ArrayList<ArrayList<GameObject>> narrowPhase() {

		ArrayList<ArrayList<GameObject>> possibleCollisions = new ArrayList<ArrayList<GameObject>>();
		ArrayList<GameObject> gameObjects = gm.getGameObjects();
		for (int i = 0; i < gameObjects.size(); i++) {

		}
		return possibleCollisions;
	}

	private void broadPhase() {
		// TODO Auto-generated method stub

	}

	public void checkCollision(GameObject tempObject) {

		if (!tempObject.isCollidable()) {
			return;
		}

	}

}
