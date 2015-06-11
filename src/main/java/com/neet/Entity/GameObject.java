package com.neet.Entity;

import java.awt.Graphics2D;

public interface GameObject {

	public abstract void reset();

	public abstract void update(long delay);

	public abstract void draw(Graphics2D g);

}