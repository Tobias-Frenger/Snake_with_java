package snake_main_package;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputManager implements KeyListener {
	private Snake snake;
	private Game game;

	public KeyInputManager(Snake snake, Game game) {
		this.snake = snake;
		this.game = game;
	}

	public void keyTyped(KeyEvent keyevent) {
	}

	public void keyPressed(KeyEvent keyevent) {
		int i = keyevent.getKeyCode();
		if (i == KeyEvent.VK_W && snake.getDirection() != Snake.DOWN) {
			snake.setDirection(Snake.UP);

			System.out.println("W - was pressed");
		}
		if (i == KeyEvent.VK_S && snake.getDirection() != Snake.UP) {
			System.out.println("S - was pressed");
			snake.setDirection(Snake.DOWN);

		}
		if (i == KeyEvent.VK_D && snake.getDirection() != Snake.LEFT) {
			System.out.println("D - was pressed");
			snake.setDirection(Snake.RIGHT);

		}
		if (i == KeyEvent.VK_A && snake.getDirection() != Snake.RIGHT) {
			System.out.println("A - was pressed");
			snake.setDirection(Snake.LEFT);

		}
		if (i == KeyEvent.VK_ESCAPE) {
			if (game.stateOfGame() == game.getGameState() || game.stateOfGame() == game.getHighScoreState()) {
				System.out.println("Going back to menu");
				game.setPause(!game.isPaused());
				game.setGameState(game.getMenuState());
			} else {
				System.out.println("system exit");
				game.setRunning(false);
				System.exit(0);
			}
		}
		if (i == KeyEvent.VK_SPACE) {
			game.setPause(!game.isPaused());
			if (Game.gameIsOver() == true) {
				game.getBoardPanel().setScoreString("");
				game.setScore(0);
				snake.snakeStart();
			}
		}

	}

	public void keyReleased(KeyEvent keyevent) {
	}

}
