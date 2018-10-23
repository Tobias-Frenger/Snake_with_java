package snake_main_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * This class detects if the user is hovering with the mouse cursor over one of
 * the buttons in the menu.
 * 
 * @author a16tobfr
 *
 * @method mouseMoved(MouseEvent) - used to detect if the mouse is moved over the buttons
 * 
 */
public class MouseMotionManager implements MouseMotionListener {
	private Game game;

	public MouseMotionManager(Game game) {
		this.game = game;
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		if (game.stateOfGame() == game.getMenuState()) {
			game.getBoardPanel().getRenderMenuPanel().getStartButton().checkHover(e.getX(), e.getY() - 37);
			game.getBoardPanel().getRenderMenuPanel().getQuitButton().checkHover(e.getX(), e.getY() - 37);
			game.getBoardPanel().getRenderMenuPanel().getHighScoreButton().checkHover(e.getX(), e.getY() - 37);
			game.getBoardPanel().repaint();
		}
	}
}
