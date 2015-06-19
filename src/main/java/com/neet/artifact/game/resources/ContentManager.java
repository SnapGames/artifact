package com.neet.artifact.game.resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * This class loads resources on boot. Spritesheets are taken from here.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class ContentManager {

	public static Map<String, BufferedImage[][]> images = new HashMap<>();

	/**
	 * Load file image as group with Width x height size.
	 * 
	 * @param group
	 * @param file
	 * @param width
	 * @param height
	 */
	public static void loadImageBank(String group, String file, int width,
			int height) {
		images.put(group, load(file, width, height));

	}

	/**
	 * Load file as image buffered by slicing it as tiles of size (width x
	 * height).
	 * 
	 * @param file
	 * @param w
	 * @param h
	 * @return
	 */
	public static BufferedImage[][] load(String file, int w, int h) {
		BufferedImage[][] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(ContentManager.class
					.getResourceAsStream(file));
			int width = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}

	/**
	 * Retrieve an images bank from content on its <code>group</code> name.
	 * 
	 * @param group
	 * @return
	 */
	public static BufferedImage[][] getImageBank(String group) {
		return images.get(group);

	}

	/**
	 * Retrieve the image <code> index </code>from the bank named
	 * <code>group</code>.
	 * 
	 * @param group
	 * @param index
	 * @return
	 */
	public static BufferedImage[] getImageBank(String group, int index) {
		return images.get(group)[index];

	}
}
