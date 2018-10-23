package snake_main_package;

import java.awt.Point;
import java.util.Random;

public class Apple extends Fruit{
	private int appleEnergy = 10;
	private Point applePosition;
	private Random random;
	
	Apple() {
		random = new Random();
		applePosition = new Point(1 + random.nextInt(38), 5 + random.nextInt(34));
	}
	
	@Override
	public int getEnergy() {
		return appleEnergy;
	}

	@Override
	public Point fruitPosition() {
		return applePosition;
	}

	@Override
	public int getRandomPosition(int value) {
		return random.nextInt(value);
	}

}