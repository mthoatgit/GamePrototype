package de.mth.comon.test;

import org.junit.Before;
import org.junit.Test;

import de.mth.game.common.GameModel;
import de.mth.game.common.UpdateHandler;
import de.mth.game.gameobject.GameObject;
import de.mth.game.gameobject.Mountain;
import de.mth.game.gameobject.NPC;
import de.mth.game.gameobject.Player;

public class CollisionTest {

	private GameModel gameModel;
	private UpdateHandler uh;

	@Before
	public void setup() {
		gameModel = new GameModel();
		uh = new UpdateHandler(gameModel);

		GameObject player = new Player(100, 100);

		GameObject npc = new NPC(150, 150);
		npc.setPerceptionRange(60);

		GameObject mountain = new Mountain(50, 50);

		gameModel.getGameObjects().add(player);
		gameModel.getGameObjects().add(npc);
		gameModel.getGameObjects().add(mountain);

	}

	@Test
	public void testCollision() {

		// Assert.assertFalse(uh.checkCollision(tempObject););
	}

	@Test
	public void testPerception() {

	}

}
