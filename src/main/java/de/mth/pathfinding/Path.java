package de.mth.pathfinding;

import java.util.ArrayList;

public class Path {
	
	/** The list of steps building up this path */
	private ArrayList<Step> steps;
	
	/**
	 * Create an empty path
	 */
	public Path() {
		steps = new ArrayList<Step>();
	}

	/**
	 * Get the length of the path, i.e. the number of steps
	 * 
	 * @return The number of steps in this path
	 */
	public int getLength() {
		return steps.size();
	}
	
	/**
	 * Get the step at a given index in the path
	 * 
	 * @param index The index of the step to retrieve. Note this should
	 * be >= 0 and < getLength();
	 * @return The step information, the position on the map.
	 */
	public Step getStep(int index) {
		return (Step) steps.get(index);
	}
	
	/**
	 * Get the x coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose x coordinate should be retrieved
	 * @return The x coordinate at the step
	 */
	public int getX(int index) {
		return getStep(index).getX();
	}

	/**
	 * Get the y coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose y coordinate should be retrieved
	 * @return The y coordinate at the step
	 */
	public int getY(int index) {
		return getStep(index).getY();
	}
	
	/**
	 * Append a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void appendStep(int x, int y) {
		steps.add(new Step(x,y));
	}

	/**
	 * Prepend a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void prependStep(int x, int y) {
		steps.add(0, new Step(x, y));
	}
	
	/**
	 * Check if this path contains the given step
	 * 
	 * @param x The x coordinate of the step to check for
	 * @param y The y coordinate of the step to check for
	 * @return True if the path contains the given step
	 */
	public boolean contains(int x, int y) {
		return steps.contains(new Step(x,y));
	}
	
	
	
	
	
}
