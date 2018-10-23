package snake_main_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The board class is where we put all the objects that is supposed to be
 * painted on the screen.
 * 
 * @author a16tobfr
 *
 * @method paintComponent(Graphics) - used to draw the relevant components
 * @method renderHighScore(Graphics) - used to render the highscore
 * @method renderEntity(Graphics) - renders the entities on the board
 * @method renderMenu(Graphics) - renders the menu
 * @method borders(Graphics) - renders the borders for the game
 * @method bannerWindow(Graphics) - renders the window in which the banner is placed
 * @method banner(Graphics) - renders the banner for the game
 * @method scoreBoxWindow(Graphics) - renders the score box in which the current score is
 *         rendered
 * * * * * getter methods
 * @method getSnake() - returns the snake so we can paint it on the screen
 * @method getRenderMenuPanel() - returns the RenderMenuPanel object
 * @method getRenderEntityPanel() - returns the RenderGameEntity object
 *  * * * * * setter method
 * @method setScoreString(int)
 * 
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private Game game;
	private Snake snake = new Snake();
	private Apple apple = new Apple();

	private Color highscoreTextColor = new Color(219, 215, 4);
	private Color gameBorderColor = new Color(86, 0, 0);
	private Color bannerWindowColor = new Color(130, 138, 150);
	private Color scoreBoxBorderColor = Color.DARK_GRAY;
	private Color scoreBoxInnerColor = new Color(110, 118, 130);
	private Color scoreBoxTextColor = new Color(110, 198, 130);
	private String scoreString;
	private RenderGameEntity renderEntity = new RenderGameEntity(this.snake, this.apple);
	private RenderMenuPanel renderMenu = new RenderMenuPanel();
	private Font highScoreFont = new Font("Serif", Font.BOLD, 58);
	private BufferedImage image;

	public BoardPanel(Game game) {
		this.game = game;
		setVisible(true);
	}

	public void paintComponent(Graphics graphics) {
		borders(graphics);
		bannerWindow(graphics);
		scoreBoxWindow(graphics);
		banner(graphics);
		if (game.isInMenu() == true && game.isInGame() == false) {
			renderMenu(graphics);
		} else if (game.isInGame() == true && game.isInMenu() == false) {
			renderEntity(graphics);
		} else if (game.stateOfGame() == game.getHighScoreState()) {
			renderHighScore(graphics);
		}
	}

	private void renderHighScore(Graphics graphics) {
		int currentHighscore = game.getFileManager().getHighScore();
		graphics.setColor(highscoreTextColor);
		graphics.drawString("Highscore - ", 200, 400);
		graphics.drawString("" + currentHighscore, 500, 400);

	}

	private void renderEntity(Graphics graphics) {
		if (game.stateOfGame() == game.getGameState()) {
			renderEntity.renderEntities(graphics);
		}
	}

	private void renderMenu(Graphics graphics) {
		if (game.stateOfGame() == game.getMenuState()) {
			renderMenu.paintMenu(graphics);
		}
	}

	private void borders(Graphics graphics) {
		graphics.setColor(gameBorderColor);
		// Top border
		graphics.fillRect(0, 0, 800, 100);
		// Left border
		graphics.fillRect(0, 100, 20, 700);
		// Right border
		graphics.fillRect(780, 100, 20, 700);
		// Bottom border
		graphics.fillRect(20, 780, 760, 20);
	}

	private void bannerWindow(Graphics graphics) {
		graphics.setColor(bannerWindowColor);
		graphics.fillRect(20, 15, 760, 70);
	}

	private void banner(Graphics graphics) {
		try {
			image = ImageIO.read(new File("snakeLogo_570w.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		graphics.drawImage(image, 25, 20, this);
	}

	private void scoreBoxWindow(Graphics graphics) {
		setScoreString("" + game.getScore());
		graphics.setColor(scoreBoxBorderColor);
		graphics.fillRect(600, 17, 177, 66);
		graphics.setColor(scoreBoxInnerColor);
		graphics.fillRect(603, 20, 171, 60);
		graphics.setColor(scoreBoxTextColor);
		graphics.setFont(highScoreFont);
		graphics.drawString(scoreString.toString(), 612, 70);

	}

	public Snake getSnake() {
		return this.snake;
	}

	public RenderMenuPanel getRenderMenuPanel() {
		return renderMenu;
	}

	public RenderGameEntity getRenderEntityPanel() {
		return renderEntity;
	}
	
	public void setScoreString(String string) {
		scoreString = string;
	}
}
