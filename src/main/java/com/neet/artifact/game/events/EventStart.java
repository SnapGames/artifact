/**
 * 
 */
package com.neet.artifact.game.events;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.neet.artifact.game.entity.Title;
import com.snapgames.framework.GamePanel;
import com.snapgames.framework.event.Event;
import com.snapgames.framework.state.GameState;

/**
 * Start event processing Title and subtitle display.
 * 
 * @author Frédéric Delorme
 *
 */
public class EventStart extends Event {

	protected ArrayList<Rectangle> tb;

	/**
	 * 
	 */
	public EventStart() {
		this.eventCode = "EventStart";
		tb = new ArrayList<>();
	}

	@Override
	public void run(GameState state) {
		eventCount++;
		if (eventCount == 1) {
			tb.clear();
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
			tb.add(new Rectangle(0, GamePanel.HEIGHT / 2, GamePanel.WIDTH,
					GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2,
					GamePanel.HEIGHT));
		}
		if (eventCount > 1 && eventCount < 60) {
			tb.get(0).height -= 4;
			tb.get(1).width -= 6;
			tb.get(2).y += 4;
			tb.get(3).x += 6;
		}
		if (eventCount == 30) {
			((Title) state.getGameObject("title")).begin();
		}
		if (eventCount == 60) {
			state.setAttribute("blockInput", false);
			eventCount = 0;
			((Title) state.getGameObject("subtitle")).begin();
			tb.clear();
			this.status = EventStatus.DONE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.event.Event#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for (int i = 0; i < tb.size(); i++) {
			g.fill(tb.get(i));
		}
	}

}
