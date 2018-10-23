package snake_main_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The MouseInputManager class detects mouse clicks when the user is within the
 * menu of the game.
 * 
 * @author a16tobfr
 *
 * @method mosePressed(MouseEvent) - used to detect if the mouse is pressed within the borders of the different buttons
 * 
 */
public class MouseInputManager implements MouseListener {
	private Game game;

	public MouseInputManager(Game game) {
		this.game = game;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// start button
		if (e.getX() <= 480 && e.getX() >= 320 && e.getY() <= 437 && e.getY() >= 387
				&& game.stateOfGame() == game.getMenuState()) {
			game.setGameState(game.getGameState());
		}
		// highscore button
		if (e.getX() <= 480 && e.getX() >= 320 && e.getY() <= 495 && e.getY() >= 447
				&& game.stateOfGame() == game.getMenuState()) {
			game.setGameState(game.getHighScoreState());
		}
		// quit button
		if (e.getX() <= 480 && e.getX() >= 320 && e.getY() <= 557 && e.getY() >= 507
				&& game.stateOfGame() == game.getMenuState()) {
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
