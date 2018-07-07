package de.mth.game.gameobject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

import de.mth.game.pathfinding.*;
import de.mth.game.texture.*;

public abstract class GameObject {

	protected double x, y;

	private boolean garbage = false;

	private Texture texture;

	protected double width, height;

	private double speed;

	protected double velX, velY;

	protected double destinationX, destinationY;

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
		this.speed = 1;
		this.velX = 0;
		this.velY = 0;

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

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double( getX(),  getY(),  getWidth(),  getHeight());
	}

	public void drawBounds(Graphics g) {
		Rectangle2D b = getBounds();
		g.setColor(Color.BLUE);
		g.drawRect((int)b.getX(), (int)b.getY(), (int)b.getWidth(), (int)b.getHeight());
	}

	public double[] calculateVelocity(double destX, double destY) {
		double dx = destX - ((double) getX());
		double dy = destY - ((double) getY());

		// Satz des Pythagoras
		double length = (double) Math.sqrt(dx * dx + dy * dy);

		// Aufteilung auf x- und y-Achse
		dx /= length;
		dy /= length;

		// Speed
		dx *= speed;
		dy *= speed;

		double[] vel = new double[2];
		vel[0] = dx;
		vel[1] = dy;
		return vel;
	}

	public double getTop() {
		return getY();
	}

	public double getBottom() {
		return getY() + getHeight();
	}

	public double getLeft() {
		return getX();
	}

	public double getRight() {
		return getX() + getWidth();
	}

	public double[] getVelocity() {
		return calculateVelocity(getDestinationX(), getDestinationY());
	}

	public void setVelocity() {
		double[] velocity = calculateVelocity(getDestinationX(), getDestinationY());
		setVelX(velocity[0]);
		setVelY(velocity[1]);

	}

	public void setVelocity(double[] velocity) {
		setVelX(velocity[0]);
		setVelY(velocity[1]);

	}

	public Rectangle2D getNextStep() {
		return new Rectangle2D.Double((getX() + getVelX()), (getY() + getVelY()), getWidth(), getHeight());
	}

	public boolean isAtDestination() {
		Rectangle dest = new Rectangle((int) getDestinationX() - 1, (int) getDestinationY() - 1, 2, 2);
		Rectangle pos = new Rectangle((int) getX() - 1, (int) getY() - 1, 2, 2);
		return dest.intersects(pos);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
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

	public void setDestination(double x, double y) {
		setDestinationX(x);
		setDestinationY(y);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
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

	public boolean isCollidingWith(GameObject otherObject) {
		return this.getBounds().intersects(otherObject.getBounds());

	}

	public boolean isCollidingAtNextStepWith(GameObject otherObject) {
		return this.getNextStep().intersects(otherObject.getBounds());
	}

	public abstract void defineTextures(TextureLoader textureLoader);

	public abstract void render(Graphics g);

	public abstract void update();

	public abstract void destroy();

	public abstract void resolveCollision(ArrayList<GameObject> gameObject);

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public double getDestinationX() {
		return destinationX;
	}

	public void setDestinationX(double destinationX) {
		this.destinationX = destinationX;
	}

	public double getDestinationY() {
		return destinationY;
	}

	public void setDestinationY(double destinationY) {
		this.destinationY = destinationY;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}
