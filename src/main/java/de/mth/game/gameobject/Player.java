package de.mth.game.gameobject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

import de.mth.game.collision.CollisionDirectionDetector;
import de.mth.game.collision.CollisionDirectionDetector.Direction;
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
		boolean left = false;
		boolean right = false;
		boolean top = false;
		boolean bottom = false;

		// setCollidingAtNextStep(true);
		CollisionDirectionDetector collisionDirectionDetector = new CollisionDirectionDetector();

		Rectangle2D nextStep = getNextStep();

		if (gameObjects.size() > 2) {
			System.out.println("more than 2");
		}

		for (GameObject gameObject : gameObjects) {

			Direction direction = collisionDirectionDetector.getDirection(nextStep, gameObject);
			// System.out.println("Player.resolveCollision()");
			switch (direction) {
			case LEFT:
				left = true;
				// if (getVelX() < 0) {
				// setVelX(0);
				// }
				break;
			case RIGHT:
				right = true;
				// if (getVelX() > 0) {
				// setVelX(0);
				// }
				break;
			case TOP:
				// if(this.getBounds().intersects(gameObject.getBounds())) {
				// System.out.println("Player.resolveCollision()");
				// Rectangle mountain = gameObject.getBounds();
				// Rectangle player = this.getBounds();
				// player.get

				// }
				top = true;
				GameObject gameObject2 = gameObject;
				// if (getVelY() < 0) {
				// setVelY(0);
				// }
				break;
			case BOTTOM:
				bottom = true;
				// if (getVelY() > 0) {
				// setVelY(0);
				// }
				break;
			default:
			}

		}

		/*
		 * Check ob der Player an einer Wand langläuft. (Wand = mehrere Objekte direkt
		 * nebeneinander)
		 */
		if ((top || bottom) && (right || left)) {
			if (getVelX() != 0 && getVelY() != 0) {

				boolean isVerticalWall = false;
				boolean isHorizontalWall = false;
				boolean isWall = false;
				for (GameObject x : gameObjects) {
					for (GameObject y : gameObjects) {
						if (!x.equals(y)) {
							if (x.getY() == y.getY()) {
								isHorizontalWall = true;
							}
							if (x.getX() == y.getX()) {
								isVerticalWall = true;
							}
						}
					}
				}

				if (isHorizontalWall && !(isHorizontalWall && isVerticalWall)) {
					right = false;
					left = false;
					System.out.println("right = false");
				} else if (isVerticalWall && !(isHorizontalWall && isVerticalWall)) {
					top = false;
					bottom = false;
					System.out.println("top = false");
				}

			}

		}

		if (left) {
			if (getVelX() < 0) {
				setVelX(0);
			}
		}
		if (right) {
			if (getVelX() > 0) {
				setVelX(0);
			}
		}
		if (top) {
			if (getVelY() < 0) {
				setVelY(0);
			}
		}
		if (bottom) {
			if (getVelY() > 0)
				setVelY(0);
		}

		if (top || bottom && right || left)

		{
			// System.out.println("Player.resolveCollision()");
		}

	}

	public double recalculateVelocity(GameObject gameObject) {
		/*
		 * NextStep wird kollidieren Ziel: Velocity nicht direkt auf Null setzen sondern
		 * abhängig von der Entfernung
		 */

		double y2 = gameObject.getY();
		double result = getY() - y2 - 1.0;

		return result;
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
