package de.mth.game.gameobject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.util.*;

import de.mth.game.texture.*;

public class NonPlayer extends GameObject {

	private Animation playerWalk;

	private State state = State.WANDER;

	private Queue<Point> path;
	
	public enum State {
		WANDER, MOVE, ATTACK, NONE;
	}

	public NonPlayer(int x, int y) {
		super(x, y);
		setCollidable(true);
		
		this.path = new LinkedList<>();
			testQueue();
		
	}

	@Override
	public void defineTextures(TextureLoader textureLoader) {
		setTexture(textureLoader.getTexEnemy());

		ArrayList<BufferedImage> animationList = new ArrayList<BufferedImage>();
		animationList.add(getTexture().getSprite(0, 0));
		animationList.add(getTexture().getSprite(0, 1));
		playerWalk = new Animation(10, animationList);

	}

	@Override
	public void update() {

	

		if (isAtDestination()) {
			setVelX(0);
			setVelY(0);
			Point p;
			if(!path.isEmpty()) {
				
				p = path.poll();
				setDestination(p.getX(), p.getY());
			}else {
					testQueue();
			}
			
		}
		x += getVelX();
		y += getVelY();

		if (isAtDestination()) {
			 wander();

		}

		playerWalk.runAnimation();
	}

	public void wander() {

		Random random = new Random();
//		setDestination(random.nextInt(de.mth.game.common.Window.WIDTH), random.nextInt(de.mth.game.common.Window.WIDTH));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int) getDestinationX(), (int) getDestinationY(), 2, 2);
		if (velX != 0 || velY != 0) {
			playerWalk.drawAnimation(g, (int) x, (int) y);
		} else {
			g.drawImage(getTexture().getSprite(1, 0), (int) x, (int) y, null);
		}
	}

	@Override
	public void destroy() {
		setGarbage(true);

	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public void resolveCollision(ArrayList<GameObject> gameObjects) {

		setCollidingLeft(false);
		setCollidingRight(false);
		setCollidingTop(false);
		setCollidingBottom(false);

		Rectangle2D nextStep = getNextStep();

		// if (gameObjects.size() > 2) {
		// System.out.println("more than 2");
		// }

		getCollisionDirections(nextStep, gameObjects);

		/*
		 * Check ob der Player an einer Wand langläuft. (Wand = mehrere Objekte direkt
		 * nebeneinander)
		 */
		checkWallsAndCorners(gameObjects);

		correctVelocity();
		
	}
	
	public void createPath() {
		
	}
	
	public void testQueue(){
		
		path.add(new Point(100,100));
		path.add(new Point(200,100));
		path.add(new Point(200,200));
		path.add(new Point(100,200));
		
	
	}


}
