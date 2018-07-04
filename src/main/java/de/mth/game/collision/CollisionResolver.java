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

	public Map<GameObject, ArrayList<GameObject>> getCollidedObjects(ArrayList<GameObject> allObjects) {

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
			
			// Return all real collisions
			ArrayList<GameObject> collidedObjectsList = getCollidedObjectsList(checkObject, collidableObjectsList);
			
			if (collidedObjectsList.size() > 0) {
				objectsToResolveMap.put(checkObject, collidedObjectsList);
			}
		}
		return objectsToResolveMap;
	}

	public void resolveCollisions(Map<GameObject, ArrayList<GameObject>> objectsToResolveMap) {
		objectsToResolveMap.forEach((k, v) -> k.resolveCollision(v));
	}

	private ArrayList<GameObject> getCollidedObjectsList(GameObject checkObject, List<GameObject> collidableObjectsList) {
		ArrayList<GameObject> collidedObjectsList = new ArrayList<GameObject>();
		for (int j = 0; j < collidableObjectsList.size(); j++) {

			GameObject collidableObject = collidableObjectsList.get(j);

//			// getNextStep
//			Rectangle collidableObjectBounds = collidableObject.getNextStep();
//			Rectangle checkObjectBounds = checkObject.getNextStep();

			// Kollision beim nächsten Step?
			if (collidableObject.isCollidingAtNextStepWith(checkObject)) {

				// Mit sich selbst?
				if (!checkObject.equals(collidableObject)) {
					GameObject collidedObject = collidableObjectsList.get(j);
					collidedObjectsList.add(collidedObject);
				}
			}

		}

		return collidedObjectsList;
	}
	
}
