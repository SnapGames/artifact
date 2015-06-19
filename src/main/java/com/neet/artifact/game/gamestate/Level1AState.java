package com.neet.artifact.game.gamestate;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.neet.artifact.game.entity.EnemyProjectile;
import com.neet.artifact.game.entity.EnergyParticle;
import com.neet.artifact.game.entity.Explosion;
import com.neet.artifact.game.entity.HUD;
import com.neet.artifact.game.entity.Player;
import com.neet.artifact.game.entity.PlayerSave;
import com.neet.artifact.game.entity.Teleport;
import com.neet.artifact.game.entity.Title;
import com.neet.artifact.game.entity.enemies.Gazer;
import com.neet.artifact.game.entity.enemies.GelPop;
import com.neet.artifact.game.events.EventDead;
import com.neet.artifact.game.events.EventFinish;
import com.neet.artifact.game.events.EventStart;
import com.webcontext.game.framework.audio.JukeBox;
import com.webcontext.game.framework.entity.Enemy;
import com.webcontext.game.framework.gfx.Background;
import com.webcontext.game.framework.gfx.tilemap.TileMap;
import com.webcontext.game.framework.state.GameStateManager;
import com.webcontext.game.framework.state.LevelGameState;

/**
 * THe first discovering level , to learn how to play !
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class Level1AState extends LevelGameState {

	private Background sky;
	private Background clouds;
	private Background mountains;

	/**
	 * Level Constructor.
	 * 
	 * @param gsm
	 */
	public Level1AState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.LevelGameState#registerEvents()
	 */
	public void registerEvents() {
		eventManager.register(new EventStart());
		eventManager.register(new EventDead());
		eventManager.register(new EventFinish());
	}

	public void init() {
		// backgrounds
		sky = new Background("/Backgrounds/sky.gif", 0);
		clouds = new Background("/Backgrounds/clouds.gif", 0.1);
		mountains = new Background("/Backgrounds/mountains.gif", 0.2);

		gameObjects.put("sky", sky);
		gameObjects.put("clouds", clouds);
		gameObjects.put("mountains", mountains);

		// tilemap
		tileMap = new TileMap(30);
		tileMap.init();
		tileMap.loadTiles("/Tilesets/ruinstileset.gif");
		tileMap.loadMap("/Maps/level1a.map");
		tileMap.setPosition(140, 0);
		tileMap.setBounds(tileMap.getWidth() - 1 * tileMap.getTileSize(),
				tileMap.getHeight() - 2 * tileMap.getTileSize(), 0, 0);
		tileMap.setTween(1);

		gameObjects.put("tilemap", tileMap);

		// player
		player = new Player(tileMap);
		player.setPosition(300, 161);
		player.setHealth(PlayerSave.getHealth());
		player.setLives(PlayerSave.getLives());
		player.setTime(PlayerSave.getTime());

		gameObjects.put("player", player);

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

		gameObjects.put("hud", hud);

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

		gameObjects.put("title", title);

		gameObjects.put("subtitle", subtitle);

		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(3700, 131);

		// start event
		eventManager.activate("EventStart");
		eventManager.process(this);

		// sfx
		JukeBox.load("/SFX/teleport.mp3", "teleport");
		JukeBox.load("/SFX/explode.mp3", "explode");
		JukeBox.load("/SFX/enemyhit.mp3", "enemyhit");

		// music
		JukeBox.load("/Music/level1.mp3", "level1");
		JukeBox.loop("level1", 600, JukeBox.getFrames("level1") - 2200);
		attributes.put("music.clip", "level1");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#populateEnemies()
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
	 * @see com.neet.artifact.gamestate.LevelGameState#update()
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

		eventManager.draw(g);

	}

	// /////////////////////////////////////////////////////
	// ////////////////// EVENTS
	// /////////////////////////////////////////////////////

	// reset level
	public void reset() {
		super.reset();
		player.reset();
		player.setPosition(300, 161);
		populateEnemies();
		attributes.put("blockInput", false);
		tileMap.setShaking(false, 0);

		eventManager.resetCount();
		eventManager.activate("EventStart");
		eventManager.process(this);

		title = new Title(hageonText.getSubimage(0, 0, 178, 20));
		title.sety(60);

		subtitle = new Title(hageonText.getSubimage(0, 33, 91, 13));
		subtitle.sety(85);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#getNextLevel()
	 */
	protected String getNextLevel() {
		return ArtifactGameStateManager.LEVEL1BSTATE;
	}

}