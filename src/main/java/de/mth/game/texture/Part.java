package de.mth.game.texture;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Part{

	private int x;
	private int y;
	private BufferedImage sprite;
	
	public Part(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(getX(), getY(), 32, 32);
	}
	
	

}
