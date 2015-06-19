package com.neet.artifact.game.gamestate;

import java.awt.Graphics2D;
import java.util.ArrayList;

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
import com.neet.artifact.game.entity.enemies.Tengu;
import com.neet.artifact.game.events.EventDead;
import com.neet.artifact.game.events.EventFinish;
import com.neet.artifact.game.events.EventStart;
import com.webcontext.game.framework.audio.JukeBox;
import com.webcontext.game.framework.entity.Enemy;
import com.webcontext.game.framework.gfx.Background;
import com.webcontext.game.framework.gfx.tilemap.TileMap;
import com.webcontext.game.framework.state.impl.GameStateManager;
import com.webcontext.game.framework.state.impl.LevelGameState;

/**
 * A second level to discover more things
 *
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 * 
 */
public class Level1BState extends LevelGameState {

	/**
	 * Background temple.
	 */
	Background temple;

	/**
	 * A new event to managed is Earth Quake !
	 */
	boolean eventQuake;

	/**
	 * Level1 constructor.
	 * 
	 * @param gsm
	 */
	public Level1BState(GameStateManager gsm) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#init()
	 */
	public void init() {

		// backgrounds
		temple = new Background("/Backgrounds/temple.gif", 0.5, 0);

		// tilemap
		tileMap = new TileMap(30);
		tileMap.init();
		tileMap.loadTiles("/Tilesets/ruinstileset.gif");
		tileMap.loadMap("/Maps/level1b.map");
		tileMap.setPosition(140, 0);
		tileMap.setTween(1);

		// player
		player = new Player(tileMap);
		player.setPosition(300, 131);
		player.setHealth(PlayerSave.getHealth());
		player.setLives(PlayerSave.getLives());
		player.setTime(PlayerSave.getTime());

		// enemies
		enemies = new ArrayList<Enemy>();
		eprojectiles = new ArrayList<EnemyProjectile>();
		populateEnemies();

		// energy particle
		energyParticles = new ArrayList<EnergyParticle>();

		player.init(enemies, energyParticles);

		// explosions
		explosions = new ArrayList<Explosion>();

		// hud
		hud = new HUD(player);

		// title and subtitle
		try {
			title = new Title(hageonText.getSubimage(0, 0, 178, 20));
			title.sety(60);
			subtitle = new Title(hageonText.getSubimage(0, 33, 91, 13));
			subtitle.sety(85);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gameObjects.put("title", title);
		gameObjects.put("subtitle", subtitle);

		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(2850, 371);

		// start event
		eventManager.activate("EventStart");
		eventManager.process(this);

		// sfx
		JukeBox.load("/SFX/teleport.mp3", "teleport");
		JukeBox.load("/SFX/explode.mp3", "explode");
		JukeBox.load("/SFX/enemyhit.mp3", "enemyhit");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#populateEnemies()
	 */
	public void populateEnemies() {
		enemies.clear();
		GelPop gp;
		Gazer g;
		Tengu t;
		gp = new GelPop(tileMap, player);
		gp.setPosition(750, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(900, 150);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1320, 250);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1570, 160);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1590, 160);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2600, 370);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2620, 370);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2640, 370);
		enemies.add(gp);

		g = new Gazer(tileMap);
		g.setPosition(904, 130);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1080, 270);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1200, 270);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1704, 300);
		enemies.add(g);

		t = new Tengu(tileMap, player, enemies);
		t.setPosition(1900, 580);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2330, 550);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2400, 490);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2457, 430);
		enemies.add(t);

	}

	// /////////////////////////////////////////////////////
	// ////////////////// EVENTS
	// /////////////////////////////////////////////////////

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#reset()
	 */
	public void reset() {
		super.reset();
		player.setPosition(300, 131);
		tileMap.setShaking(false, 0);
		title = new Title(hageonText.getSubimage(0, 0, 178, 20));
		title.sety(60);
		subtitle = new Title(hageonText.getSubimage(0, 33, 91, 13));
		subtitle.sety(85);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#update()
	 */
	public void update(long delay) {
		super.update(delay);
		// check if quake event should start
		if (player.getx() > 2175 && !tileMap.isShaking()) {
			eventManager.activate("EventEarthQuake");
			attributes.put("blockingState", true);
		}

		// move backgrounds
		temple.setPosition(tileMap.getx(), tileMap.gety());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {
		super.draw(g);
		// draw background
		temple.draw(g);
	}

	public String getNextState() {
		return ArtifactGameStateManager.LEVEL1CSTATE;
	}

}