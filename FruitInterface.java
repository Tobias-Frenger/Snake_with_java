package snake_main_package;

import java.awt.Point;

/**
 * Is an interface for the fruit class. Defines some methods that will be used
 * by each object that inherits from the fruit class
 * 
 * @author a16tobfr
 *
 */
public interface FruitInterface {
	public int getRandomPositionWithMaxValue(int value);

	public Point fruitPosition();

	public int getEnergy();

}
