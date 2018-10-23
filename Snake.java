package snake_main_package;

import java.awt.Point;
import java.util.ArrayList;

/**
 * This class contains all the logic behind setting up a snake on the screen
 * 
 * @author a16tobfr
 *
 * @method snakeReStart(KeyInputManager) - used to set the restart conditions
 * @method incrementTailLengthBy(int) - used to increase the tail of the snake
 * @method noTailAt(int, int) - used to check if the snake moves onto it self
 * * * * * getter methods
 * @method getHead() - returns the head of the snake
 * @method getSnakeParts() - returns the snakeParts array list
 * @method getDirection() - returns the current direction
 * @method getScale() - returns the scale that has been set
 * @method getSpeed() - returns the speed that has been set
 * @method getTailLength() - returns the length of the snake tail
 * * * * * setter methods
 * @method setHeadLocation(int,int) - used to set the location of the snake head
 * @method setDirection(int) - used to set the direction that the snake is traveling in
 */
public class Snake extends Entity {
	private Point head;
	private int snakeScale = 20;
	private int snakeSpeed = 10;
	private int tailLength = 0;
	private int direction = DOWN;
	@SuppressWarnings("unused")
	private KeyInputManager keyInputManager;
	private ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	public Snake() {
		head = new Point(20, 20);
		snakeParts.add(head);
		tailLength = 1;
	}

	public void snakeReStart(KeyInputManager keyInputManager) {
		this.keyInputManager = keyInputManager;
		snakeParts.clear();
		head = new Point(20, 20);
		getSnakeParts().add(head);
		tailLength = 1;
		keyInputManager.setMovingDown(false);
		keyInputManager.setMovingUp(false);
		keyInputManager.setMovingLeft(false);
		keyInputManager.setMovingRight(false);
		Game.setGameOver(false);
	}
	
	public void incrementTailLengthBy(int increment) {
		tailLength += increment;
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
	
	public void setHeadLocation(int x, int y) {
		head.setLocation(new Point(x, y));
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
}
