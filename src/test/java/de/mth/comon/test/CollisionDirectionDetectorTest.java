package de.mth.comon.test;

import static org.junit.Assert.*;

import org.junit.*;

import de.mth.game.collision.*;
import de.mth.game.collision.CollisionDirectionDetector.Direction;
import de.mth.game.gameobject.*;

public class CollisionDirectionDetectorTest {

	@Test
	public void getLeftCollisionTest() {
		GameObject player = new Player(30, 30);
		GameObject mountain = new Mountain(0, 30);

		Direction direction = CollisionDirectionDetector.getDirection(player.getBounds(), mountain.getBounds());
		// Player is right Other is left -> Collision Player on left Side
		assertEquals(Direction.LEFT, direction);
	}

	
	@Test
	public void getRightCollisionTest() {
		GameObject player = new Player(0, 30);
		GameObject mountain = new Mountain(30, 30);

		Direction direction = CollisionDirectionDetector.getDirection(player.getBounds(), mountain.getBounds());
		// Player is left Other is Right -> Collision Player on Right Side
		assertEquals(Direction.RIGHT, direction);

	}


	@Test
	public void getTopCollisionTest() {
		GameObject player = new Player(30, 30);
		GameObject mountain = new Mountain(30, 0);

		Direction direction = CollisionDirectionDetector.getDirection(player.getBounds(), mountain.getBounds());
		// // Player is bottom Other is top -> Collision Player on top Side
		assertEquals(Direction.TOP, direction);
	}

	@Test
	public void getBottomCollisionTest() {
		GameObject player = new Player(30, 0);
		GameObject mountain = new Mountain(30, 30);

		Direction direction = CollisionDirectionDetector.getDirection(player.getBounds(), mountain.getBounds());
		// Player is top Other is bottom -> Collision Player on bottom Side
		assertEquals(Direction.BOTTOM, direction);
	}

	// @Test
	// public void isCollidingTopTest() {
	// GameObject player = new Player(100, 100);
	// GameObject mountain = new Mountain(100, 70);
	//
	// boolean intersects = mountain.getBounds().intersects(player.getBounds());
	// assertTrue(intersects);
	//
	// boolean collidingTop = player.isCollidingTop(mountain);
	// boolean collidingBottom = player.isCollidingBottom(mountain);
	// boolean collidingLeft = player.isCollidingLeft(mountain);
	// boolean collidingRight = player.isCollidingRight(mountain);
	//
	// assertFalse(collidingLeft);
	// assertFalse(collidingRight);
	// assertFalse(collidingBottom);
	// assertTrue(collidingTop);
	// }
	//
	// @Test
	// public void isCollidingBottomTest() {
	// GameObject player = new Player(100, 70);
	//
	// GameObject mountain = new Mountain(100, 100);
	//
	// boolean intersects = mountain.getBounds().intersects(player.getBounds());
	// assertTrue(intersects);
	//
	// boolean collidingTop = player.isCollidingTop(mountain);
	// boolean collidingBottom = player.isCollidingBottom(mountain);
	// boolean collidingLeft = player.isCollidingLeft(mountain);
	// boolean collidingRight = player.isCollidingRight(mountain);
	//
	// assertFalse(collidingLeft);
	// assertFalse(collidingRight);
	// assertTrue(collidingBottom);
	// assertFalse(collidingTop);
	//
	// }
	//
	// @Test
	// public void isCollidingLeftTest() {
	// GameObject player = new Player(70, 100);
	// GameObject mountain = new Mountain(100, 100);
	//
	// boolean intersects = mountain.getBounds().intersects(player.getBounds());
	// assertTrue(intersects);
	//
	// boolean collidingTop = player.isCollidingTop(mountain);
	// boolean collidingBottom = player.isCollidingBottom(mountain);
	// boolean collidingLeft = player.isCollidingLeft(mountain);
	// boolean collidingRight = player.isCollidingRight(mountain);
	//
	// assertTrue(collidingLeft);
	// assertFalse(collidingRight);
	// assertFalse(collidingBottom);
	// assertFalse(collidingTop);
	// }
	//
	// @Test
	// public void isCollidingRightTest() {
	// GameObject player = new Player(100, 100);
	// GameObject mountain = new Mountain(70, 100);
	//
	// boolean intersects = mountain.getBounds().intersects(player.getBounds());
	// assertTrue(intersects);
	//
	// boolean collidingTop = player.isCollidingTop(mountain);
	// boolean collidingBottom = player.isCollidingBottom(mountain);
	// boolean collidingLeft = player.isCollidingLeft(mountain);
	// boolean collidingRight = player.isCollidingRight(mountain);
	//
	// assertFalse(collidingLeft);
	// assertTrue(collidingRight);
	// assertFalse(collidingBottom);
	// assertFalse(collidingTop);
	// }

}
