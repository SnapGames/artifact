package com.neet.framework.gfx.tilemap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.neet.framework.GamePanel;
import com.neet.framework.entity.GameObject;

/**
 * TileMap graphical object to render all background graphics based on simple
 * {@link Tile}.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class TileMap implements GameObject {

	// position
	private double x;
	private double y;

	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	private double tween;

	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	// effects
	private boolean shaking;
	private int intensity;

	/**
	 * Default constructor.
	 * 
	 * @param tileSize
	 */
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
	}

	/**
	 * Loading Tiles information from file s
	 * 
	 * @param tileFilename
	 *            the graphical file containing the tiles elements.
	 */
	public void loadTiles(String tileFilename) {

		try {

			tileset = ImageIO
					.read(getClass().getResourceAsStream(tileFilename));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize,
						tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col * tileSize, tileSize,
						tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load Map based on tiles from file s.
	 * 
	 * @param tileMapFilename
	 *            the file containing all details for this tilemap.
	 */
	public void loadMap(String tileMapFilename) {

		try {

			InputStream in = getClass().getResourceAsStream(tileMapFilename);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;

			String delims = "\\s+";
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * what is the type of the Tile at position(row,col)
	 * 
	 * @param row
	 *            row in the map
	 * @param col
	 *            column in the map
	 * @return return type of the Tile at (row,col).
	 */
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}

	public boolean isShaking() {
		return shaking;
	}

	public void setTween(double d) {
		tween = d;
	}

	public void setShaking(boolean b, int i) {
		shaking = b;
		intensity = i;
	}

	public void setBounds(int i1, int i2, int i3, int i4) {
		xmin = GamePanel.WIDTH - i1;
		ymin = GamePanel.WIDTH - i2;
		xmax = i3;
		ymax = i4;
	}

	public void setPosition(double x, double y) {

		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;

		fixBounds();

		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.y / tileSize;

	}

	public void fixBounds() {
		if (x < xmin)
			x = xmin;
		if (y < ymin)
			y = ymin;
		if (x > xmax)
			x = xmax;
		if (y > ymax)
			y = ymax;
	}

	/**
	 * update the tilemap. here mainly position of the tilemap.
	 */
	public void update(long delta) {
		if (shaking) {
			this.x += Math.random() * intensity - intensity / 2;
			this.y += Math.random() * intensity - intensity / 2;
		}
	}

	/**
	 * Draw the tilemap.
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g) {

		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

			if (row >= numRows)
				break;

			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {

				if (col >= numCols)
					break;
				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize,
						(int) y + row * tileSize, null);

			}

		}

	}

	public int getTileSize() {
		return tileSize;
	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	/*
	 * (non-Javadoc)
	 * @see com.neet.framework.entity.GameObject#init()
	 */
	@Override
	public void init() {
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 0.07;
	}

	/*
	 * (non-Javadoc)
	 * @see com.neet.framework.entity.GameObject#reset()
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
