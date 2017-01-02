/**
 * 
 */
package com.snapgames.framework.event;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.snapgames.framework.event.Event.EventStatus;
import com.snapgames.framework.state.GameState;

/**
 * @author frederic
 *
 */
public class EventManager {

	/**
	 * registered Events
	 */
	Map<String, Event> events;

	/**
	 * the event count to trigger specific processing.
	 */
	protected static int eventCount = 0;

	/**
	 * 
	 */
	public EventManager() {
		events = new HashMap<>();
	}

	/**
	 * Process the events stack.
	 * 
	 * @param state
	 */
	public void process(GameState state) {
		for (Entry<String, Event> event : events.entrySet()) {
			if (event.getValue().status == EventStatus.EXECUTING) {
				event.getValue().run(state);
			}
		}
	}

	/**
	 * register a new Event to the event processor.
	 * 
	 * @param event
	 */
	public void register(Event event) {
		event.setEventManaget(this);
		events.put(event.getEventCode(), event);
	}

	/**
	 * unregister an event.
	 * 
	 * @param event
	 */
	public void unregister(Event event) {
		events.remove(event.getEventCode());
	}

	/**
	 * remove all events from stack.
	 */
	public void flush() {
		events.clear();
	}

	/**
	 * Reset all Events from event list to BEFORE status.
	 */
	public void resetEvents() {
		if (events != null && !events.isEmpty()) {
			for (Entry<String, Event> event : events.entrySet()) {
				event.getValue().status = EventStatus.BEFORE;
			}
		}
	}

	/**
	 * unregister the event on its eventCode.
	 * 
	 * @param eventCode
	 */
	public void unregister(String eventCode) {
		events.remove(eventCode);
	}

	/**
	 * Activate an event
	 * 
	 * @param eventCode
	 */
	public void activate(String eventCode) {
		events.get(eventCode).status = EventStatus.EXECUTING;
	}
	
	public boolean isActive(String eventCode){
		return events.get(eventCode).status == EventStatus.EXECUTING;
	}

	/**
	 * Reset Count event.
	 */
	public void resetCount() {
		eventCount = 0;

	}

	/**
	 * Draw any Event that need to be drawn.
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g) {
		for (Entry<String, Event> entry : events.entrySet()) {
			if (entry.getValue().status == EventStatus.EXECUTING) {
				entry.getValue().draw(g);
			}
		}

	}

	/**
	 * Set count to a predefined value.
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		eventCount = count;
	}
}
