package de.mth.game.common;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.mth.game.gameobject.GameObject;

public class RenderHandler {

	private GameModel gm;

	public RenderHandler(GameModel gm) {
		this.gm = gm;
	}

	protected void render(Graphics g) {

		renderTerrain(g);
		renderObjects(g);

	}

	private int renderTerrain(Graphics g) {
		GameObject[][] terrain = gm.getTerrain();

		for (int col = 0; col < terrain.length; col++) {
			for (int row = 0; row < terrain[0].length; row++) {
				GameObject t = terrain[col][row];
				if (t != null) {
					if (isVisible(t)) {
						t.render(g);
					}
				}
			}
		}
		return terrain[0].length * terrain.length;

	}

	private int renderObjects(Graphics g) {
		for (int i = 0; i < gm.getGameObjects().size(); i++) {
			GameObject tempObject = gm.getGameObjects().get(i);
			if (isVisible(tempObject)) {
				tempObject.render(g);
				if (tempObject.isCollidable()) {
					Rectangle h = tempObject.getBounds();
					g.drawRect((int) h.getX(), (int) h.getY(), (int) h.getWidth(), (int) h.getHeight());
				}
				// tempObject.drawBounds(g);
			}
		}
		return gm.getGameObjects().size();
	}

	public boolean isVisible(GameObject go) {
		Camera cam = gm.getCamera();
		Rectangle r = new Rectangle((int) (-cam.getX()), (int) (-cam.getY()), Main.WIDTH + 32, Main.HEIGHT + 32);
		if (r.intersects(go.getBounds())) {
			return true;
		}
		return false;
	}

}
