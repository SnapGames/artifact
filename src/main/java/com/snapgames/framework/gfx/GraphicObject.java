/**
 * 
 */
package com.snapgames.framework.gfx;

import com.snapgames.framework.entity.GameObject;

/**
 * The GraphicObject is a default GameObject with some static and physic
 * characteristics.
 * 
 * @author Frédéric Delorme.
 *
 */
public abstract class GraphicObject implements GameObject {

	// position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	// dimensions
	protected int width;
	protected int height;
}
