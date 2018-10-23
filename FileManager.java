 package snake_main_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {

	private Game game;
	private int highScore = 0;
	private int tempScore = 0;
	private Scanner readingScanner;

	FileManager(Game game) {
		this.game = game;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int score) {
		highScore = score;
	}

	public void writeToFile() throws IOException {
		readFromFile();
		try {
			FileWriter fileWriter = new FileWriter("snake_highscore.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			tempScore = game.getScore();

			if (highScore < tempScore) {
				System.out.println("SCORE IS GREATER THEN PREVIOUS SCORE");
				System.out.println("SCORE IS GREATER THEN PREVIOUS SCORE");
				highScore = tempScore;
				printWriter.println(highScore);
			}
			printWriter.close();
		} catch (IOException e) {
			System.out.println("ERROR!");
		}
	}

	public void readFromFile() {
		try {
			readingScanner = new Scanner(new File("snake_highscore.txt"));

			while (readingScanner.hasNextInt()) {
				setHighScore(readingScanner.nextInt());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
