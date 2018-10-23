package snake_main_package;

import java.awt.Point;
import java.io.IOException;

public class EventManager {
	private Thread eventThread;
	private Snake snake;
	private Apple apple;
	private Game game;
	
	public EventManager(Snake snake, Apple apple, Game game) {
		this.snake = snake;
		this.apple = apple;
		this.game = game;
	}

	public void startEventManagerThread() {
		moveInDirectionThread();
		eventThread.start();
	}

	public void sleepTimer(int sleepThisManyMilliSeconds) {
		try {
			Thread.sleep(sleepThisManyMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void gameOverEvent() {
		try {
			game.getFileManager().writeToFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Game.setGameOver(true);
	}

	private void snakeEvents() {

		System.out.println("running");
		snake.getSnakeParts().add(new Point(snake.getHead().x, snake.getHead().y));
		
		// detects the current direction and moves in that direction
		if (snake.getDirection() == Snake.UP) {
			if (snake.getHead().y - 1 > 4 && snake.noTailAt(snake.getHead().x, snake.getHead().y - 1)) {
				snake.setHeadLocation(snake.getHead().x, snake.getHead().y -= 1);
			} else {
				gameOverEvent();
			}
		}
		if (snake.getDirection() == Snake.DOWN) {
			if (snake.getHead().y + 1 < 39 && snake.noTailAt(snake.getHead().x, snake.getHead().y + 1)) {
				snake.setHeadLocation(snake.getHead().x, snake.getHead().y += 1);
			} else {
				gameOverEvent();
			}
		}
		if (snake.getDirection() == Snake.RIGHT) {
			if (snake.getHead().x + 1 < 39 && snake.noTailAt(snake.getHead().x + 1, snake.getHead().y)) {
				snake.setHeadLocation(snake.getHead().x += 1, snake.getHead().y);
			} else {
				gameOverEvent();
			}
		}
		if (snake.getDirection() == Snake.LEFT) {
			if (snake.getHead().x - 1 > 0 && snake.noTailAt(snake.getHead().x - 1, snake.getHead().y)) {
				snake.setHeadLocation(snake.getHead().x -= 1, snake.getHead().y);
			} else {
				gameOverEvent();
			}
		}
		// remove points which should not exist
		if (snake.getSnakeParts().size() > snake.getTailLength()) {
			snake.getSnakeParts().remove(0);
		}
		// when - snake head position = apple position
		if (snake.getHead().equals(apple.fruitPosition())) {
			snake.incrementTailLengthBy(1);
			game.incrementScore(10);
			apple.fruitPosition().setLocation(1 + apple.getRandomPosition(38), 5 + apple.getRandomPosition(34));
		}
		

	}

	public void moveInDirectionThread() {
		System.out.println("MoveInDirection()");
		eventThread = new Thread(new Runnable() {
			public void run() {
				while (game.isRunning() == true) {
					if (game.isPaused() == false && Game.gameIsOver() == false) {
						snakeEvents();
					}
					sleepTimer(1000/ snake.getSpeed());
				}
			}
		});
	}

}
