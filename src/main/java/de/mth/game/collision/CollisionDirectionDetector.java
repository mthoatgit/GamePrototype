package de.mth.game.collision;

import java.awt.geom.Rectangle2D;
import java.util.*;

import de.mth.game.gameobject.*;

public class CollisionDirectionDetector {

	public enum Direction {
		LEFT, RIGHT, TOP, BOTTOM
	}
	
	public static Direction getDirection(Rectangle2D nextStep, Rectangle2D other) {

		Double inf = Double.MAX_VALUE;
		Double[] array = new Double[] { inf, inf, inf, inf };
		
		double left = nextStep.getX();
		double right = nextStep.getX() + nextStep.getWidth();
		double top = nextStep.getY();
		double bottom = nextStep.getY() + nextStep.getHeight();
		
		double otherLeft = other.getX();
		double otherRight = other.getX() + nextStep.getWidth();
		double otherTop = other.getY();
		double otherBottom = other.getY() + nextStep.getHeight();
		
		if (left < otherRight && right > otherRight) {
			// Player is right Other is left -> Collision Player on left Side
			double leftCollision = Math.abs(left - otherRight);
			array[0] = leftCollision;
		}
		
		if (right > otherLeft && left < otherLeft) {
			// Player is left Other is Right -> Collision Player on Right Side
			double rightCollision = Math.abs(right - otherLeft);
			array[1] = rightCollision;
		}
		
		if (top < otherBottom && bottom > otherBottom) {
			// // Player is bottom Other is top -> Collision Player on top Side
			double bottomCollision = otherBottom - top;
			array[2] = bottomCollision;
		}

		if (bottom > otherTop && top < otherTop) {
			// Player is top Other is bottom -> Collision Player on bottom Side
			double topCollision = bottom - otherTop;
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
	
	public static Map<GameObject, Direction> getDirections(Rectangle2D nextStep, ArrayList<GameObject> gameObjectList){
		Map<GameObject, Direction> map = new HashMap<>();
		for(GameObject other : gameObjectList) {
			map.put(other, getDirection(nextStep, other.getBounds()));
		}
		return map;
	}
}
