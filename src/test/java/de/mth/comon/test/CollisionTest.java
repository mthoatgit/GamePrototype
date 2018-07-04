package de.mth.comon.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import de.mth.game.common.*;
import de.mth.game.gameobject.*;

public class CollisionTest {

//	private GameModel gameModel;
//
//	@Before
//	public void setup() {
//		// gameModel = new GameModel();
//		// uh = new UpdateHandler(gameModel);
//		//
//		// GameObject player = new Player(100, 100);
//		//
//		// GameObject npc = new NPC(150, 150);
//		// npc.setPerceptionRange(60);
//		//
//		// GameObject mountain = new Mountain(50, 50);
//		//
//		// gameModel.getGameObjects().add(player);
//		// gameModel.getGameObjects().add(npc);
//		// gameModel.getGameObjects().add(mountain);
//
//	}
//
//	@Test
//	public void testIsCollidingTop() {
//		Player player = new Player(100, 100);
//		Mountain mountain = new Mountain(100, 70);
//
//		boolean intersects = mountain.getBounds().intersects(player.getBounds());
//		assertTrue(intersects);
//
//		boolean collidingTop = player.isCollidingTop(mountain);
//		boolean collidingBottom = player.isCollidingBottom(mountain);
//		boolean collidingLeft = player.isCollidingLeft(mountain);
//		boolean collidingRight = player.isCollidingRight(mountain);
//
//		assertFalse(collidingLeft);
//		assertFalse(collidingRight);
//		assertFalse(collidingBottom);
//		assertTrue(collidingTop);
//	}
//
//	@Test
//	public void testIsCollidingBottom() {
//		GameObject player = new Player(100, 70);
//
//		GameObject mountain = new Mountain(100, 100);
//
//		boolean intersects = mountain.getBounds().intersects(player.getBounds());
//		assertTrue(intersects);
//
//		boolean collidingTop = player.isCollidingTop(mountain);
//		boolean collidingBottom = player.isCollidingBottom(mountain);
//		boolean collidingLeft = player.isCollidingLeft(mountain);
//		boolean collidingRight = player.isCollidingRight(mountain);
//
//		assertFalse(collidingLeft);
//		assertFalse(collidingRight);
//		assertTrue(collidingBottom);
//		assertFalse(collidingTop);
//
//	}
//
//	@Test
//	public void testIsCollidingLeft() {
//		GameObject player = new Player(70, 100);
//		GameObject mountain = new Mountain(100, 100);
//
//		boolean intersects = mountain.getBounds().intersects(player.getBounds());
//		assertTrue(intersects);
//
//		boolean collidingTop = player.isCollidingTop(mountain);
//		boolean collidingBottom = player.isCollidingBottom(mountain);
//		boolean collidingLeft = player.isCollidingLeft(mountain);
//		boolean collidingRight = player.isCollidingRight(mountain);
//
//		assertTrue(collidingLeft);
//		assertFalse(collidingRight);
//		assertFalse(collidingBottom);
//		assertFalse(collidingTop);
//	}
//
//	@Test
//	public void testIsCollidingRight() {
//		GameObject player = new Player(100, 100);
//		GameObject mountain = new Mountain(70, 100);
//
//		boolean intersects = mountain.getBounds().intersects(player.getBounds());
//		assertTrue(intersects);
//
//		boolean collidingTop = player.isCollidingTop(mountain);
//		boolean collidingBottom = player.isCollidingBottom(mountain);
//		boolean collidingLeft = player.isCollidingLeft(mountain);
//		boolean collidingRight = player.isCollidingRight(mountain);
//
//		assertFalse(collidingLeft);
//		assertTrue(collidingRight);
//		assertFalse(collidingBottom);
//		assertFalse(collidingTop);
//	}
//
//	@Test
//	public void testCollision() {
//
//		GameObject player = new Player(110, 70);
//		player.setVelX(10);
//		player.setVelY(10);
//
//		ArrayList<GameObject> gameObjects = new ArrayList<>();
//		GameObject mountain1 = new Mountain(100, 100);
//		GameObject mountain2 = new Mountain(132, 100);
//		GameObject npc = new NPC(50, 50);
//		npc.setVelX(10);
//		npc.setVelY(10);
//
//		gameObjects.add(mountain1);
//		gameObjects.add(mountain2);
//
//		boolean intersects = mountain1.getBounds().intersects(player.getBounds());
//		boolean intersects2 = mountain2.getBounds().intersects(player.getBounds());
//
//		assertTrue(intersects);
//		assertTrue(intersects2);
//
//		// player.isCollidingRight(mount)
//
//		player.resolveCollision(gameObjects);
//
//		assertEquals(true, true);
//
//		assertEquals("", npc.getVelX(), 10, 0);
//		assertEquals("", npc.getVelY(), 10, 0);
//
//		// Assert.assertFalse(uh.checkCollision(tempObject););
//	}
//
//	@Test
//	public void testPerception() {
//
//	}

}
