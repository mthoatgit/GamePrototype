package de.mth.game.gameobject;

import java.awt.Graphics;
import java.awt.Image;

import de.mth.game.texture.TextureLoader;

public class Mountain extends GameObjectBase {

	public Mountain(int x, int y) {
		super(x, y);
		setCollidable(true);
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {

		g.drawImage((Image) getTexture().getSprite(0, 1), (int) x, (int) y, null);
		g.drawImage((Image) getTexture().getSprite(0, 2), (int) x, (int) y, null);
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
	public void setPerceptionRange(int perceptionRange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolveCollision(GameObject gameObject) {
		// TODO Auto-generated method stub

	}

}
