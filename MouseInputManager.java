package snake_main_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputManager implements MouseListener {
	private Game game;

	public MouseInputManager(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() <= 480 && e.getX() >= 320 && e.getY() <= 437 && e.getY() >= 387
				&& game.stateOfGame() == game.getMenuState()) {
			game.setGameState(game.getGameState());
			System.out.println("START BUTTON PRESSED");

		}

		if (e.getX() <= 480 && e.getX() >= 320 && e.getY() <= 495 && e.getY() >= 447
				&& game.stateOfGame() == game.getMenuState()) {
			System.out.println("HIGHSCORE BUTTON PRESSED");
			game.setGameState(game.getHighScoreState());
		}

		if (e.getX() <= 480 && e.getX() >= 320 && e.getY() <= 557 && e.getY() >= 507
				&& game.stateOfGame() == game.getMenuState()) {
			System.out.println("QUIT BUTTON PRESSED");
			game.setRunning(false);

		}

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
