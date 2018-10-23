 package snake_main_package;

import java.awt.Point;
import java.util.ArrayList;

public class Snake extends Entity {
	private int snakeScale = 20;
	private Point head;
	private int snakeSpeed = 10;
	private int tailLength = 0;
	private int direction = DOWN;
	private ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	public Snake() {
		head = new Point(20, 20);
		snakeParts.add(head);
		tailLength = 1;
		System.out.println("Snake() - constructor");
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public ArrayList<Point> getSnakeParts() {
		return snakeParts;
	}

	public int getDirection() {
		return direction;
	}

	public int getScale() {
		return snakeScale;
	}

	public int getSpeed() {
		return snakeSpeed;
	}

	public int getTailLength() {
		return tailLength;
	}

	public void incrementTailLengthBy(int increment) {
		tailLength += increment;
	}

	public void snakeStart() {
		snakeParts.clear();
		head = new Point(20, 20);
		getSnakeParts().add(head);
		tailLength = 1;
		direction = DOWN;
		Game.setGameOver(false);
	}

	public boolean noTailAt(int x, int y) {
		for (Point point : snakeParts) {
			if (point.equals(new Point(x, y))) {
				System.out.println("COLLISION!");
				return false;
			}
		}
		return true;
	}

	public Point getHead() {
		return head;
	}

	public void setHeadLocation(int x, int y) {
		head.setLocation(new Point(x, y));
	}
}
