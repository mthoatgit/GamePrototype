package de.mth.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import de.mth.game.texture.Part;
import de.mth.game.texture.TextureLoader;

public class House extends GameObject {

	ArrayList<Part> parts;

	private Entrance entrance;

	public House(int x, int y) {
		super(x, y);
	}

	public Entrance getEntrance() {
		return entrance;
	}

	public void setEntrance(Entrance entrance) {
		this.entrance = entrance;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < parts.size(); i++) {
			g.drawImage(parts.get(i).getSprite(), parts.get(i).getX(), parts.get(i).getY(), null);
		}

	}

	@Override
	public Rectangle getBounds() {

		int width = 0;
		int height = 0;

		int x = 0;
		int y = 0;

		for (int i = 0; i < parts.size(); i++) {
			Part part = parts.get(i);
			if (part.getX() > x) {
				x = part.getX();
			}
			if (part.getY() > y) {
				y = part.getY();
			}
		}

		x = x - (int) getX();
		y = y - (int) getY();
		width = x + 32;
		height = y + 32;

		return new Rectangle((int) getX(), (int) getY(), width, height);
	}

	public ArrayList<Rectangle> getBuildBounds() {
		ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();
		for (int i = 0; i < parts.size(); i++) {
			Part t = parts.get(i);
			t.getBounds();
		}

		return bounds;
	}

	@Override
	public void defineTextures(TextureLoader textureLoader) {
		setTexture(textureLoader.getTexTerrain());

		int build[][] = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
		parts = new ArrayList<Part>();

		for (int row = 0; row < build.length; row++) {
			for (int col = 0; col < build[0].length; col++) {
				int x = (int) getX() + (col * 32);
				int y = (int) getY() + (row * 32);
				parts.add(new Part(x, y, getTexture().getSprite(col, row + 3)));
			}
		}

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
	public void setPerceptionRange(int perceptionRange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolveCollision(ArrayList<GameObject> gameObject) {
		// TODO Auto-generated method stub
		
	}

}
