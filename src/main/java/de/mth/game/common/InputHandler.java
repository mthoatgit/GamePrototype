package de.mth.game.common;

import de.mth.game.gameobject.Player;

public class InputHandler {

	private GameModel gm;

	public InputHandler(GameModel gm) {
		this.gm = gm;

	}

	public void input() {
		playerInput();

	}

	public void playerInput() {

		Player p = gm.getPlayer();
		Input input = gm.getInput();
		Camera cam = gm.getCamera();
		if (input.isMousePressed()) {
			p.setDestination((int) (input.getPressedX() - cam.getX()),
					(int) (input.getPressedY() - cam.getY()));
		}
		if (input.isMouseDragged()) {
			p.setDestination((int) (input.getDraggedX() - cam.getX()),
					(int) (input.getDraggedY() - cam.getY()));
		}
		if (input.isMouseReleased()) {
			p.setDestination((int) (input.getReleasedX() - cam.getX()),
					(int) (input.getReleasedY() - cam.getY()));
			input.setMouseReleased(false);
		}

	}

	public float[] convertToGlobal(float valueX, float valueY) {
		float[] converted = new float[2];
		converted[0] = valueX - gm.getCamera().getX();
		converted[1] = valueY - gm.getCamera().getY();

		return converted;
	}

}
