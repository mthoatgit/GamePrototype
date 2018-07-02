package de.mth.game.gameobject;

import java.awt.Graphics;
import java.util.ArrayList;

import de.mth.game.texture.TextureLoader;

public class Bullet extends GameObject {

	public Bullet(int x, int y, float destX, float destY) {
		super(x, y);
		setSpeed(3);

		float[] vel = getVelocity(destX, destY);

		setVelX(vel[0]);
		setVelY(vel[1]);

		setCollidable(true);
	}

	@Override
	public void update() {
		x += getVelX();
		y += getVelY();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getTexture().getSprite(0, 0), (int) x, (int) y, null);
	}

	@Override
	public void defineTextures(TextureLoader textureLoader) {

		setTexture(textureLoader.getTexPlayer());
	}

	@Override
	public void destroy() {
		setGarbage(true);
	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public void setPerceptionRange(int perceptionRange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolveCollision(ArrayList<GameObject> gameObject) {
		// TODO Auto-generated method stub
		
	}

	

}
