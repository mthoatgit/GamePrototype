package de.mth.game.collision;

import java.awt.*;
import java.util.*;
import java.util.List;

import de.mth.game.common.*;
import de.mth.game.gameobject.*;

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

	public ArrayList<GameObject> getAllCollidableGameObjects() {
		ArrayList<GameObject> allCollidableGameObjects = new ArrayList<>();
		allCollidableGameObjects.addAll(getCollidableTerrain());
		allCollidableGameObjects.addAll(getCollidableGameObjects());
		return allCollidableGameObjects;
	}

	public void checkCollision(ArrayList<GameObject> allObjects) {

		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = new HashMap<>();

		QuadTree quad = new QuadTree(0, new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT));
		quad.clear();

		for (int i = 0; i < allObjects.size(); i++) {
			quad.insert(allObjects.get(i));
		}

		List<GameObject> collidableObjectsList = new ArrayList<GameObject>();
		for (int i = 0; i < allObjects.size(); i++) {

			collidableObjectsList.clear();
			GameObject checkObject = allObjects.get(i);

			// Return all objects that could collide with the given object
			collidableObjectsList = quad.retrieve(collidableObjectsList, checkObject);
			ArrayList<GameObject> collidedObjectsList = getCollidedObjectsList(checkObject, collidableObjectsList);

			if (collidedObjectsList.size() > 0) {

				objectsToResolveMap.put(checkObject, collidedObjectsList);
			}

			// resolveCollisions(objectsToResolveMap);

			if (collidedObjectsList.size() != 0) {

				checkObject.resolveCollision(collidedObjectsList);

			}
			if (checkObject instanceof Player) {
				System.out.println("CollisionResolver.checkCollision()");
			}
		}
	}

	private void resolveCollisions(Map<GameObject, ArrayList<GameObject>> objectsToResolveMap) {
		objectsToResolveMap.forEach((k, v) -> k.resolveCollision(v));
	}

	private ArrayList<GameObject> getCollidedObjectsList(GameObject checkObject, List<GameObject> collidableObjectsList) {
		ArrayList<GameObject> collidedObjectsList = new ArrayList<GameObject>();
		for (int j = 0; j < collidableObjectsList.size(); j++) {

			GameObject collidableObject = collidableObjectsList.get(j);

			// getNextStep
			Rectangle collidableObjectBounds = collidableObject.getNextStep();
			Rectangle checkObjectBounds = checkObject.getNextStep();

			// Kollision?
			if (collidableObjectBounds.intersects(checkObjectBounds)) {

				// Mit sich selbst?
				if (!checkObject.equals(collidableObject)) {

					// if (checkObject instanceof NPC) {
					// System.out.println("CollisionResolver.checkCollision()
					// NPC 1");
					// if (collidableObject instanceof Mountain) {
					//
					// System.out.println("CollisionResolver.checkCollision()
					// NPC 2");
					// }
					// }

					GameObject collidedObject = collidableObjectsList.get(j);

					collidedObjectsList.add(collidedObject);

				}
			}

		}

		return collidedObjectsList;
	}

	private ArrayList<GameObject> getCollidableTerrain() {
		GameObject[][] terrain = gameModel.getTerrain();
		ArrayList<GameObject> collidableTerrain = new ArrayList<>();
		for (int col = 0; col < terrain.length; col++) {
			for (int row = 0; row < terrain[0].length; row++) {
				GameObject gameObject = terrain[col][row];
				if (gameObject.isCollidable()) {
					collidableTerrain.add(gameObject);
				}
			}
		}
		return collidableTerrain;
	}

	private ArrayList<GameObject> getCollidableGameObjects() {
		ArrayList<GameObject> collidableGameObjects = new ArrayList<>();
		for (int i = 0; i < gameModel.getGameObjects().size(); i++) {
			GameObject gameObject = gameModel.getGameObjects().get(i);
			if (gameModel.getGameObjects().get(i).isCollidable()) {
				collidableGameObjects.add(gameObject);
			}
		}
		return collidableGameObjects;
	}

}
