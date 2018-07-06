package de.mth.game.gameobject;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

import de.mth.game.texture.*;

public class NPC extends GameObject {

	private Animation playerWalk;

	private State state = State.WANDER;

	public enum State {
		WANDER, MOVE, ATTACK, NONE;
	}

	public NPC(int x, int y) {
		super(x, y);
		setCollidable(true);
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

		if (!isCollidingAtNextStep()) {

			setVelocity(getVelocity());
		}
		setCollidingAtNextStep(false);

		if (isAtDestination()) {
			setVelX(0);
			setVelY(0);
		}
		x += getVelX();
		y += getVelY();

		if (isAtDestination()) {
			// wander();

		}

		playerWalk.runAnimation();
	}

	public void wander() {

		Random random = new Random();
		setDestination(random.nextInt(Window.WIDTH), random.nextInt(Window.HEIGHT));

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

		if (gameObjects.size() != 0) {
			GameObject gameObject = gameObjects.get(0);

			setCollidingAtNextStep(true);
			if (gameObject instanceof Bullet) {
				destroy();
			}

			if (gameObject instanceof Player || gameObject instanceof Mountain) {
				gameObject.setCollidingAtNextStep(true);
				dodge(gameObject);

			}
		}
	}

	public void dodge(GameObject gameObject) {
		setVelocity(getVelocity());

//		if (isCollidingTop(gameObject)) {
//			setVelY(0);
//			// System.out.println("npc.top()");
//		}
//		if (isCollidingBottom(gameObject)) {
//			setVelY(0);
//			// setVelY(0);
//
//			// System.out.println("npc.bottom()");
//		}
//		if (isCollidingLeft(gameObject)) {
//			setVelX(0);
//			// System.out.println("npc.left()");
//		}
//		if (isCollidingRight(gameObject)) {
//			setVelX(0);
//			// System.out.println("npc.right()");
//		}
	}

}
