package snake_main_package;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderMenuPanel extends JPanel { 
	@SuppressWarnings("unused")
	private Game game;
	private CustomButton startButton = new CustomButton(320, 350, 160, 50, "Start Game");
	private CustomButton quitButton = new CustomButton(320, 470, 160, 50, "Quit Game");
	private CustomButton highScoreButton = new CustomButton(320, 410, 160, 50, "Highscore");

	public CustomButton getStartButton() {
		return startButton;
	}

	public CustomButton getQuitButton() {
		return quitButton;
	}

	public CustomButton getHighScoreButton() {
		return highScoreButton;
	}

	public RenderMenuPanel() {

		System.out.println("Menu - visible");
		setVisible(true);
	}

	public void paintMenu(Graphics graphics) {
		paintStartButton(graphics);
		paintQuitButton(graphics);
		paintHighscoreButton(graphics);
		System.out.println("paintMenu(g)");
	}

	private void paintHighscoreButton(Graphics graphics) {
		highScoreButton.drawComponent(graphics);
		System.out.println("paintStartButton(g)");
	}

	private void paintStartButton(Graphics graphics) {
		startButton.drawComponent(graphics);
		System.out.println("paintStartButton(g)");
	}

	private void paintQuitButton(Graphics graphics) {
		quitButton.drawComponent(graphics);
		System.out.println("paintQuitButton(g)");
	}

}
