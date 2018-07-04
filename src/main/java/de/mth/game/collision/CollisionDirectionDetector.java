package de.mth.game.collision;

import java.util.*;

import de.mth.game.gameobject.*;

public class CollisionDirectionDetector {

	// public double getLeftCollision(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// return checkGameObject.getLeft() - otherGameObject.getRight();
	// }
	//
	// public double getRightCollision(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// return checkGameObject.getRight() - otherGameObject.getLeft();
	// }
	//
	// public double getTopCollision(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// return checkGameObject.getTop() - otherGameObject.getBottom();
	// }
	//
	// public double getBottomCollision(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// return checkGameObject.getBottom() - otherGameObject.getTop();
	// }

	public enum Direction {
		LEFT, RIGHT, TOP, BOTTOM
	}
	
	public Direction getDirection(GameObject check, GameObject other) {

		Double inf = Double.MAX_VALUE;
		Double[] array = new Double[] { inf, inf, inf, inf };

		if (check.getRight() > other.getLeft() && check.getLeft() < other.getLeft()) {
			// Player is left Other is Right -> Collision Player on Right Side
			double leftCollision = Math.abs(check.getRight() - other.getLeft());
			array[0] = leftCollision;
		}

		if (check.getLeft() < other.getRight() && check.getRight() > other.getRight()) {
			// Player is right Other is left -> Collision Player on left Side
			double rightCollision = Math.abs(check.getLeft() - other.getRight());
			array[1] = rightCollision;
		}
		if (check.getTop() < other.getBottom() && check.getBottom() > other.getBottom()) {
			// // Player is bottom Other is top -> Collision Player on top Side
			double bottomCollision = other.getBottom() - check.getTop();
			array[2] = bottomCollision;
		}

		if (check.getBottom() > other.getTop() && check.getTop() < other.getTop()) {
			// Player is top Other is bottom -> Collision Player on bottom Side
			double topCollision = check.getBottom() - other.getTop();
			array[3] = topCollision;
		}

		List<Double> asList = Arrays.asList(array);
		int indexOf = asList.indexOf(Collections.min(asList));
		
		 switch(indexOf){ 
	        case 0: 
	            return Direction.LEFT;
	        case 1: 
	        	return Direction.RIGHT;
	        case 2: 
	        	return Direction.TOP;
	        case 3: 
	        	return Direction.BOTTOM;
	        default: 
	        	System.out.println("CollisionDirectionDetector.getDirection()");
	        } 
		 return null;
	}

//	public boolean isCollidingRight(GameObject checkGameObject, GameObject otherGameObject) {
//		int direction = getDirection(checkGameObject, otherGameObject);
//		if(direction == 0) {
//			return true;
//		}
//		return false;
//	}
//
//	public boolean isCollidingLeft(GameObject checkGameObject, GameObject otherGameObject) {
//		int direction = getDirection(checkGameObject, otherGameObject);
//		if(direction == 1) {
//			return true;
//		}
//		return false;
//	}
//
//	public boolean isCollidingTop(GameObject checkGameObject, GameObject otherGameObject) {
//		int direction = getDirection(checkGameObject, otherGameObject);
//		if(direction == 2) {
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean isCollidingBottom(GameObject checkGameObject, GameObject otherGameObject) {
//		int direction = getDirection(checkGameObject, otherGameObject);
//		if(direction == 3) {
//			return true;
//		}
//		return false;
//	}

	

	// public boolean isCollidingBottom(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// double t = getTopCollision(checkGameObject, otherGameObject);
	// double b = getBottomCollision(checkGameObject, otherGameObject);
	// double l = getLeftCollision(checkGameObject, otherGameObject);
	// double r = getRightCollision(checkGameObject, otherGameObject);
	//
	// return t < b && t < l && t < r;
	// }
	//
	// public boolean isCollidingTop(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// double t = getTopCollision(checkGameObject, otherGameObject);
	// double b = getBottomCollision(checkGameObject, otherGameObject);
	// double l = getLeftCollision(checkGameObject, otherGameObject);
	// double r = getRightCollision(checkGameObject, otherGameObject);
	//
	// return b < t && b < l && b < r;
	// }
	//
	// public boolean isCollidingRight(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// double t = getTopCollision(checkGameObject, otherGameObject);
	// double b = getBottomCollision(checkGameObject, otherGameObject);
	// double l = getLeftCollision(checkGameObject, otherGameObject);
	// double r = getRightCollision(checkGameObject, otherGameObject);
	//
	// return r < l && r < t && r < b;
	// }
	//
	// public boolean isCollidingLeft(GameObject checkGameObject, GameObject
	// otherGameObject) {
	// double t = getTopCollision(checkGameObject, otherGameObject);
	// double b = getBottomCollision(checkGameObject, otherGameObject);
	// double l = getLeftCollision(checkGameObject, otherGameObject);
	// double r = getRightCollision(checkGameObject, otherGameObject);
	//
	// return l < r && l < t && l < b;
	// }
}
