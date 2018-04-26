package de.mth.game.gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import de.mth.game.common.Main;
import de.mth.game.texture.Animation;
import de.mth.game.texture.TextureLoader;

public class NPC extends GameObjectBase {

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
			wander();

		}

		playerWalk.runAnimation();
	}

	public void wander() {

		Random random = new Random();
		setDestination(random.nextInt(Main.WIDTH), random.nextInt(Main.HEIGHT));

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
	public void setPerceptionRange(int perceptionRange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolveCollision(GameObject gameObject) {
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
