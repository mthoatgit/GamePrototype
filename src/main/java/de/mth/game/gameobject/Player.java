package de.mth.game.gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import de.mth.game.collision.CollisionDirectionDetector;
import de.mth.game.common.Camera;
import de.mth.game.common.Converter;
import de.mth.game.common.Game;
import de.mth.game.common.GameModel;
import de.mth.game.common.Input;
import de.mth.game.texture.Animation;
import de.mth.game.texture.TextureLoader;

public class Player extends MoveableGameObject {

	private Animation walkUp;
	private Animation walkDown;
	private Animation walkLeft;
	private Animation walkRight;

	private boolean aimModus = false;

	public Player(int x, int y) {
		super(x, y);
		setSpeed(2);
		setCollidable(true);

	}

	public void input(GameModel gm) {

		checkCooldowns();

		Input input = gm.getInput();
		Camera cam = gm.getCamera();

		/*
		 * Movement
		 */
		if (input.isRightMousePressed()) {
			setDestination((int) (input.getPressedX() - cam.getX()), (int) (input.getPressedY() - cam.getY()));
		}

		if (input.isRightMouseDragged()) {
			setDestination((int) (input.getDraggedX() - cam.getX()), (int) (input.getDraggedY() - cam.getY()));
		}
		if (input.isRightMouseReleased()) {
			setDestination((int) (input.getReleasedX() - cam.getX()), (int) (input.getReleasedY() - cam.getY()));
			input.setRightMouseReleased(false);
		}
		/*
		 * Shoot
		 */

		if (input.isA() && !onCooldown) {
			if (!onCooldown) {
				setAimModus(true);

			}

		}

		if (isAimModus() && input.isLeftMouseReleased()) {
			if (!onCooldown) {
				shoot(gm);
				ticks = 0;
				onCooldown = true;
				input.setLeftMouseReleased(false);
				setAimModus(false);
			}

		}
		if (isAimModus() && input.isRightMouseReleased()) {
			setAimModus(false);
		}

	}

	private void shoot(GameModel gm) {
		Input input = gm.getInput();
		Camera cam = gm.getCamera();

		Converter converter = new Converter(gm);
		double[] dest = converter.convertToGlobal(input.getMouseX(), input.getMouseY());

		gm.getGameObjects().add(new Bullet((int) getX(), (int) getY(), dest[0], dest[1]));
	}

	private void checkCooldowns() {
		ticks++;

		if (ticks == cooldown) {
			onCooldown = false;
		}

	}

	private boolean onCooldown = false;
	private int cooldown = 100;
	private int ticks = 0;

	@Override
	public void update() {

		// if (!isCollidingAtNextStep()) {
		//
		// setVelocity(getVelocity());
		// }
		// setCollidingAtNextStep(false);

		if (isAtDestination()) {
			setVelX(0);
			setVelY(0);
		}
		x += getVelX();
		y += getVelY();

		walkUp.runAnimation();
		walkDown.runAnimation();
		walkLeft.runAnimation();
		walkRight.runAnimation();

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

	@Override
	public void render(Graphics g) {
		if (velX != 0 || velY != 0) {
			if (Math.abs(velX) >= Math.abs(velY)) {
				if (velX < 0) {
					// links
					walkLeft.drawAnimation(g, (int) x, (int) y);
					movement = Movement.LEFT;
				} else {
					// rechts
					walkRight.drawAnimation(g, (int) x, (int) y);
					movement = Movement.RIGHT;
				}
			} else {
				if (velY < 0) {
					// hoch
					walkUp.drawAnimation(g, (int) x, (int) y);
					movement = Movement.UP;
				} else {
					// runter
					walkDown.drawAnimation(g, (int) x, (int) y);
					movement = Movement.DOWN;

				}
			}
		} else {
			switch (movement) {
			case UP:
				g.drawImage(getTexture().getSprite(0, 0), (int) x, (int) y, null);
				break;
			case DOWN:
				g.drawImage(getTexture().getSprite(0, 1), (int) x, (int) y, null);
				break;
			case LEFT:
				g.drawImage(getTexture().getSprite(0, 2), (int) x, (int) y, null);
				break;
			case RIGHT:
				g.drawImage(getTexture().getSprite(0, 3), (int) x, (int) y, null);
				break;
			}
		}

		if (isAimModus()) {
			g.setColor(Color.RED);
			g.drawString("Aim", (int) x, (int) y);
		} else if (!onCooldown) {
			g.setColor(Color.RED);
			g.drawString("ready", (int) x + 50, (int) y);

		}

		if (Game.DEBUG) {
			renderDebugInformation(g);
		}

	}

	private void renderDebugInformation(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int) getDestinationX(), (int) getDestinationY(), 2, 2);

	}

	@Override
	public void defineTextures(TextureLoader textureLoader) {
		setTexture(textureLoader.getTexPlayer());

		walkUp = new Animation(10, getTexture().buildAnimation(0, 9, 3));
		walkDown = new Animation(10, getTexture().buildAnimation(0, 9, 2));
		walkLeft = new Animation(10, getTexture().buildAnimation(0, 9, 1));
		walkRight = new Animation(10, getTexture().buildAnimation(0, 9, 4));

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public Movement getMovement() {
		return movement;
	}

	public boolean isAimModus() {
		return aimModus;
	}

	public void setAimModus(boolean aimModus) {
		this.aimModus = aimModus;
	}

}
