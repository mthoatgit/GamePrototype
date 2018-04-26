package de.mth.game.common;

public class Converter {

	private GameModel gm;

	public Converter(GameModel gm) {
		this.gm = gm;
	}

	public float[] convertToGlobal(float valueX, float valueY) {
		float[] converted = new float[2];
		converted[0] = valueX - gm.getCamera().getX();
		converted[1] = valueY - gm.getCamera().getY();

		return converted;
	}

}
