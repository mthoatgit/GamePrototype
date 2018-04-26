package de.mth.game.texture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	private ArrayList<BufferedImage> imageList;
	
	public Animation(int speed, BufferedImage... args){
		this.speed = speed;
		images = new BufferedImage[args.length];
		for(int i = 0; i < args.length; i++){
			images[i] = args[i];
		}
		frames = args.length;
	}
	
	public Animation(int speed, ArrayList<BufferedImage> imageList){
		this.speed = speed;
		this.imageList = imageList;
		
		frames = imageList.size();
	}
	
	/**
	 * In Update-Methode des GameObjects aufrufen!
	 */
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}
	}
		
	private void nextFrame(){
		for(int i = 0; i < frames; i++){
			if(count == i){
				currentImg = imageList.get(i);
			}
		}
		
		count++;
		
		if(count > frames){
			count = 0; //Reset Animation
		}
	}
	
	/**
	 * In Render-Methode des GameObjects aufrufen!
	 */
	public void drawAnimation(Graphics g, int x, int y){
		g.drawImage(currentImg,  x,  y,  null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
		g.drawImage(currentImg,  x,  y,  scaleX, scaleY, null);
	}
	
}
