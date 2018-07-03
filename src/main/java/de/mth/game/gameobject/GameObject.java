package de.mth.game.gameobject;

import java.awt.*;
import java.util.*;

import de.mth.game.pathfinding.*;
import de.mth.game.texture.*;

public abstract class GameObject {

	protected float x, y;

	private boolean garbage = false;

	private Texture texture;

	protected float width, height;

	private double speed;

	public Path path;
	public int pathCounter;

	protected float velX, velY;

	protected float destinationX, destinationY;

	protected boolean colliding = false;
	protected boolean collidingAtNextStep = false;

	public enum Movement {
		UP, DOWN, LEFT, RIGHT;
	}

	public Movement movement = Movement.DOWN;

	private boolean collidable;

	protected GameObject collidedObject;

	public GameObject(int x, int y) {
		setPosition(x, y);
		setDestination(x, y);
		this.path = null;
		this.speed = 1;
		this.velX = 0;
		this.velY = 0;
		this.pathCounter = 0;

		setCollidable(false);
		setGarbage(false);

		// Texture
		defineTextures(TextureLoader.getInstance());
		if (getTexture() != null) {
			this.width = getTexture().getSprite(0, 0).getWidth();
			this.height = getTexture().getSprite(0, 0).getHeight();
		}

	}

	private void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	public boolean equals(Rectangle rect) {

		if (getBounds().getX() == rect.getX() && getBounds().getY() == rect.getY()) {
			return true;

		}
		return false;
	}

	public void drawBounds(Graphics g) {
		Rectangle b = getBounds();
		g.setColor(Color.BLUE);
		g.drawRect(b.x, b.y, b.width, b.height);
	}

	public float[] getVelocity(float destX, float destY) {
		float dx = destX - ((float) getX());
		float dy = destY - ((float) getY());

		// Satz des Pythagoras
		float length = (float) Math.sqrt(dx * dx + dy * dy);

		// Aufteilung auf x- und y-Achse
		dx /= length;
		dy /= length;

		// Speed
		dx *= speed;
		dy *= speed;

		float[] vel = new float[2];
		vel[0] = dx;
		vel[1] = dy;
		return vel;
	}

	public float getBottom() {
		return ((float) getBounds().getY()) + getHeight();
	}

	public float getRight() {
		return ((float) getBounds().getX()) + getWidth();
	}

	public double getLeftCollision(GameObject collidingObject) {
		return getRight() - collidingObject.getX();
	}

	public double getRightCollision(GameObject collidingObject) {
		return collidingObject.getRight() - getBounds().getX();
	}

	public double getTopCollision(GameObject collidingObject) {
		return getBottom() - collidingObject.getY();
	}

	public double getBottomCollision(GameObject collidingObject) {
		return collidingObject.getBottom() - getBounds().getY();
	}

	public boolean isCollidingBottom(GameObject collidingObject) {
		double t = getTopCollision(collidingObject);
		double b = getBottomCollision(collidingObject);
		double l = getLeftCollision(collidingObject);
		double r = getRightCollision(collidingObject);

		return t < b && t < l && t < r;
	}

	public boolean isCollidingTop(GameObject collidingObject) {
		double t = getTopCollision(collidingObject);
		double b = getBottomCollision(collidingObject);
		double l = getLeftCollision(collidingObject);
		double r = getRightCollision(collidingObject);

		return b < t && b < l && b < r;
	}

	public boolean isCollidingRight(GameObject collidingObject) {
		double t = getTopCollision(collidingObject);
		double b = getBottomCollision(collidingObject);
		double l = getLeftCollision(collidingObject);
		double r = getRightCollision(collidingObject);

		return r < l && r < t && r < b;
	}

	public boolean isCollidingLeft(GameObject collidingObject) {
		double t = getTopCollision(collidingObject);
		double b = getBottomCollision(collidingObject);
		double l = getLeftCollision(collidingObject);
		double r = getRightCollision(collidingObject);

		return l < r && l < t && l < b;
	}

	public float[] getVelocity() {
		return getVelocity(getDestinationX(), getDestinationY());
	}

	public void setVelocity(float[] velocity) {
		setVelX(velocity[0]);
		setVelY(velocity[1]);

	}

	public Rectangle getNextStep() {
		float[] velocity = getVelocity();
		return new Rectangle((int) (getX() + (int) velocity[0]), (int) (getY() + (int) velocity[1]), (int) getWidth(), (int) getHeight());
	}

	public boolean isAtDestination() {
		Rectangle dest = new Rectangle((int) getDestinationX() - 1, (int) getDestinationY() - 1, 2, 2);
		Rectangle pos = new Rectangle((int) getX() - 1, (int) getY() - 1, 2, 2);
		return dest.intersects(pos);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		if (this instanceof Player) {
			System.out.println("GameObject.setWidth()");
		}
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public boolean isGarbage() {
		return garbage;
	}

	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}

	public float getDestinationX() {
		return destinationX;
	}

	public void setDestinationX(float destinationX) {
		this.destinationX = destinationX;
	}

	public float getDestinationY() {
		return destinationY;
	}

	public void setDestinationY(float destinationY) {
		this.destinationY = destinationY;
	}

	public void setDestination(float x, float y) {
		setDestinationX(x);
		setDestinationY(y);
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
		setDestination(path.getX(pathCounter), path.getY(pathCounter));
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public boolean isCollidable() {
		return collidable;

	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	public boolean isColliding() {
		return colliding;
	}

	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}

	public GameObject getCollidedObject() {
		return collidedObject;
	}

	public void setCollidedObject(GameObject gameObject) {
		this.collidedObject = gameObject;

	}

	public boolean isCollidingAtNextStep() {
		return collidingAtNextStep;
	}

	public void setCollidingAtNextStep(boolean collidingAtNextStep) {
		this.collidingAtNextStep = collidingAtNextStep;
	}

	public abstract void defineTextures(TextureLoader textureLoader);

	public abstract void render(Graphics g);

	public abstract void update();

	public abstract void destroy();

	public abstract void resolveCollision(ArrayList<GameObject> gameObject);

	public abstract void setPerceptionRange(int perceptionRange);

}
