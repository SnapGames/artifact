package com.snapgames.framework.handler;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * this class contains a boolean array of current and previous key state for the
 * 10 keys that are used for this game. a key k is down when keyState[k] is
 * true.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class InputHandler {

	public static final int NUM_KEYS = 16;

	private static boolean keyState[] = new boolean[NUM_KEYS];
	private static boolean prevKeyState[] = new boolean[NUM_KEYS];

	/**
	 * List of available Keys for input handler.
	 * 
	 * @author frederic.delorme@web-context.com
	 *
	 */
	public enum KeyCode {
		UP(0),
		LEFT(1),
		DOWN(2),
		RIGHT(3),
		BUTTON1(4),
		BUTTON2(5),
		BUTTON3(6),
		BUTTON4(7),
		ENTER(8),
		ESCAPE(9),
		SCREENSHOT(10),
		RECORD(11);

		/**
		 * Internal key code.
		 */
		private int keyCode;

		/**
		 * private constructor to initialize specific value
		 * 
		 * @param i
		 */
		private KeyCode(int i) {
			keyCode = i;
		}

		public int code() {
			return keyCode;
		}
	}

	/**
	 * Internal key mapping for the game.
	 */
	private static Map<KeyCode, Integer> keyMapping;

	/**
	 * initializing default mapping.
	 */
	static {
		keyMapping = new HashMap<>();
		keyMapping.put(KeyCode.BUTTON1, KeyEvent.VK_W);
		keyMapping.put(KeyCode.BUTTON2, KeyEvent.VK_X);
		keyMapping.put(KeyCode.BUTTON3, KeyEvent.VK_C);
		keyMapping.put(KeyCode.BUTTON4, KeyEvent.VK_F);
		keyMapping.put(KeyCode.UP, KeyEvent.VK_UP);
		keyMapping.put(KeyCode.DOWN, KeyEvent.VK_DOWN);
		keyMapping.put(KeyCode.LEFT, KeyEvent.VK_LEFT);
		keyMapping.put(KeyCode.RIGHT, KeyEvent.VK_RIGHT);
		keyMapping.put(KeyCode.ENTER, KeyEvent.VK_ENTER);
		keyMapping.put(KeyCode.ESCAPE, KeyEvent.VK_ESCAPE);
		keyMapping.put(KeyCode.SCREENSHOT, KeyEvent.VK_S);
		keyMapping.put(KeyCode.RECORD, KeyEvent.VK_R);
	}

	/**
	 * Default constructor .
	 */
	public InputHandler() {
	}

	/**
	 * Verify Keys.
	 * 
	 * @param i
	 * @param b
	 */
	public static void keySet(int i, boolean b) {

		for (Entry<KeyCode, Integer> entry : keyMapping.entrySet()) {
			if (i == entry.getValue()) {
				keyState[entry.getKey().code()] = b;
			}
		}
	}

	/**
	 * update key states
	 */
	public static void update(long delta) {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}

	/**
	 * is specific <code>key</code> is pressed ?
	 * 
	 * @param key
	 *            key number to verirfy.
	 * @return
	 */
	public static boolean isPressed(KeyCode key) {
		return keyState[key.code()] && !prevKeyState[key.code()];
	}

	/**
	 * verify if any key was pressed.
	 * 
	 * @return
	 */
	public static boolean anyKeyPress() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i])
				return true;
		}
		return false;
	}

	/**
	 * Retrieve <code>key</code> state
	 * 
	 * @param key
	 *            a KeyCode to retrieve state for.
	 * @return
	 */
	public static boolean getKeyState(KeyCode key) {
		return keyState[key.code()];
	}

	public static boolean getPreviousState(KeyCode key) {
		return prevKeyState[key.code()];
	}

}
