package utilities;

import java.util.Arrays;
import java.util.Random;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class Utilities {
	/**
	 * Class constructor
	 */
	private Utilities() {
	}

	public static final double generateRandomNumber() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public static final int generateRandomNumber(int lowerBound, int upperBound) {
		Random random = new Random();
		return random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}

	/**
	 * Finds the position of some letter in the array
	 * 
	 * @param map    used in the game
	 * @param letter to be searched for in the array
	 * @return the position of the letter, if the letter isn't found returns a
	 *         default position
	 */
	public static byte[] findPosition(char[][] map, char letter) {
		for (byte i = 0; i < map.length; i++) {
			for (byte j = 0; j < map[i].length; j++) {
				if (map[i][j] == letter) {
					return new byte[] { i, j };
				}
			}
		}
		return new byte[] { -1, -1 };
	}

	public static char[][] deepCopy(char[][] original) {
		if (original == null) {
			return null;
		}

		char[][] result = new char[original.length][];
		for (int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
			// result[i] = original[i].clone();

			// For Java versions prior to Java 6 use the next:
			// System.arraycopy(original[i], 0, result[i], 0, original[i].length);
		}
		return result;
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	private static int count(char[][] map, char c) {
		int cnt = 0;
		for (byte i = 0; i < map.length; i++) {
			for (byte j = 0; j < map[i].length; j++) {
				if (map[i][j] == c) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static byte[][] findPositions(char[][] map, char c) {
		byte[][] pos = new byte[Utilities.count(map, c)][2];
		int k = 0;
		for (byte i = 0; i < map.length; i++) {
			for (byte j = 0; j < map[i].length; j++) {
				if (map[i][j] == c) {
					pos[k][0] = i;
					pos[k][1] = j;
					k++;
				}
			}
		}
		return pos;
	}

}
