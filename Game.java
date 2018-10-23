package snake_main_package;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * This is the class in which we create the frame for the game. The game loop is
 * also created within this class. The class creates several other objects that
 * is used within the game loop to control mouse and key inputs etc. An enum
 * called STATE is created within this class. The states that we define here are
 * used to manipulate things like what scene we are currently in.
 * 
 * @author a16tobfr
 *
 * @method gameLoop() - the main loop where checkStateAndUpdate is run
 * @method gameShutDown() - used to exit the game after the game loop ends
 * @method sleepTimer(int) - sleeps the thread for the value that gets set
 * @method gameWindow() - used to set the initial appearance of the frame
 * @method checkStateAndUpdate() - used to call the necessary methods that updates the game accordingly
 * * * * * getter methods
 * @method stateOfGame() - returns the current state that the game is in
 * @method getGameState() - returns the state called GAME
 * @method getMenuState() - returns the state called MENU
 * @method getHighScoreState() - returns the state called HIGHSCORE
 * @method getFileManager() - returns the FileManager
 * @method getBoardPanel() - returns the BoardPanel
 * @method gameIsOver() - returns a boolean value
 * @method getScore() - returns the score
 * @method isInMenu() - returns a boolean value
 * @method isInGame() - returns a boolean value
 * @method isPaused() - returns a boolean value
 * @method isRunning() - returns a boolean value
 * * * * * setter methods - chosen not to comment on most of these since they are self explanatory
 * @method setGameOver(boolean)
 * @method setPause(boolean)
 * @method setRunning(boolean)
 * @method incrementScore(int)
 * @method setGameState(STATE) - STATE is from the enumerator which is created within this class
 * @method setScore(int)
 */
public class Game {
	private int score = 0;
	private JFrame frame = new JFrame();
	private FileManager fileManager = new FileManager(this);
	private BoardPanel boardPanel = new BoardPanel(this);
	private MouseInputManager mouseInput = new MouseInputManager(this);
	private KeyInputManager keyInput = new KeyInputManager(boardPanel.getSnake(), this);
	private MouseMotionManager mouseMotion = new MouseMotionManager(this);
	private EventManager eventManager = new EventManager(boardPanel.getSnake(),
			boardPanel.getRenderEntityPanel().getApple(), this);
	private STATE gameState = STATE.MENU;

	private static boolean gameOver = false;

	private boolean isRunning = true;
	private boolean isPaused = false;
	private boolean inMenu = false;
	private boolean inGame = false;
	private boolean threadHasStarted = false;

	// Game constructor
	public Game() {
		gameWindow();
		gameLoop();
	}

	// The game loop
	private void gameLoop() {

		while (isRunning) {
			checkStateAndUpdate();
			sleepTimer(16);
		}
		gameShutDown();
	}

	private void gameShutDown() {
		System.exit(0);
	}

	private void sleepTimer(int timeInMilliSeconds) {
		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {
			System.out.println("sleepTimer - error");
			e.printStackTrace();
		}
	}

	// Initial frame appearance
	private void gameWindow() {
		frame.add(boardPanel);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBackground(new Color(53, 51, 48));
		frame.setSize(805, 835);
		frame.setLocationRelativeTo(null);
		frame.setTitle("The Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(boardPanel);
	}

	// used in the game loop
	// if the stateOfGame() return value changes, the method will adjust
	// accordingly. Going through what is relevant for each state
	private void checkStateAndUpdate() {
		if (stateOfGame() == getMenuState() && !inMenu) {
			setPause(true);
			inMenu = !inMenu;
			inGame = false;
			frame.removeKeyListener(keyInput);
			frame.repaint();
			frame.addMouseListener(mouseInput);
			if (stateOfGame() == getMenuState()) {
				frame.addMouseMotionListener(mouseMotion);
			}

		} else if (stateOfGame() == getGameState() && inMenu) {
			setPause(false);
			inMenu = !inMenu;
			inGame = true;
			frame.removeMouseListener(mouseInput);
			frame.removeMouseMotionListener(mouseMotion);
			frame.repaint();
			frame.addKeyListener(keyInput);
			keyInput.startKeyInputThread();
			if (threadHasStarted == false) {
				eventManager.startEventManagerThread();
				threadHasStarted = true;
			}

		} else if (stateOfGame() == getHighScoreState() && inMenu) {
			setPause(true);
			inMenu = !inMenu;
			frame.removeMouseListener(mouseInput);
			frame.removeMouseMotionListener(mouseMotion);
			frame.addKeyListener(keyInput);
			fileManager.readFromFile();
			frame.repaint();
		}

		if (stateOfGame() == getGameState() && inGame && !isPaused()) {
			if (Game.gameIsOver()) {
				setPause(true);
			}
			frame.repaint();
		}
	}

	// Game states
	private enum STATE {
		GAME("GAME - state"), MENU("MENU - state"), HIGHSCORE("HIGHSCORE - state");

		@SuppressWarnings("unused")
		private final String controlString;

		STATE(String controlString) {
			this.controlString = controlString;
		}
	}

	public STATE stateOfGame() {
		return gameState;
	}

	public STATE getGameState() {
		return STATE.GAME;
	}

	public STATE getMenuState() {
		return STATE.MENU;
	}

	public STATE getHighScoreState() {
		return STATE.HIGHSCORE;
	}

	// public setters
	public static void setGameOver(boolean value) {
		gameOver = value;
	}

	public void setPause(boolean booleanValue) {
		isPaused = booleanValue;
	}

	public void setRunning(boolean booleanValue) {
		isRunning = booleanValue;
	}

	public void incrementScore(int incrementBy) {
		score += incrementBy;
	}

	public void setGameState(STATE state) {
		gameState = state;
	}

	public void setScore(int newScore) {
		score = newScore;
	}

	// public getters
	public FileManager getFileManager() {
		return fileManager;
	}

	public int getScore() {
		return score;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public static boolean gameIsOver() {
		return gameOver;
	}

	public boolean isInMenu() {
		return inMenu;
	}

	public boolean isInGame() {
		return inGame;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public boolean isRunning() {
		return isRunning;
	}

}
