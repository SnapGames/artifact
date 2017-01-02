/**
 * 
 */
package com.snapgames.framework.tools;

import java.util.Random;

/**
 * Generate a random value from an enum.
 * 
 * @author Frédéric Delorme
 *
 */
public class EnumRandomizer {

	/**
	 * Random generator.
	 */
	private static Random random = new Random();

	/**
	 * Generate a default value for this enum.
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T extends Enum<?>> T random(Class<T> clazz) {
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}
}
