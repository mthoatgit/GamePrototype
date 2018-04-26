package de.mth.game.collision;

public class CollisionHelper {

	// public static int collisionDirection(GameObject player, GameObject tile)
	// {
	// if (!player.overlaps(tile))
	// return 0;
	//
	// float pL = player.getX(), // left
	// pR = player.getRight(), // right
	// pT = player.getY(), // bottom
	// pB = player.getBottom(); // top
	//
	// float tL = tile.getX(), // left
	// tR = tL + tile.getRight(), // right
	// tT = tile.getY(), // bottom
	// tB = tile.getBottom(); // top
	//
	// Float inf = Float.MAX_VALUE;
	// List<Float> intersect_diffs = new ArrayList<Float>(new Float[] { inf,
	// inf, inf, inf });
	//
	// if (pR > tL && pL < tL) // Player on left
	// intersect_diffs[0] = pR - tL;
	// if (pL < tR && pR > tR) // Player on Right
	// intersect_diffs[1] = tR - pL;
	// if (pT > tB && pB < tB) // Player on Bottom
	// intersect_diffs[2] = pT - tB;
	// if (pB < tT && pT > tT) // Player on Top
	// intersect_diffs[3] = tT - pB;
	//
	// // return the closest intersection
	// return intersect_diffs.indexOf(Collections.Min(intersect_diffs));
	// }

}
