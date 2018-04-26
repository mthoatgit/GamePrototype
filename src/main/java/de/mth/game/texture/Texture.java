package de.mth.game.texture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Texture {
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	private SpriteSheet ss;
	
	private BufferedImage image = null;
	
	private BufferedImage[][] images;
		
	
	public Texture(String path){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			image = loader.loadImage(path);
		}catch(Exception e){
			e.printStackTrace();
		} 
		images = new BufferedImage[image.getWidth()/WIDTH][image.getHeight()/HEIGHT];
		ss = new SpriteSheet(image);
		
		loadTextures();
	}
	
	private void loadTextures(){
		for(int col = 0; col < getImage().getWidth()/WIDTH; col++){
			for(int row = 0; row< getImage().getHeight()/HEIGHT; row++){
				BufferedImage bi = grabTexture(col, row, WIDTH, HEIGHT);
				if(bi != null){
					addSubImageAt(bi, col, row);
				}
			}
		}
	}
	
	public BufferedImage getSprite(int col, int row){
		return images[col][row];
	}
	
	protected BufferedImage grabTexture(int col, int row, int width, int height){
		return getSpriteSheet().grabImage(col, row, width, height);
		
	}

	protected void addSubImageAt(BufferedImage subImage, int col, int row){
		images[col][row] = subImage; 
	}
	
	protected BufferedImage[][] getImages() {
		return images;
	}

	protected void setImages(BufferedImage[][] images) {
		this.images = images;
	}

	public SpriteSheet getSpriteSheet() {
		return ss;
	}

	protected void setSpriteSheet(SpriteSheet ss) {
		this.ss = ss;
	}

	protected BufferedImage getImage() {
		return image;
	}

	protected void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public int getWidth(){
		return getImage().getWidth();
	}
	
	public int getHeight(){
		return getImage().getHeight();
		
	}
	
	public ArrayList<BufferedImage> buildAnimation(int colFrom, int colTo, int row){		
		ArrayList<BufferedImage> animationList = new ArrayList<BufferedImage>();
		for(int i = colFrom; i < colTo+1; i++){
			animationList.add(getSprite(i, row));
		}
		return animationList; 	
	}
}
