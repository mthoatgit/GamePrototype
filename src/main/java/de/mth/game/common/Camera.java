package de.mth.game.common;

import java.awt.Rectangle;

import de.mth.game.gameobject.Player;

public class Camera {
	private float x, y = 0;

	public Camera() {
	}

	public void tick(Player player) {
		x = -player.getX() + Main.WIDTH / 2 - player.getWidth() / 2;
		y = -player.getY() + Main.HEIGHT / 2 - player.getHeight() / 2;
		// System.out.println(x +" " + y);
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

	public Rectangle getBounds() {
		return new Rectangle((int) -x, (int) -y, Main.WIDTH + 32,
				Main.HEIGHT + 32);
	}

}
