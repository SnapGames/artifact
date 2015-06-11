package com.neet.TileMap;

import java.awt.image.BufferedImage;

/**
 * A Tile element to build great background graphics.
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class Tile {

	private BufferedImage image;
	private int type;

	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;

	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getType() {
		return type;
	}

}
