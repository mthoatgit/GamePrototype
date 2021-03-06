package de.mth.comon.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import de.mth.game.collision.CollisionResolver;
import de.mth.game.gameobject.GameObject;
import de.mth.game.gameobject.Mountain;
import de.mth.game.gameobject.NonPlayer;
import de.mth.game.gameobject.Player;

public class PlayerResolveCollisionTest {

private static CollisionResolver collisionResolver;
	
	private ArrayList<GameObject> allObjects;
	
	@BeforeClass
	public static void setup() {
		collisionResolver = new CollisionResolver();
	}
	
	@Test
	public void resolveCollisionWithVerticalCollision() {
		allObjects = new ArrayList<>();
		Player player = new Player(40, 60);
		player.setVelX(1);
		player.setVelY(1);
		
		
		Mountain mountain1 = new Mountain(30, 30); //Top Collision
		Mountain mountain2 = new Mountain(30, 90); //Bottom Collision
		
		NonPlayer npc = new NonPlayer(300, 300); //No Collision
		allObjects.add(player);
		allObjects.add(mountain1);
		allObjects.add(mountain2);
		allObjects.add(npc);
		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allObjects);
		ArrayList<GameObject> gameObjects = objectsToResolveMap.get(player);
		
		player.resolveCollision(gameObjects);
		
		assertEquals(2, gameObjects.size());
		
		assertEquals(1, player.getVelX(), 0);
		assertEquals(0, player.getVelY(), 0);
	}
	
	@Test
	public void resolveCollision() {
		allObjects = new ArrayList<>();
		Player player = new Player(40, 60);
		player.setVelX(1);
		player.setVelY(1);
		
		
		Mountain mountainTop = new Mountain(30, 30); //Top Collision
		Mountain mountainBottom = new Mountain(30, 90); //Bottom Collision
		Mountain mountainLeft = new Mountain(10, 60); //Left Collision
//		Mountain mountainRight = new Mountain(70, 60); //Right Collision
		
		NonPlayer npc = new NonPlayer(300, 300); //No Collision
		
		allObjects.add(player);
		allObjects.add(mountainTop);
		allObjects.add(mountainBottom);
		allObjects.add(mountainLeft);
//		allObjects.add(mountainRight);
		allObjects.add(npc);
		
		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allObjects);
		ArrayList<GameObject> gameObjects = objectsToResolveMap.get(player);
		
		player.resolveCollision(gameObjects);
		
		assertEquals(1, player.getVelX(), 0);
		assertEquals(0, player.getVelY(), 0);
	}
	
	@Test
	public void resolveCollision2() {
		allObjects = new ArrayList<>();
		Player player = new Player(40, 60);
		player.setVelX(1);
		player.setVelY(1);
		
		
		Mountain mountainTop = new Mountain(30, 30); //Top Collision
		Mountain mountainRight = new Mountain(70, 60); //Right Collision
		NonPlayer npc = new NonPlayer(300, 300); //No Collision
		
		allObjects.add(player);
		allObjects.add(mountainTop);
		allObjects.add(mountainRight);
		allObjects.add(npc);
		
		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allObjects);
		ArrayList<GameObject> gameObjects = objectsToResolveMap.get(player);
		
		player.resolveCollision(gameObjects);
		
		assertEquals(0, player.getVelX(), 0);
		assertEquals(1, player.getVelY(), 0);
	}
	
	@Test
	public void resolveCollision3() {
		allObjects = new ArrayList<>();
		Player player = new Player(40, 60);
		player.setVelX(-1);
		player.setVelY(-1);
		
		
		Mountain mountainTop = new Mountain(30, 30); //Top Collision
		Mountain mountainBottom = new Mountain(30, 90); //Bottom Collision
//		Mountain mountainLeft = new Mountain(10, 60); //Left Collision
		Mountain mountainRight = new Mountain(70, 60); //Right Collision
		
		NonPlayer npc = new NonPlayer(300, 300); //No Collision
		
		allObjects.add(player);
		allObjects.add(mountainTop);
		allObjects.add(mountainBottom);
//		allObjects.add(mountainLeft);
		allObjects.add(mountainRight);
		allObjects.add(npc);
		
		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allObjects);
		ArrayList<GameObject> gameObjects = objectsToResolveMap.get(player);
		
		player.resolveCollision(gameObjects);
		
		assertEquals(-1, player.getVelX(), 0);
		assertEquals(0, player.getVelY(), 0);
	}
	
	@Test
	public void resolveCollision4() {
		allObjects = new ArrayList<>();
		Player player = new Player(40, 60);
		player.setVelX(-1);
		player.setVelY(-1);
		
		
		Mountain mountainBottom = new Mountain(30, 90); //Bottom Collision
		Mountain mountainLeft = new Mountain(10, 60); //Left Collision
		Mountain mountainRight = new Mountain(70, 60); //Right Collision
		
		NonPlayer npc = new NonPlayer(300, 300); //No Collision
		
		allObjects.add(player);
		allObjects.add(mountainBottom);
		allObjects.add(mountainLeft);
		allObjects.add(mountainRight);
		allObjects.add(npc);
		
		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allObjects);
		ArrayList<GameObject> gameObjects = objectsToResolveMap.get(player);
		
		player.resolveCollision(gameObjects);
		
		assertEquals(0, player.getVelX(), 0);
		assertEquals(-1, player.getVelY(), 0);
	}

	
}
