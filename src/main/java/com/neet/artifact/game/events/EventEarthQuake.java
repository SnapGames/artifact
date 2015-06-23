/**
 *
 */
package com.neet.artifact.game.events;

import com.neet.artifact.game.entity.Player;
import com.webcontext.game.framework.event.Event;
import com.webcontext.game.framework.gfx.tilemap.TileMap;
import com.webcontext.game.framework.state.GameState;

/**
 * Generate earth quake.
 *
 * @author Frédéric Delorme
 */
public class EventEarthQuake extends Event {

    /**
     *
     */
    public EventEarthQuake() {
        this.eventCode = "EventEarthQuake";
    }

    /**
     * manage the new Earthquake event.
     */
    @Override
    public void run(GameState state) {
        eventCount++;

        Player player = (Player) (state.getGameObject("player"));
        TileMap tileMap = (TileMap) (state.getGameObject("tilemap"));

        if (eventCount == 1) {
            player.stop();
            player.setPosition(2175, player.gety());
        }
        if (eventCount == 60) {
            player.setEmote(Player.CONFUSED);
        }
        if (eventCount == 120)
            player.setEmote(Player.NONE);
        if (eventCount == 150)
            tileMap.setShaking(true, 10);
        if (eventCount == 180)
            player.setEmote(Player.SURPRISED);
        if (eventCount == 300) {
            player.setEmote(Player.NONE);
            this.status = EventStatus.DONE;
            state.setAttribute("blockingState", false);
            eventManager.resetCount();
        }
    }
}
