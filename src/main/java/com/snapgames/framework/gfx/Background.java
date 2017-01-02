package com.snapgames.framework.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.snapgames.framework.GamePanel;

/**
 * Background image object
 * 
 * @author Frédéric Delorme
 *
 */
public class Background extends GraphicObject {

	private BufferedImage image;

	private String filename;

	/** position **/
	private double x, y;
	/** speed **/
	private double dx, dy;
	/** size **/
	private int width, height;
	/** scale **/
	private double xscale, yscale;

	/**
	 * default contrsuctor.
	 */
	public Background() {
		this.filename = "";
		this.xscale = this.yscale = 0.1;
	}

	/**
	 * Load background from image s file.
	 * 
	 * @param backgroundFilename
	 *            name for the file to be loaded.
	 */
	public Background(String backgroundFilename) {
		this(backgroundFilename, 0.1);
	}

	/**
	 * Load image from backgroundFilename image with scale a preset.
	 * 
	 * @param backgroundFilename
	 *            name for the file to be loaded.
	 * @param scale
	 *            the scale to fit the image on screen.
	 */
	public Background(String backgroundFilename, double scale) {
		this(backgroundFilename, scale, scale);
	}

	/**
	 * Load image from backgroundFilename image with scale set to
	 * (xScale,yScale).
	 * 
	 * @param backgroundFilename
	 *            name for the file to be loaded.
	 * @param xScale
	 *            the X axis scale
	 * @param yScale
	 *            the Y axis scale.
	 */
	public Background(String backgroundImage, double xScale, double yScale) {
		try {
			filename = backgroundImage;
			image = ImageIO.read(getClass().getResourceAsStream(filename));
			width = image.getWidth();
			height = image.getHeight();
			xscale = xScale;
			yscale = yScale;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * load <code>backgroundImage</code> file as a background image with slicing
	 * from (<code>x,y</code>) position with size (<code>w,h</code>) and set the
	 * default <code>scale</code>.
	 * 
	 * @param backgroundFilename
	 *            name for the file to be loaded. name for the file to be
	 *            loaded.
	 * @param scale
	 *            the scale to fit the image on screen.
	 * @param x
	 *            x axis position to slice from
	 * @param y
	 *            y position to slice from
	 * @param w
	 *            width to be sliced
	 * @param h
	 *            height to be sliced.
	 */
	public Background(String backgroundImage, double scale, int x, int y, int w, int h) {
		try {
			filename = backgroundImage;
			image = ImageIO.read(getClass().getResourceAsStream(filename));
			image = image.getSubimage(x, y, w, h);
			width = image.getWidth();
			height = image.getHeight();
			xscale = scale;
			yscale = scale;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Move background image object to the (x,y) position.
	 * 
	 * @param x
	 *            position on X axis
	 * @param y
	 *            position on Y axis
	 */
	public void setPosition(double x, double y) {
		this.x = (x * xscale) % width;
		this.y = (y * yscale) % height;
	}

	/**
	 * Set the speed of background image to (dx,dy).
	 * 
	 * @param dx
	 *            speed on X axis.
	 * @param dy
	 *            speed on Y axis.
	 */
	public void setSpeed(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	/**
	 * Set Scale of background image to (xScale,yScale)
	 * 
	 * @param xScale
	 *            the X axis scale
	 * @param yScale
	 *            the Y axis scale.
	 */
	public void setScale(double xScale, double yScale) {
		this.xscale = xScale;
		this.yscale = yScale;
	}

	/**
	 * Set background image to (imgWith, imgHeight).
	 * 
	 * @param imgWidth
	 *            width of the background image.
	 * @param imgHeight
	 *            height of the background image.
	 */
	public void setDimensions(int imgWidth, int imgHeight) {
		width = imgWidth;
		height = imgHeight;
	}

	/**
	 * retrieve X position.
	 * 
	 * @return
	 */
	public double getx() {
		return x;
	}

	/**
	 * retrieve Y position.
	 * 
	 * @return
	 */
	public double gety() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.snapgames.framework.entity.GameObject#update(long)
	 */
	public void update(long delta) {
		x += dx;
		while (x <= -width)
			x += width;
		while (x >= width)
			x -= width;
		y += dy;
		while (y <= -height)
			y += height;
		while (y >= height)
			y -= height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.snapgames.framework.entity.GameObject#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {

		g.drawImage(image, (int) x, (int) y, null);

		if (x < 0) {
			g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);
		}
		if (x > 0) {
			g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
		}
		if (y < 0) {
			g.drawImage(image, (int) x, (int) y + GamePanel.HEIGHT, null);
		}
		if (y > 0) {
			g.drawImage(image, (int) x, (int) y - GamePanel.HEIGHT, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.snapgames.framework.entity.GameObject#reset()
	 */
	public void reset() {

	}

}
