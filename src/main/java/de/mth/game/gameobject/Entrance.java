package de.mth.game.gameobject;

import java.awt.Graphics;
import java.util.ArrayList;

import de.mth.game.texture.TextureLoader;

public class Entrance extends GameObject {

	public Entrance(int x, int y) {
		super(x, y);
		setWidth(32);
		setHeight(20);
	}

	@Override
	public void update() {
	}

	@Override
	public void defineTextures(TextureLoader textureLoader) {

	}

	@Override
	public void render(Graphics g) {

	}

	@Override
	public void destroy() {
	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resolveCollision(ArrayList<GameObject> gameObject) {
		// TODO Auto-generated method stub
		
	}

	

}
