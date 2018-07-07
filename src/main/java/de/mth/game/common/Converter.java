package de.mth.game.common;

public class Converter {

	private GameModel gm;

	public Converter(GameModel gm) {
		this.gm = gm;
	}

	public double[] convertToGlobal(double valueX, double valueY) {
		double[] converted = new double[2];
		converted[0] = valueX - gm.getCamera().getX();
		converted[1] = valueY - gm.getCamera().getY();

		return converted;
	}

}
