package de.mth.comon.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;
import org.mockito.verification.VerificationMode;

import de.mth.game.collision.CollisionResolver;
import de.mth.game.common.GameModel;
import de.mth.game.gameobject.GameObject;
import de.mth.game.gameobject.Mountain;
import de.mth.game.gameobject.NPC;
import de.mth.game.gameobject.Player;

public class CollisionResolverTest {

	
	private CollisionResolver collisionResolver;
	
	private ArrayList<GameObject> allObjects;
	private Player player;
	
	@Before
	public void setup() {
		collisionResolver = new CollisionResolver(new GameModel());
		allObjects = new ArrayList<>();
		player = new Player(40, 60);
		Mountain mountain1 = new Mountain(30, 30);
		Mountain mountain2 = new Mountain(62, 90);
		NPC npc = new NPC(300, 300);
		allObjects.add(player);
		allObjects.add(mountain1);
		allObjects.add(mountain2);
		allObjects.add(npc);
	}

	@Test
	public void getCollidedObjectsTest() {
		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allObjects);
		ArrayList<GameObject> arrayList = objectsToResolveMap.get(player);

		assertEquals(3, objectsToResolveMap.size());
		assertEquals(2, arrayList.size());
	}
	
	@Test
	public void resolveCollision() {
		Map<GameObject, ArrayList<GameObject>> objectsToResolveMap = collisionResolver.getCollidedObjects(allObjects);
		ArrayList<GameObject> gameObjects = objectsToResolveMap.get(player);
		
		player.resolveCollision(gameObjects);
		
		
		
	}



}
