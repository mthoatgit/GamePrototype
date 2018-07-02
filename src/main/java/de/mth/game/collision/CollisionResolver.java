package de.mth.game.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.mth.game.common.Game;
import de.mth.game.common.GameModel;
import de.mth.game.gameobject.GameObject;
import de.mth.game.gameobject.Player;

public class CollisionResolver {

	private GameModel gameModel;

	public CollisionResolver(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
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

			listWithCollidableObjects.clear();
			GameObject checkObject = allObjects.get(i);
			
			// Return all objects that could collide with the given object
			listWithCollidableObjects = quad.retrieve(listWithCollidableObjects, checkObject);
			
			for (int j = 0; j < listWithCollidableObjects.size(); j++) {

				GameObject collidableObject = listWithCollidableObjects.get(j);

				// getNextStep
				Rectangle collidableObjectBounds = collidableObject.getNextStep();
				Rectangle checkObjectBounds = checkObject.getNextStep();

				// Kollision?
				if (collidableObjectBounds.intersects(checkObjectBounds)) {
					
					// Mit sich selbst?
					if (!checkObject.equals(collidableObject)) {

						GameObject collidedObject = listWithCollidableObjects.get(j);

							collidedObjects.add(collidedObject);
					}
				}
			}
			checkObject.resolveCollision(collidedObjects);
		}
	}

	private void addCollidableTerrain(ArrayList<GameObject> allObjects) {
		GameObject[][] terrain = gameModel.getTerrain();
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
		for (int i = 0; i < gameModel.getGameObjects().size(); i++) {
			GameObject gameObject = gameModel.getGameObjects().get(i);
			if (gameModel.getGameObjects().get(i).isCollidable()) {
				allObjects.add(gameObject);
			}
		}
	}

}
