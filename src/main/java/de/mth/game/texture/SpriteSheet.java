package de.mth.game.texture;

import java.awt.image.BufferedImage;

public class SpriteSheet {
private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height){
		if((col*width)+width <= image.getWidth() && (row*height) + height < image.getHeight()){
			return image.getSubimage((col * width), (row * height), width, height);
			
		}
		return null;
	}
}
