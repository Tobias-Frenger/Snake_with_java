package snake_main_package;

import java.awt.Point;
import java.util.Random;

/**
 * Contains the logic behind setting up an apple on the screen
 * 
 * @author a16tobfr
 *
 * @method fruitPosition() - returns a point
 * @method getEnergy() - returns the energy value of the apple
 * @method getRandomPositionWithMaxValue - returns a value between zero and the input value
 */
public class Apple extends Fruit {
	private int appleEnergy = 10;
	private Point applePosition;
	private Random random;

	public Apple() {
		random = new Random();
		applePosition = new Point(1 + random.nextInt(38), 5 + random.nextInt(34));
	}

	public Point fruitPosition() {
		return applePosition;
	}
	
	public int getEnergy() {
		return appleEnergy;
	}

	public int getRandomPositionWithMaxValue(int value) {
		return random.nextInt(value);
	}

}