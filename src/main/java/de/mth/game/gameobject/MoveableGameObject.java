package de.mth.game.gameobject;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import de.mth.game.collision.CollisionDirectionDetector;
import de.mth.game.collision.CollisionDirectionDetector.Direction;

public abstract class MoveableGameObject extends GameObject{

	private boolean collidingLeft = false;
	private boolean collidingRight = false;
	private boolean collidingTop = false;
	private boolean collidingBottom = false;
	
	public MoveableGameObject(int x, int y) {
		super(x, y);
	}

	public void correctVelocity() {
		if (isCollidingLeft()) {
			if (getVelX() < 0) {
				setVelX(0);
			}
		}
		if (isCollidingRight()) {
			if (getVelX() > 0) {
				setVelX(0);
			}
		}
		if (isCollidingTop()) {
			if (getVelY() < 0) {
				setVelY(0);
			}
		}
		if (isCollidingBottom()) {
			if (getVelY() > 0)
				setVelY(0);
		}
	}

	public void checkWallsAndCorners(ArrayList<GameObject> gameObjects) {
		if ((isCollidingTop() || isCollidingBottom()) && (isCollidingRight() || isCollidingLeft())) {
			if (getVelX() != 0 && getVelY() != 0) {
				boolean isVerticalWall = isVerticalWall(gameObjects);
				boolean isHorizontalWall = isHorizontalWall(gameObjects);
				if (isHorizontalWall && !(isHorizontalWall && isVerticalWall)) {
					setCollidingRight(false);
					setCollidingLeft(false);
				} else if (isVerticalWall && !(isHorizontalWall && isVerticalWall)) {
					setCollidingTop(false);
					setCollidingBottom(false);
				}
			}
		}
	}

	public boolean isHorizontalWall(ArrayList<GameObject> gameObjects) {
		for (GameObject x : gameObjects) {
			for (GameObject y : gameObjects) {
				if (!x.equals(y)) {
					if (x.getY() == y.getY()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isVerticalWall(ArrayList<GameObject> gameObjects) {
		for (GameObject x : gameObjects) {
			for (GameObject y : gameObjects) {
				if (!x.equals(y)) {
					if (x.getX() == y.getX()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void getCollisionDirections(Rectangle2D nextStep, ArrayList<GameObject> gameObjects) {

		for (GameObject gameObject : gameObjects) {

			Direction direction = CollisionDirectionDetector.getDirection(nextStep, gameObject.getBounds());
			switch (direction) {
			case LEFT:
				setCollidingLeft(true);
				break;
			case RIGHT:
				setCollidingRight(true);
				break;
			case TOP:
				setCollidingTop(true);
				break;
			case BOTTOM:
				setCollidingBottom(true);
				break;
			default:
			}

		}
	}

	public boolean isCollidingLeft() {
		return collidingLeft;
	}

	public void setCollidingLeft(boolean collidingLeft) {
		this.collidingLeft = collidingLeft;
	}

	public boolean isCollidingRight() {
		return collidingRight;
	}

	public void setCollidingRight(boolean collidingRight) {
		this.collidingRight = collidingRight;
	}

	public boolean isCollidingTop() {
		return collidingTop;
	}

	public void setCollidingTop(boolean collidingTop) {
		this.collidingTop = collidingTop;
	}

	public boolean isCollidingBottom() {
		return collidingBottom;
	}

	public void setCollidingBottom(boolean collidingBottom) {
		this.collidingBottom = collidingBottom;
	}

	
	
	
	
	
}
