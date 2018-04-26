package de.mth.game.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.mth.game.gameobject.GameObject;

public class QuadTree {

	private int MAX_OBJECTS = 9;
	private int MAX_LEVELS = 5;

	private int level;
	private List<GameObject> objects;
	private Rectangle bounds;
	private QuadTree[] nodes;

	/*
	 * Constructor
	 */
	public QuadTree(int pLevel, Rectangle pBounds) {
		level = pLevel;
		objects = new ArrayList<GameObject>();
		bounds = pBounds;
		nodes = new QuadTree[4];
	}

	/*
	 * Clears the QuadTree
	 */
	public void clear() {
		objects.clear();

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}

	}

	/*
	 * Splits the node into 4 subnodes
	 */
	private void split() {
		// System.out.println("QuadTree.split()");
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();

		nodes[0] = new QuadTree(level + 1, new Rectangle(x + subWidth, y,
				subWidth, subHeight));
		nodes[1] = new QuadTree(level + 1, new Rectangle(x, y, subWidth,
				subHeight));
		nodes[2] = new QuadTree(level + 1, new Rectangle(x, y + subHeight,
				subWidth, subHeight));
		nodes[3] = new QuadTree(level + 1, new Rectangle(x + subWidth, y
				+ subHeight, subWidth, subHeight));
	}

	/*
	 * Determine which node the object belongs to. -1 means object cannot
	 * completely fit within a child node and is part of the parent node
	 */
	private int getIndex(GameObject gameObject) {
		Rectangle pRect = gameObject.getBounds();

		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect
				.getY() + pRect.getHeight() < horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);

		// Object can completely fit within the left quadrants
		if (pRect.getX() < verticalMidpoint
				&& pRect.getX() + pRect.getWidth() < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if (pRect.getX() > verticalMidpoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}
		// System.out.println("QuadTree.getIndex() " + index);
		return index;
	}

	/*
	 * Insert the object into the QuadTree. If the node exceeds the capacity, it
	 * will split and add all objects to their corresponding nodes.
	 */
	public void insert(GameObject pRect) {
		if (nodes[0] != null) {
			int index = getIndex(pRect);

			if (index != -1) {
				nodes[index].insert(pRect);

				return;
			}
		}

		objects.add(pRect);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				} else {
					i++;
				}
			}
		}
	}

	/*
	 * Return all objects that could collide with the given object
	 */
	public List<GameObject> retrieve(List<GameObject> returnObjects,
			GameObject gameObject) {
		int index = getIndex(gameObject);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, gameObject);
		}

		returnObjects.addAll(objects);

		return returnObjects;
	}

	// public static void main(String[] args) {
	// QuadTree quad = new QuadTree(0, new Rectangle(0, 0, 600, 600));
	//
	// ArrayList<Rectangle> allObjects = new ArrayList<Rectangle>();
	// // allObjects.add(new Rectangle(0, 0, 10, 10));
	// // allObjects.add(new Rectangle(20, 20, 10, 10));
	// // allObjects.add(new Rectangle(40, 20, 10, 10));
	// // allObjects.add(new Rectangle(60, 20, 10, 10));
	// // allObjects.add(new Rectangle(80, 20, 10, 10));
	// // allObjects.add(new Rectangle(0, 0, 10, 10));
	// // allObjects.add(new Rectangle(200, 20, 10, 10));
	// // allObjects.add(new Rectangle(400, 20, 10, 10));
	// // allObjects.add(new Rectangle(500, 20, 10, 10));
	// // allObjects.add(new Rectangle(300, 20, 10, 10));
	// // allObjects.add(new Rectangle(250, 40, 10, 10));
	// // allObjects.add(new Rectangle(300, 60, 10, 10));
	// // allObjects.add(new Rectangle(250, 60, 10, 10));
	// //
	// // allObjects.add(new Rectangle(200, 400, 10, 10));
	// // allObjects.add(new Rectangle(400, 400, 10, 10));
	// // allObjects.add(new Rectangle(500, 400, 10, 10));
	// // allObjects.add(new Rectangle(250, 400, 10, 10));
	// // allObjects.add(new Rectangle(300, 400, 10, 10));
	// // allObjects.add(new Rectangle(250, 400, 10, 10));
	// //
	// // allObjects.add(new Rectangle(200, 500, 10, 10));
	// // allObjects.add(new Rectangle(400, 500, 10, 10));
	// // allObjects.add(new Rectangle(500, 500, 10, 10));
	// // allObjects.add(new Rectangle(300, 550, 10, 10));
	// // allObjects.add(new Rectangle(250, 500, 10, 10));
	// //
	// // allObjects.add(new Rectangle(220, 500, 10, 10));
	// // allObjects.add(new Rectangle(420, 500, 10, 10));
	// // allObjects.add(new Rectangle(520, 500, 10, 10));
	// // allObjects.add(new Rectangle(320, 550, 10, 10));
	// allObjects.add(new Rectangle(270, 500, 10, 10));
	// allObjects.add(new Rectangle(275, 500, 10, 10));
	// // allObjects.add(new Rectangle(290, 500, 10, 10));
	// //
	// // allObjects.add(new Rectangle(220, 450, 10, 10));
	// // allObjects.add(new Rectangle(420, 450, 10, 10));
	// // allObjects.add(new Rectangle(520, 450, 10, 10));
	// // allObjects.add(new Rectangle(320, 450, 10, 10));
	// // allObjects.add(new Rectangle(270, 450, 10, 10));
	// // allObjects.add(new Rectangle(290, 450, 10, 10));
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
	// // System.out.println("check " + returnObjects.size());
	//
	// for (int x = 0; x < returnObjects.size(); x++) {
	// // Run collision detection algorithm between objects
	// if (returnObjects.get(x).intersects(allObjects.get(i))) {
	// // if (!(returnObjects.get(x).getX() == allObjects.get(i)
	// // .getX() && returnObjects.get(x).getY() == allObjects
	// // .get(i).getY())) {
	// System.out.println("QuadTree.main()");
	// // }
	//
	// // System.out.println("intersect");
	// // System.out.println("x " + returnObjects.get(x).getX()
	// // + " y  " + returnObjects.get(x).getY());
	// // System.out.println("ix " + allObjects.get(i).getX()
	// // + " iy  " + allObjects.get(i).getY());
	// }
	// }
	//
	// }
	// System.out.println(allObjects.size());
	//
	// }
}
