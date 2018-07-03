package de.mth.game.gameobject;

import java.awt.*;
import java.util.*;

import de.mth.game.common.*;
import de.mth.game.texture.*;

public class Player extends GameObject {

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
		float[] dest = converter.convertToGlobal(input.getMouseX(), input.getMouseY());

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

		walkUp.runAnimation();
		walkDown.runAnimation();
		walkLeft.runAnimation();
		walkRight.runAnimation();

	}

	@Override
	public void resolveCollision(ArrayList<GameObject> gameObjects) {

		boolean left = true;
		boolean right = true;
		boolean top = true;
		boolean bottom = true;

		if (gameObjects.size() != 0) {

			setCollidingAtNextStep(true);
			// gameObject.setCollidingAtNextStep(true);

			// if (gameObject instanceof Mountain) {
			// if (Game.DEBUG) {
			// System.out.println("Player.resolveCollision()");
			// }
			// setVelX(0);
			// setVelY(0);
			//
			// }
			//
			// if (gameObject instanceof NPC) {
			//
			// dodge(gameObject);
			//
			// }

			/*
			 * TODO: Abfrage welche directions betroffen bzw. mehr als eine
			 * Richtung -> ansonsten existiert ein Bug bei
			 * nebeneinanderliegenden Objekten wie Mountain sodass der Player
			 * sich an den Objekten vorbeiglitched
			 */
			// if (gameObjects.size() != 0) {
			for (GameObject gameObject : gameObjects) {
				// GameObject gameObject = gameObjects.get(0);

				if (isCollidingTop(gameObject)) {
					top = false;
					// setVelY(0);
					// System.out.println("npc.top()");
				}
				if (isCollidingBottom(gameObject)) {
					bottom = false;
					// setVelY(0);
					// setVelY(0);

					// System.out.println("npc.bottom()");
				}
				if (isCollidingLeft(gameObject)) {
					left = false;
					// setVelX(0);
					// System.out.println("npc.left()");
				}
				if (isCollidingRight(gameObject)) {
					right = false;
					// setVelX(0);
					// System.out.println("npc.right()");
				}

			}

			// }

			// if (gameObjects.size() > 1) {
			// if (isCollidingTop(gameObject)) {
			// setVelY(0);
			// // System.out.println("npc.top()");
			// }
			// if (isCollidingBottom(gameObject)) {
			// setVelY(0);
			// // setVelY(0);
			//
			// // System.out.println("npc.bottom()");
			// }
			// if (isCollidingLeft(gameObject)) {
			// if (getVelX() < 0) {
			//
			// setVelX(0);
			// }
			// // System.out.println("npc.left()");
			// }
			// if (isCollidingRight(gameObject)) {
			// if (getVelY() > 0) {
			// setVelX(0);
			// }
			// // System.out.println("npc.right()");
			// }
			// }
		}

		if (!bottom) {
			if (getVelY() > 0) {
				setVelY(0);
			}
		}

		if (!top) {
			if (getVelY() < 0) {
				setVelY(0);
			}
		}

		if (!right) {
			if (getVelX() > 0) {

				setVelX(0);
			}
		}

		if (!left) {
			if (getVelX() < 0) {

				setVelX(0);
			}
		}

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

	@Override
	public void setPerceptionRange(int perceptionRange) {
		// TODO Auto-generated method stub

	}

	public boolean isAimModus() {
		return aimModus;
	}

	public void setAimModus(boolean aimModus) {
		this.aimModus = aimModus;
	}

}
