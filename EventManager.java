package snake_main_package;

import java.awt.Point;
import java.io.IOException;

/**
 * This class detects and reacts when certain events occur
 * 
 * @author a16tobfr
 * 
 * @method sleepTimer(int) - sleeps the thread for the value that gets set
 * 
 * @method eventUpdaterThread() - Defines a thread that is used so we can detect
 *         the events that occur without affecting other parts of the program.
 *         Since the thread will update the movement of the snake we make it so
 *         the speed of the snake affects the sleepTimer that we use in the loop
 * 
 * @method startEventManagerThread() - method used to start the thread
 * 
 * @method gameOverEvent() - This method is used when certain conditions are not
 *         met that results in a game over scenario
 * 
 * @method relocateFruit() - Used to relocate the fruit on the screen
 * 
 * @method snakeAteFruit() - Used to increment the current score and the size of
 *         the snake when the snake has eaten the fruit
 * 
 * @method snakeHeadAtFruit() - boolean method that returns true when the snake
 *         head is at the same location on the board as the fruit.
 * 
 * @method snakeEvents() - used to move the snake if the conditions are met, it
 *         also uses other methods within this class to do different things
 *         depending on the event
 */
public class EventManager {
	private Thread eventThread;
	private Snake snake;
	private Apple apple;
	private Game game;
	private int leftBorder = 0;
	private int rightBorder = 39;
	private int topBorder = 4;
	private int bottomBorder = 39;

	public EventManager(Snake snake, Apple apple, Game game) {
		this.snake = snake;
		this.apple = apple;
		this.game = game;
	}

	public void sleepTimer(int timeInMilliSeconds) {
		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {
			System.out.println("sleepTimer - error");
			e.printStackTrace();
		}
	}

	public void eventUpdaterThread() {
		eventThread = new Thread(new Runnable() {
			public void run() {
				while (game.isRunning() == true) {
					if (game.isPaused() == false && Game.gameIsOver() == false) {
						snakeEvents();
					}
					sleepTimer(1000 / snake.getSpeed());
				}
			}
		});
	}

	public void startEventManagerThread() {
		eventUpdaterThread();
		eventThread.start();
	}

	private void gameOverEvent() {
		try {
			game.getFileManager().writeToFile();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Game.setGameOver(true);
	}

	private void relocateFruit() {
		apple.fruitPosition().setLocation(1 + apple.getRandomPositionWithMaxValue(38),
				5 + apple.getRandomPositionWithMaxValue(34));
	}

	private void snakeAteFruit() {
		snake.incrementTailLengthBy(1);
		game.incrementScore(10);
	}

	private boolean snakeHeadAtFruit() {
		if (snake.getHead().equals(apple.fruitPosition())) {
			return true;
		} else {
			return false;
		}
	}

	private void snakeEvents() {
		snake.getSnakeParts().add(new Point(snake.getHead().x, snake.getHead().y));

		// detects the current direction and moves in that direction
		if (snake.getDirection() == Snake.UP) {
			if (snake.getHead().y - 1 > topBorder && snake.noTailAt(snake.getHead().x, snake.getHead().y - 1)) {
				snake.setHeadLocation(snake.getHead().x, snake.getHead().y -= 1);
			} else {
				gameOverEvent();
			}
		}
		if (snake.getDirection() == Snake.DOWN) {
			if (snake.getHead().y + 1 < bottomBorder && snake.noTailAt(snake.getHead().x, snake.getHead().y + 1)) {
				snake.setHeadLocation(snake.getHead().x, snake.getHead().y += 1);
			} else {
				gameOverEvent();
			}
		}
		if (snake.getDirection() == Snake.RIGHT) {
			if (snake.getHead().x + 1 < rightBorder && snake.noTailAt(snake.getHead().x + 1, snake.getHead().y)) {
				snake.setHeadLocation(snake.getHead().x += 1, snake.getHead().y);
			} else {
				gameOverEvent();
			}
		}
		if (snake.getDirection() == Snake.LEFT) {
			if (snake.getHead().x - 1 > leftBorder && snake.noTailAt(snake.getHead().x - 1, snake.getHead().y)) {
				snake.setHeadLocation(snake.getHead().x -= 1, snake.getHead().y);
			} else {
				gameOverEvent();
			}
		}
		// remove Points which should not exist
		if (snake.getSnakeParts().size() > snake.getTailLength()) {
			snake.getSnakeParts().remove(0);
		}
		// when - snake head position = apple position
		if (snakeHeadAtFruit()) {
			snakeAteFruit();
			relocateFruit();
			// making sure the fruit does not spawn on the snake
			while (snake.getSnakeParts().contains(apple.fruitPosition())) {
				relocateFruit();
			}
		}
	}

}
