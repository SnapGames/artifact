package com.snapgames.framework.gfx.tilemap;

import java.awt.image.BufferedImage;

/**
 * A Tile element to build great background graphics.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class Tile {
	public enum TileType {
		NORMAL(0),
		BLOCKED(1),
		OBJECT(2);

		private int value=0;

		private TileType(int value) {
			this.setValue(value);
		}

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		private void setValue(int value) {
			this.value = value;
		}
	}

	private BufferedImage image;

	private TileType type;

	public Tile(BufferedImage image, TileType type) {
		this.image = image;
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public TileType getType() {
		return type;
	}

}
