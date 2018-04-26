package de.mth.game.gameobject;

import java.awt.Graphics;

import de.mth.game.texture.TextureLoader;

public class Grass extends GameObjectBase {

	public Grass(int x, int y) {
		super(x, y);
		setCollidable(false);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getTexture().getSprite(0, 1), (int) x, (int) y, null);

	}

	@Override
	public void update() {

	}

	@Override
	public void defineTextures(TextureLoader textureLoader) {
		setTexture(textureLoader.getTexTerrain());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPerceptionRange(int perceptionRange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolveCollision(GameObject gameObject) {
		// TODO Auto-generated method stub

	}

}
