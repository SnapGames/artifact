package com.neet.GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.neet.Audio.JukeBox;
import com.neet.Entity.Enemy;
import com.neet.Entity.EnemyProjectile;
import com.neet.Entity.EnergyParticle;
import com.neet.Entity.Explosion;
import com.neet.Entity.HUD;
import com.neet.Entity.Player;
import com.neet.Entity.PlayerSave;
import com.neet.Entity.Teleport;
import com.neet.Entity.Title;
import com.neet.Entity.Enemies.Gazer;
import com.neet.Entity.Enemies.GelPop;
import com.neet.TileMap.Background;
import com.neet.TileMap.TileMap;

/**
 * THe first discovereing level , to learn how to play !
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class Level1AState extends LevelGameState {

	private Background sky;
	private Background clouds;
	private Background mountains;

	public Level1AState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {
		// backgrounds
		sky = new Background("/Backgrounds/sky.gif", 0);
		clouds = new Background("/Backgrounds/clouds.gif", 0.1);
		mountains = new Background("/Backgrounds/mountains.gif", 0.2);

		// tilemap
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/ruinstileset.gif");
		tileMap.loadMap("/Maps/level1a.map");
		tileMap.setPosition(140, 0);
		tileMap.setBounds(tileMap.getWidth() - 1 * tileMap.getTileSize(),
				tileMap.getHeight() - 2 * tileMap.getTileSize(), 0, 0);
		tileMap.setTween(1);

		// player
		player = new Player(tileMap);
		player.setPosition(300, 161);
		player.setHealth(PlayerSave.getHealth());
		player.setLives(PlayerSave.getLives());
		player.setTime(PlayerSave.getTime());

		// enemies
		enemies = new ArrayList<Enemy>();
		eprojectiles = new ArrayList<EnemyProjectile>();
		populateEnemies();

		// energy particle
		energyParticles = new ArrayList<EnergyParticle>();

		// init player
		player.init(enemies, energyParticles);

		// explosions
		explosions = new ArrayList<Explosion>();

		// hud
		hud = new HUD(player);

		// title and subtitle
		try {
			hageonText = ImageIO.read(getClass().getResourceAsStream(
					"/HUD/HageonTemple.gif"));
			title = new Title(hageonText.getSubimage(0, 0, 178, 20));
			title.sety(60);
			subtitle = new Title(hageonText.getSubimage(0, 20, 82, 13));
			subtitle.sety(85);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(3700, 131);

		// start event
		eventStart = true;
		tb = new ArrayList<Rectangle>();
		eventStart();

		// sfx
		JukeBox.load("/SFX/teleport.mp3", "teleport");
		JukeBox.load("/SFX/explode.mp3", "explode");
		JukeBox.load("/SFX/enemyhit.mp3", "enemyhit");

		// music
		JukeBox.load("/Music/level1.mp3", "level1");
		JukeBox.loop("level1", 600, JukeBox.getFrames("level1") - 2200);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.LevelGameState#populateEnemies()
	 */
	protected void populateEnemies() {
		enemies.clear();
		/*
		 * Tengu t = new Tengu(tileMap, player, enemies); t.setPosition(1300,
		 * 100); enemies.add(t); t = new Tengu(tileMap, player, enemies);
		 * t.setPosition(1330, 100); enemies.add(t); t = new Tengu(tileMap,
		 * player, enemies); t.setPosition(1360, 100); enemies.add(t);
		 */
		GelPop gp;
		Gazer g;

		gp = new GelPop(tileMap, player);
		gp.setPosition(1300, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1320, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1340, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1660, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1680, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1700, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2177, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2960, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2980, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(3000, 100);
		enemies.add(gp);

		g = new Gazer(tileMap);
		g.setPosition(2600, 100);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(3500, 100);
		enemies.add(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.LevelGameState#update()
	 */
	public void update(long delay) {
		super.update(delay);

		// move backgrounds
		clouds.setPosition(tileMap.getx(), tileMap.gety());
		mountains.setPosition(tileMap.getx(), tileMap.gety());

	}

	public void draw(Graphics2D g) {

		// draw background
		sky.draw(g);
		clouds.draw(g);
		mountains.draw(g);

		// draw tilemap
		tileMap.draw(g);

		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		// draw enemy projectiles
		for (int i = 0; i < eprojectiles.size(); i++) {
			eprojectiles.get(i).draw(g);
		}

		// draw explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g);
		}

		// draw player
		player.draw(g);

		// draw teleport
		teleport.draw(g);

		// draw hud
		hud.draw(g);

		// draw title
		if (title != null)
			title.draw(g);
		if (subtitle != null)
			subtitle.draw(g);

		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for (int i = 0; i < tb.size(); i++) {
			g.fill(tb.get(i));
		}

	}

	// /////////////////////////////////////////////////////
	// ////////////////// EVENTS
	// /////////////////////////////////////////////////////

	// reset level
	public void reset() {
		player.reset();
		player.setPosition(300, 161);
		populateEnemies();
		blockInput = true;
		eventCount = 0;
		tileMap.setShaking(false, 0);
		eventStart = true;
		eventStart();
		title = new Title(hageonText.getSubimage(0, 0, 178, 20));
		title.sety(60);
		subtitle = new Title(hageonText.getSubimage(0, 33, 91, 13));
		subtitle.sety(85);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.LevelGameState#getNextLevel()
	 */
	protected int getNextLevel() {
		return GameStateManager.LEVEL1BSTATE;
	}

}