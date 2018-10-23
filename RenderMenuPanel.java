package snake_main_package;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * This class renders the buttons for the menu that gets created within this
 * class
 * 
 * @author a16tobfr
 * 
 * @method paintMenu(Graphics) - used to paint the menu buttons
 * @method paintHighscoreButton(Graphics) - used within paintMenu
 * @method paintStartButton(Graphics) - used within paintMenu
 * @method paintQuitButton(Graphics) - used within paintMenu
 * * * * * getter methods
 * @method getStartButton() - returns a CustomButton
 * @method getQuitButton() - returns a CurstomButton
 * @method getHighScoreButton() - returns a CustomButton
 */
@SuppressWarnings("serial")
public class RenderMenuPanel extends JPanel {
	private CustomButton startButton = new CustomButton(320, 350, 160, 50, "Start Game");
	private CustomButton quitButton = new CustomButton(320, 470, 160, 50, "Quit Game");
	private CustomButton highScoreButton = new CustomButton(320, 410, 160, 50, "Highscore");

	public RenderMenuPanel() {
		setVisible(true);
	}

	public void paintMenu(Graphics graphics) {
		paintStartButton(graphics);
		paintQuitButton(graphics);
		paintHighscoreButton(graphics);
	}

	private void paintHighscoreButton(Graphics graphics) {
		highScoreButton.drawComponent(graphics);
	}

	private void paintStartButton(Graphics graphics) {
		startButton.drawComponent(graphics);
	}

	private void paintQuitButton(Graphics graphics) {
		quitButton.drawComponent(graphics);
	}
	
	public CustomButton getStartButton() {
		return startButton;
	}

	public CustomButton getQuitButton() {
		return quitButton;
	}

	public CustomButton getHighScoreButton() {
		return highScoreButton;
	}

}
