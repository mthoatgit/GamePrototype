package de.mth.game.common;

import java.awt.*;

import de.mth.game.gameobject.*;

public class Camera {
	
	private double x, y = 0;

	public Camera() {
	}

	public void tick(Player player) {
		x = -player.getX() + Window.WIDTH / 2 - player.getWidth() / 2;
		y = -player.getY() + Window.HEIGHT / 2 - player.getHeight() / 2;
		// System.out.println(x +" " + y);
	}

	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) -x, (int) -y, Window.WIDTH + 32, Window.HEIGHT + 32);
	}

}
