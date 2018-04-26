package de.mth.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.mth.game.texture.TextureLoader;

public interface GameObject {

	public float getX();

	public float getY();

	public float getWidth();

	public float getHeight();

	public Rectangle getBounds();

	public void defineTextures(TextureLoader textureLoader);

	public void render(Graphics g);

	public void update();

	public boolean isGarbage();

	public void setGarbage(boolean garbage);

	public void destroy();

	public boolean isCollidable();

	public void setCollidable(boolean collidable);

	public boolean isColliding();

	public void setColliding(boolean colliding);

	public boolean isCollidingAtNextStep();

	public void setCollidingAtNextStep(boolean collidingAtNextStep);

	public GameObject getCollidedObject();

	public void setCollidedObject(GameObject gameObject);

	public void resolveCollision(GameObject gameObject);

	public boolean equals(Rectangle rect);

	public float[] getVelocity();

	public Rectangle getNextStep();

	public void setPerceptionRange(int perceptionRange);

	public float getBottom();

	public float getRight();

}
