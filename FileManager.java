package snake_main_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class writes the current high score when a game-over event occurs. It is
 * also used to read the score from the file whenever the user presses the high
 * score button in the menu.
 * 
 * @author a16tobfr
 *
 * @method writeToFile() - first calls the readFromFile method in order to get
 *         the current highscore value. Only writes to the file if the new value
 *         is greater than the old one
 * @method readFromFile() - reads the current highscore from the file
 * @method setHighScore(int) - sets current highscore
 * @method getHighScore - returns the current highscore
 */
public class FileManager {

	private Game game;
	private int highScore = 0;
	private int tempScore = 0;
	private Scanner readingScanner;

	// FileManager constructor
	FileManager(Game game) {
		this.game = game;
	}

	public void writeToFile() throws IOException {
		readFromFile();
		try {
			FileWriter fileWriter = new FileWriter("snake_highscore.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			tempScore = game.getScore();

			if (highScore < tempScore) {
				System.out.println("New highscore!");
				highScore = tempScore;
				printWriter.println(highScore);
			}
			printWriter.close();
		} catch (IOException e) {
			System.out.println("WriteToFile() - IOException!");
		}
	}

	public void readFromFile() {
		try {
			readingScanner = new Scanner(new File("snake_highscore.txt"));

			while (readingScanner.hasNextInt()) {
				setHighScore(readingScanner.nextInt());
			}
		} catch (FileNotFoundException e) {
			System.out.println("readFromFile() - FileNotFoundException!");
			e.printStackTrace();
		}
	}

	// public setter
	public void setHighScore(int score) {
		highScore = score;
	}

	// public getter
	public int getHighScore() {
		return highScore;
	}
}
