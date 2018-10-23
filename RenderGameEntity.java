 package snake_main_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderGameEntity extends JPanel {
	private Snake snake;
	private Apple apple;

	public RenderGameEntity(Snake snake, Apple apple) {
		this.snake = snake;
		this.apple = apple;
		System.out.println("RenderGameEntity");
		setVisible(true);
	}
	
	public Apple getFruit() {
		return apple;
	}

	public Snake getSnake() {
		return snake;
	}

	public void renderEntities(Graphics graphics) {
		// yellow color
		graphics.setColor(new Color(219, 215, 4));
		if (Game.gameIsOver() == true) {
			graphics.setColor(new Color(89, 88, 58));
		}
		System.out.println("drawing entities");
		// Snake tail
		for (Point point : snake.getSnakeParts()) {
			graphics.fillRect(point.x * snake.getScale(), point.y * snake.getScale(), snake.getScale(),
					snake.getScale());
		}
		// Snake head
		// orange color
		graphics.setColor(new Color(224, 134, 0));
		if (Game.gameIsOver() == true) {
			graphics.setColor(new Color(40, 9, 9));
		}
		graphics.fillRect(snake.getHead().x * snake.getScale(), snake.getHead().y * snake.getScale(), snake.getScale(),
				snake.getScale());
		System.out.println("snake painted");
		// fruit
		graphics.setColor(Color.RED);
		graphics.fillRect(apple.fruitPosition().x * 20, apple.fruitPosition().y * 20, 20, 20);
	}
}
