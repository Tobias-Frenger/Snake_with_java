 package snake_main_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class CustomButton {
	private Rectangle rect;
	private static final Color MENU_TEXT_COLOR = new Color(96, 51, 51);
	private static final Color HOVER_TEXT_COLOR = new Color(35, 160, 4);
	private Color currentColor;
	private String buttonText;

	public CustomButton(int x, int y, int width, int height, String textForButton) {
		rect = new Rectangle(x, y, width, height);
		buttonText = textForButton;
		currentColor = CustomButton.MENU_TEXT_COLOR;
	}

	public void checkHover(int x, int y) {
		if (rect.contains(x, y)) {
			currentColor = CustomButton.HOVER_TEXT_COLOR;
		} else {
			currentColor = CustomButton.MENU_TEXT_COLOR;
		}
	}

	public boolean checkClick(int x, int y) {
		return rect.contains(x, y - rect.height / 2);
	}

	public void drawComponent(Graphics graphics) {
		graphics.setFont(new Font("Tahoma", Font.BOLD, 25));
		graphics.setColor(new Color(216, 216, 216));
		graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
		graphics.setColor(new Color(163, 160, 158));
		graphics.fillRect(rect.x + 5, rect.y + 5, rect.width - 10, rect.height - 10);
		graphics.setColor(currentColor);
		graphics.drawString(buttonText,
				rect.x + (rect.width / 2) - graphics.getFontMetrics().stringWidth(buttonText) / 2,
				rect.y + rect.height - 18);
	}
}
