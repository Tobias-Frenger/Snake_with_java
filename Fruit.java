package snake_main_package;

import java.awt.Point;
import java.util.Random;

public abstract class Fruit extends Entity implements FruitInterface {
	private Point fruit;
	private Random random;

	public Fruit() {
		random = new Random();
		fruit = new Point(1 + random.nextInt(38), 5 + random.nextInt(34));
	}

	public Point fruit() {
		return fruit;
	}

}