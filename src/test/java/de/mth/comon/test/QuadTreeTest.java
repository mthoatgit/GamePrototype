package de.mth.comon.test;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.mth.game.collision.QuadTree;

public class QuadTreeTest {

	public QuadTree quad;
	public ArrayList<Rectangle> allObjects;

	@Before
	public void setup() {
		quad = new QuadTree(0, new Rectangle(0, 0, 600, 600));

		allObjects = new ArrayList<Rectangle>();
		allObjects.add(new Rectangle(0, 0, 10, 10));
		allObjects.add(new Rectangle(20, 20, 10, 10));
		allObjects.add(new Rectangle(40, 20, 10, 10));
		allObjects.add(new Rectangle(60, 20, 10, 10));
		allObjects.add(new Rectangle(80, 20, 10, 10));
		allObjects.add(new Rectangle(0, 0, 10, 10));
		allObjects.add(new Rectangle(200, 20, 10, 10));
		allObjects.add(new Rectangle(400, 20, 10, 10));
		allObjects.add(new Rectangle(500, 20, 10, 10));
		allObjects.add(new Rectangle(300, 20, 10, 10));
		allObjects.add(new Rectangle(250, 40, 10, 10));
		allObjects.add(new Rectangle(300, 60, 10, 10));
		allObjects.add(new Rectangle(250, 60, 10, 10));

		allObjects.add(new Rectangle(200, 400, 10, 10));
		allObjects.add(new Rectangle(400, 400, 10, 10));
		allObjects.add(new Rectangle(500, 400, 10, 10));
		allObjects.add(new Rectangle(250, 400, 10, 10));
		allObjects.add(new Rectangle(300, 400, 10, 10));
		allObjects.add(new Rectangle(250, 400, 10, 10));

		allObjects.add(new Rectangle(200, 500, 10, 10));
		allObjects.add(new Rectangle(400, 500, 10, 10));
		allObjects.add(new Rectangle(500, 500, 10, 10));
		allObjects.add(new Rectangle(300, 550, 10, 10));
		allObjects.add(new Rectangle(250, 500, 10, 10));

		allObjects.add(new Rectangle(220, 500, 10, 10));
		allObjects.add(new Rectangle(420, 500, 10, 10));
		allObjects.add(new Rectangle(520, 500, 10, 10));
		allObjects.add(new Rectangle(320, 550, 10, 10));
		allObjects.add(new Rectangle(270, 500, 10, 10));
		allObjects.add(new Rectangle(275, 500, 10, 10));
		allObjects.add(new Rectangle(290, 500, 10, 10));

		allObjects.add(new Rectangle(220, 450, 10, 10));
		allObjects.add(new Rectangle(420, 450, 10, 10));
		allObjects.add(new Rectangle(520, 450, 10, 10));
		allObjects.add(new Rectangle(320, 450, 10, 10));
		allObjects.add(new Rectangle(270, 450, 10, 10));
		allObjects.add(new Rectangle(290, 450, 10, 10));
	}

	@Test
	public void testQuadTree() {
		//
		// quad.clear();
		// for (int i = 0; i < allObjects.size(); i++) {
		// quad.insert(allObjects.get(i));
		// }
		//
		// List<Rectangle> returnObjects = new ArrayList<Rectangle>();
		// for (int i = 0; i < allObjects.size(); i++) {
		// returnObjects.clear();
		// quad.retrieve(returnObjects, allObjects.get(i));
		//
		// for (int x = 0; x < returnObjects.size(); x++) {
		// // Run collision detection algorithm between objects
		// if (returnObjects.get(x).intersects(allObjects.get(i))) {
		// if (!(returnObjects.get(x).getX() == allObjects.get(i)
		// .getX() && returnObjects.get(x).getY() == allObjects
		// .get(i).getY())) {
		//
		// }
		// }
		// }
		//
		// }
	}

	@Test
	public void testRetrieve() {
		// quad.clear();
		// for (int i = 0; i < allObjects.size(); i++) {
		// quad.insert(allObjects.get(i));
		// }
		// List<Rectangle> returnObjects = new ArrayList<Rectangle>();
		// for (int i = 0; i < allObjects.size(); i++) {
		// returnObjects.clear();
		// quad.retrieve(returnObjects, allObjects.get(i));
		// }

	}

	@Test
	public void testInsert() {

	}

	@Test
	public void testGetIndex() {

	}
}