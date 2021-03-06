package com.neet.artifact.game.gamestate;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.neet.artifact.game.entity.EnergyParticle;
import com.neet.artifact.game.entity.Explosion;
import com.neet.artifact.game.entity.HUD;
import com.neet.artifact.game.entity.Player;
import com.neet.artifact.game.entity.PlayerSave;
import com.neet.artifact.game.entity.Portal;
import com.neet.artifact.game.entity.Spirit;
import com.neet.artifact.game.entity.artfact.BottomLeftPiece;
import com.neet.artifact.game.entity.artfact.BottomRightPiece;
import com.neet.artifact.game.entity.artfact.TopLeftPiece;
import com.neet.artifact.game.entity.artfact.TopRightPiece;
import com.neet.artifact.game.entity.enemies.DarkEnergy;
import com.neet.artifact.game.events.EventDead;
import com.neet.artifact.game.events.EventEndGame;
import com.neet.artifact.game.events.EventFinish;
import com.neet.artifact.game.events.EventStart;
import com.snapgames.framework.GamePanel;
import com.snapgames.framework.audio.JukeBox;
import com.snapgames.framework.entity.Enemy;
import com.snapgames.framework.gfx.Background;
import com.snapgames.framework.gfx.tilemap.TileMap;
import com.snapgames.framework.state.impl.GameLevelState;
import com.snapgames.framework.state.impl.GameStateManager;

/**
 * The final level where you fight against the boss !
 *
 * @author ForeignGuyMike
 * @author Frédéric Delorme
 */
public class Level1CState extends GameLevelState {

    private Background temple;
    private TopLeftPiece tlp;
    private TopRightPiece trp;
    private BottomLeftPiece blp;
    private BottomRightPiece brp;
    private Portal portal;
    private Spirit spirit;
    // events
    private boolean eventPortal;
    private boolean flash;
    private boolean eventBossDead;

    /**
     * This is the Boss Level !
     *
     * @param gsm
     */
    public Level1CState(GameStateManager gsm) {
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
        eventManager.register(new EventEndGame());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.GameState#init()
     */
    public void init() {

        // backgrounds
        temple = new Background("/Backgrounds/temple.gif", 0.5, 0);
        gameObjects.put("temple", temple);

        // tilemap
        tileMap = new TileMap(30);
        tileMap.init();
        tileMap.loadTiles("/Tilesets/ruinstileset.gif");
        tileMap.loadMap("/Maps/level1c.map");
        tileMap.setPosition(140, 0);
        tileMap.setTween(1);
        gameObjects.put("tilemap", tileMap);

        // player
        player = new Player(tileMap);
        player.setPosition(50, 190);
        player.setHealth(PlayerSave.getHealth());
        player.setLives(PlayerSave.getLives());
        player.setTime(PlayerSave.getTime());

        gameObjects.put("player", player);

        // explosions
        explosions = new ArrayList<Explosion>();

        // enemies
        enemies = new ArrayList<Enemy>();
        populateEnemies();

        // energy particle
        energyParticles = new ArrayList<EnergyParticle>();

        // init player
        player.init(enemies, energyParticles);

        // hud
        hud = new HUD(player);

        gameObjects.put("hud", hud);

        // portal
        portal = new Portal(tileMap);
        portal.setPosition(160, 154);

        gameObjects.put("portal", portal);

        // artifact
        tlp = new TopLeftPiece(tileMap);
        trp = new TopRightPiece(tileMap);
        blp = new BottomLeftPiece(tileMap);
        brp = new BottomRightPiece(tileMap);
        tlp.setPosition(152, 102);
        trp.setPosition(162, 102);
        blp.setPosition(152, 112);
        brp.setPosition(162, 112);

        // start event
        eventManager.activate("EventStart");
        eventManager.process(this);

        // sfx
        JukeBox.load("/SFX/teleport.mp3", "teleport");
        JukeBox.load("/SFX/explode.mp3", "explode");
        JukeBox.load("/SFX/enemyhit.mp3", "enemyhit");

        // music
        JukeBox.load("/Music/level1boss.mp3", "level1boss");
        JukeBox.load("/Music/fanfare.mp3", "fanfare");

    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.LevelGameState#populateEnemies()
     */
    public void populateEnemies() {
        enemies.clear();
        spirit = new Spirit(tileMap, player, enemies, explosions);
        spirit.setPosition(-9000, 9000);
        enemies.add(spirit);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.LevelGameState#update()
     */
    public void update(long delta) {
        super.update(delta);
        // check if boss dead event should start
        if (!eventManager.isActive("EventFinish") && spirit.isDead()) {
            eventBossDead = true;
            attributes.put("blockInput", true);
        }

        if (eventPortal)
            eventPortal();
        if (eventBossDead)
            eventBossDead();

        // update artifacts
        tlp.update(delta);
        trp.update(delta);
        blp.update(delta);
        brp.update(delta);

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

        // draw artifact
        tlp.draw(g);
        trp.draw(g);
        blp.draw(g);
        brp.draw(g);

        // draw transition boxes
        g.setColor(java.awt.Color.BLACK);
        for (int i = 0; i < tb.size(); i++) {
            g.fill(tb.get(i));
        }

        // flash
        if (flash) {
            g.setColor(java.awt.Color.WHITE);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.LevelGameState#reset()
     */
    public void reset() {
        super.reset();
        player.setPosition(50, 190);
        populateEnemies();
        eventManager.activate("EventStart");
        eventManager.resetCount();
        eventCount = 0;
        eventManager.process(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.framework.state.GameState#nextState()
     */
    @Override
    public String nextState() {
        return ArtifactGameStateManager.ACIDSTATE;
    }

    /**
     * Event Portal processing !
     */
    private void eventPortal() {
        eventCount++;
        if (eventCount == 1) {
            if (portal.isOpened()) {
                eventCount = 360;
                eventManager.setCount(360);
            }
        }
        if (eventCount > 60 && eventCount < 180) {
            energyParticles.add(new EnergyParticle(tileMap, 157, 107,
                    (int) (Math.random() * 4)));
        }
        if (eventCount >= 160 && eventCount <= 180) {
            if (eventCount % 4 == 0 || eventCount % 4 == 1)
                flash = true;
            else
                flash = false;
        }
        if (eventCount == 181) {
            tileMap.setShaking(false, 0);
            flash = false;
            tlp.setVector(-0.3, -0.3);
            trp.setVector(0.3, -0.3);
            blp.setVector(-0.3, 0.3);
            brp.setVector(0.3, 0.3);
            player.setEmote(Player.SURPRISED);
        }
        if (eventCount == 240) {
            tlp.setVector(0, -5);
            trp.setVector(0, -5);
            blp.setVector(0, -5);
            brp.setVector(0, -5);
        }
        if (eventCount == 300) {
            player.setEmote(Player.NONE);
            portal.setOpening();
        }
        if (eventCount == 360) {
            flash = true;
            spirit.setPosition(160, 160);
            DarkEnergy de;
            for (int i = 0; i < 20; i++) {
                de = new DarkEnergy(tileMap);
                de.setPosition(160, 160);
                de.setVector(Math.random() * 10 - 5, Math.random() * -2 - 3);
                enemies.add(de);
            }
        }
        if (eventCount == 362) {
            flash = false;
            JukeBox.loop("level1boss", 0, 60000,
                    JukeBox.getFrames("level1boss") - 4000);
        }
        if (eventCount == 420) {
            eventPortal = false;
            attributes.put("blockInput", false);
            eventManager.resetCount();
            spirit.setActive();
        }

    }

    public void eventBossDead() {
        eventCount++;
        if (eventCount == 1) {
            player.stop();
            JukeBox.stop("level1boss");
            enemies.clear();
        }
        if (eventCount <= 120 && eventCount % 15 == 0) {
            explosions
                    .add(new Explosion(tileMap, spirit.getx(), spirit.gety()));
            JukeBox.play("explode");
        }
        if (eventCount == 180) {
            JukeBox.play("fanfare");
        }
        if (eventCount == 390) {
            eventBossDead = false;
            eventCount = 0;
            eventManager.resetCount();
            eventManager.activate("EventFinish");
        }
    }

}