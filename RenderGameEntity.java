package snake_main_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * This class renders the snake and the fruit
 * 
 * @author a16tobfr
 * 
 * @method renderEntities(Graphics) - used to render the entities
 * * * * * getter methods
 * @method getSnake() - returns the snake
 * @method getApple() - returns an apple
 */
@SuppressWarnings("serial")
public class RenderGameEntity extends JPanel {
	private Snake snake;
	private Apple apple;

	private Color snakeBodyColor = new Color(219, 215, 4);
	private Color snakeGameOverBodyColor = new Color(89, 88, 58);

	private Color snakeHeadColor = new Color(224, 134, 0);
	private Color snakeGameOverHeadColor = new Color(40, 9, 9);

	public RenderGameEntity(Snake snake, Apple apple) {
		this.snake = snake;
		this.apple = apple;
		setVisible(true);
	}

	public void renderEntities(Graphics graphics) {
		graphics.setColor(snakeBodyColor);
		if (Game.gameIsOver() == true) {
			// gameover - tail color
			graphics.setColor(snakeGameOverBodyColor);
		}
		// Snake tail
		try {
			for (Point point : snake.getSnakeParts()) {
				graphics.fillRect(point.x * snake.getScale(), point.y * snake.getScale(), snake.getScale(),
						snake.getScale());
			}
		} catch (Exception exception_1) {
			System.out.println("Java - AWT-EventQueue-0, RenderGameEntity().renderEntities()");
		}
		// Snake head - orange color
		graphics.setColor(snakeHeadColor);
		if (Game.gameIsOver() == true) {
			// gameover - head color
			graphics.setColor(snakeGameOverHeadColor);
		}
		graphics.fillRect(snake.getHead().x * snake.getScale(), snake.getHead().y * snake.getScale(), snake.getScale(),
				snake.getScale());
		// fruit
		graphics.setColor(Color.RED);
		graphics.fillRect(apple.fruitPosition().x * snake.getScale(), apple.fruitPosition().y * snake.getScale(),
				snake.getScale(), snake.getScale());
	}
	
	public Apple getApple() {
		return apple;
	}

	public Snake getSnake() {
		return snake;
	}
}
