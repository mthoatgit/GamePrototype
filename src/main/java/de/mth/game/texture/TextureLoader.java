package de.mth.game.texture;

public class TextureLoader {

	private static final TextureLoader instance = new TextureLoader();

	private static final String TERRAIN = "/terrain_sprite.png";
	private static final String PLAYER = "/sprite_zelda_scaled.png";
	private static final String ENEMY = "/player_sheet.png";

	private Texture texTerrain;
	private Texture texPlayer;
	private Texture texEnemy;

	private TextureLoader() {
		this.texTerrain = new Texture(TERRAIN);
		this.texPlayer = new Texture(PLAYER);
		this.texEnemy = new Texture(ENEMY);
	}

	public static TextureLoader getInstance() {
		return instance;
	}

	public Texture getTexTerrain() {
		return texTerrain;
	}

	public void setTexTerrain(Texture texTerrain) {
		this.texTerrain = texTerrain;
	}

	public Texture getTexPlayer() {
		return texPlayer;
	}

	public void setTexPlayer(Texture texPlayer) {
		this.texPlayer = texPlayer;
	}

	public Texture getTexEnemy() {
		return texEnemy;
	}

	public void setTexEnemy(Texture texEnemy) {
		this.texEnemy = texEnemy;
	}
}
