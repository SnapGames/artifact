package com.neet.framework.entity;

import java.awt.Graphics2D;

public interface GameObject {

	public default void init() {
		
	}
	public abstract void reset();

	public abstract void update(long delay);

	public abstract void draw(Graphics2D g);

}