package snake_main_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderBoardPanel extends JPanel {
	private Game game;
	private String scoreString;

	public RenderBoardPanel(Game game) {
		this.game = game;
		setVisible(true);
	}

	private RenderGameEntity renderEntity = new RenderGameEntity(new Snake(), new Fruit(), this.game);
	private RenderMenuPanel renderMenu = new RenderMenuPanel(this.game);

	private Font highScoreFont = new Font("Serif", Font.BOLD, 58);

	private BufferedImage image;

	public RenderMenuPanel getRenderMenuPanel() {
		return renderMenu;
	}

	public RenderGameEntity getRenderEntityPanel() {
		return renderEntity;
	}

	public void paintComponent(Graphics graphics) {
		frameBorders(graphics);
		bannerWindow(graphics);
		scoreBoxWindow(graphics);
		logo(graphics);
		if (game.isInMenu() == true && game.isInGame() == false) {
			System.out.println("paintComponent(g) - rendering Menu(g)...");
			renderMenu(graphics);
		} else if (game.isInGame() == true && game.isInMenu() == false) {
			System.out.println("paintComponent(g) - rendering Entity(g)...");
			renderEntity(graphics);
		} else if (game.stateOfGame() == game.getHighScoreState()) {
			renderHighScore(graphics);
		}
	}

	private void renderHighScore(Graphics graphics) {
		int currentHighscore = game.getFileManager().getHighScore();
		graphics.setColor(new Color(219, 215, 4));
		graphics.drawString("Highscore - ", 100, 400);
		graphics.drawString("" + currentHighscore, 400, 400);

	}

	private void renderEntity(Graphics graphics) {
		if (game.stateOfGame() == game.getGameState()) {
			renderEntity.renderEntities(graphics);
			if (Game.gameOver) {
				game.setPause(true);
			}
		}
	}

	private void renderMenu(Graphics graphics) {
		if (game.stateOfGame() == game.getMenuState()) {
			renderMenu.paintMenu(graphics);
		}
	}

	private void frameBorders(Graphics graphics) {
		graphics.setColor(new Color(86, 0, 0));
		// Top border
		graphics.fillRect(0, 0, 800, 100);
		// Left border
		graphics.fillRect(0, 100, 20, 800);
		// Right border
		graphics.fillRect(780, 100, 20, 800);
		// Bottom border
		graphics.fillRect(20, 780, 760, 20);
	}

	private void bannerWindow(Graphics graphics) {
		graphics.setColor(new Color(130, 138, 150));
		graphics.fillRect(20, 15, 760, 70);
	}

	public void setScoreString(String string) {
		scoreString = string;
	}

	private void scoreBoxWindow(Graphics graphics) {
		setScoreString("" + game.getScore());

		// if (Game.gameOver) {
		// scoreString = "";
		// game.setScore(0);
		// }
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(600, 17, 177, 66);
		graphics.setColor(new Color(110, 118, 130));
		graphics.fillRect(603, 20, 171, 60);
		graphics.setColor(new Color(110, 198, 130));
		graphics.setFont(highScoreFont);
		graphics.drawString(scoreString.toString(), 612, 70);

	}

	private void logo(Graphics graphics) {
		try {
			image = ImageIO.read(new File("snakeLogo_570w.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		graphics.drawImage(image, 25, 20, this);
	}
}
