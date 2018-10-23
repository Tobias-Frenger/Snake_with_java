package snake_main_package;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class detects the keys that the player presses on the keyboard and sets
 * booleans to the appropriate values.
 * 
 * @author a16tobfr
 * 
 * @method keyInputThread() - used to define a new thread. This is made to avoid
 *         a problem that occur when the player presses keys to quickly that
 *         results in a sort of suicide bug.
 * 
 * @method startKeyInputThread() - used to start the keyInputThread.
 * 
 * @method keyPressed() - used to manipulate the appropriate booleans depending
 *         on what key is pressed
 *         
 * @method sleepTimer(int) - sleeps the thread for the value that gets set
 * 
 * * * * * setter methods - these are also used within Snake in the snakeReStart method
 * @method setMovingDown(boolean)
 * @method setMovingUp(boolean)
 * @method setMovingLeft(boolean)
 * @method setMovingRight(boolean)
 *         
 */
public class KeyInputManager implements KeyListener {
	private Thread keyInputThread;
	private Snake snake;
	private Game game;
	private boolean facingUp = false;
	private boolean facingDown = false;
	private boolean facingLeft = false;
	private boolean facingRight = false;
	private boolean movingDown = false;
	private boolean movingUp = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;

	public KeyInputManager(Snake snake, Game game) {
		this.snake = snake;
		this.game = game;
	}
	
	public void setMovingDown(boolean value) {
		movingDown = value;
	}

	public void setMovingUp(boolean value) {
		movingUp = value;
	}

	public void setMovingLeft(boolean value) {
		movingLeft = value;
	}

	public void setMovingRight(boolean value) {
		movingRight = value;
	}
	
	private void sleepTimer(int timeInMilliSeconds) {
		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {
			System.out.println("sleepTimer - error");
			e.printStackTrace();
		}
	}
	
	public void keyInputThread() {
		keyInputThread = new Thread(new Runnable() {
			public void run() {
				while (game.isInGame()) {
					sleepTimer(1);
					if (facingUp && movingUp == false) {
						snake.setDirection(Snake.UP);
						setMovingUp(true);
						setMovingDown(false);
						setMovingLeft(false);
						setMovingRight(false);
						sleepTimer(100);
					} else if (facingDown && movingDown == false) {
						snake.setDirection(Snake.DOWN);
						setMovingUp(false);
						setMovingDown(true);
						setMovingLeft(false);
						setMovingRight(false);
						sleepTimer(100);
					} else if (facingLeft && movingLeft == false) {
						snake.setDirection(Snake.LEFT);
						setMovingUp(false);
						setMovingDown(false);
						setMovingLeft(true);
						setMovingRight(false);
						sleepTimer(100);
					} else if (facingRight && movingRight == false) {
						snake.setDirection(Snake.RIGHT);
						setMovingUp(false);
						setMovingDown(false);
						setMovingLeft(false);
						setMovingRight(true);
						sleepTimer(100);
					}

				}
			}
		});
	}

	public void startKeyInputThread() {
		keyInputThread();
		keyInputThread.start();
	}

	public void keyTyped(KeyEvent keyEvent) {
	}

	public void keyPressed(KeyEvent keyEvent) {

		int keyPressed = keyEvent.getKeyCode();

		if (keyPressed == KeyEvent.VK_W && snake.getDirection() != Snake.DOWN) {
			facingUp = true;
			facingDown = false;
			facingLeft = false;
			facingRight = false;
		}
		if (keyPressed == KeyEvent.VK_S && snake.getDirection() != Snake.UP) {
			facingUp = false;
			facingDown = true;
			facingLeft = false;
			facingRight = false;
		}
		if (keyPressed == KeyEvent.VK_D && snake.getDirection() != Snake.LEFT) {
			facingUp = false;
			facingDown = false;
			facingLeft = false;
			facingRight = true;
		}
		if (keyPressed == KeyEvent.VK_A && snake.getDirection() != Snake.RIGHT) {
			facingUp = false;
			facingDown = false;
			facingLeft = true;
			facingRight = false;
		}
		if (keyPressed == KeyEvent.VK_ESCAPE) {
			if (game.stateOfGame() == game.getGameState() || game.stateOfGame() == game.getHighScoreState()) {
				game.setPause(!game.isPaused());
				game.setGameState(game.getMenuState());
			} else {
				System.out.println("system exit");
				game.setRunning(false);
				System.exit(0);
			}
		}
		if (keyPressed == KeyEvent.VK_SPACE) {
			game.setPause(!game.isPaused());
			if (Game.gameIsOver() == true) {
				game.getBoardPanel().setScoreString("");
				game.setScore(0);
				snake.snakeReStart(this);
			}
		}
	}

	public void keyReleased(KeyEvent keyEvent) {

	}
}
