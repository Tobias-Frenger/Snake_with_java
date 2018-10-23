package snake_main_package;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game {
	private int score = 0;
	private JFrame frame = new JFrame();
	private FileManager fileManager = new FileManager(this);
	private BoardPanel boardPanel = new BoardPanel(this);
	private MouseInputManager mouseInput = new MouseInputManager(this);
	private KeyInputManager keyInput = new KeyInputManager(boardPanel.getSnake(), this);
	private MouseMotionManager mouseMotion = new MouseMotionManager(this);
	private EventManager eventManager = new EventManager(boardPanel.getSnake(),
			boardPanel.getRenderEntityPanel().getFruit(), this);
	
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
	
	public static void setGameOver(boolean value) {
		gameOver = value;
	}
	
	public static boolean gameIsOver() {
		return gameOver;
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public boolean isInMenu() {
		return inMenu;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setPause(boolean booleanValue) {
		isPaused = booleanValue;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setRunning(boolean booleanValue) {
		isRunning = booleanValue;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void incrementScore(int incrementBy) {
		score += incrementBy;
	}

	public void setScore(int newScore) {
		score = newScore;
	}

	public int getScore() {
		return score;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public void setGameState(STATE state) {
		gameState = state;
	}

	// The game loop
	public void gameLoop() {

		while (isRunning) {
			checkStateAndUpdate();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Game loop - sleep error");
				e.printStackTrace();
			}
		}
		gameShutDown();
	}

	public void gameShutDown() {
		System.exit(0);
	}

	// Initial frame appearance
	private void gameWindow() {
		frame.add(boardPanel);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBackground(new Color(53, 51, 48));
		frame.setSize(805, 835);
		frame.setPreferredSize(new Dimension(805, 835));
		frame.setLocationRelativeTo(null);
		frame.setTitle("Sneaky Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(boardPanel);
		System.out.println(frame.getWidth() + ", " + frame.getHeight());
	}

	// used in gameLoop()
	public void checkStateAndUpdate() {
		if (stateOfGame() == STATE.MENU && !inMenu) {
			setPause(true);
			System.out.println("menu - check");
			inMenu = !inMenu;
			inGame = false;
			frame.removeKeyListener(keyInput);
			frame.repaint();
			frame.addMouseListener(mouseInput);

			if (stateOfGame() == STATE.MENU) {
				frame.addMouseMotionListener(mouseMotion);
			}

		} else if (stateOfGame() == STATE.GAME && inMenu) {
			setPause(false);
			System.out.println("game - check");
			inMenu = !inMenu;
			inGame = true;
			frame.removeMouseListener(mouseInput);
			frame.removeMouseMotionListener(mouseMotion);
			frame.repaint();
			frame.addKeyListener(keyInput);
			if (threadHasStarted == false) {
				eventManager.startEventManagerThread();
				threadHasStarted = true;
			}
			
		} else if (stateOfGame() == getHighScoreState() && inMenu) {
			setPause(true);
			System.out.println("highscore - check");
			inMenu = !inMenu;
			frame.removeMouseListener(mouseInput);
			frame.removeMouseMotionListener(mouseMotion);
			frame.addKeyListener(keyInput);

			fileManager.readFromFile();

			frame.repaint();
		}
		if (stateOfGame() == getGameState() && inGame && !isPaused()) {
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
}
