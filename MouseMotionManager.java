package snake_main_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionManager implements MouseMotionListener {
	private Game game;

	public MouseMotionManager(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (game.stateOfGame() == game.getMenuState()) {
			game.getBoardPanel().getRenderMenuPanel().getStartButton().checkHover(e.getX(), e.getY() - 37);
			game.getBoardPanel().getRenderMenuPanel().getQuitButton().checkHover(e.getX(), e.getY() - 37);
			game.getBoardPanel().getRenderMenuPanel().getHighScoreButton().checkHover(e.getX(), e.getY() - 37);
			game.getBoardPanel().repaint();
		}
	}
}
