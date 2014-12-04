import java.util.*;
import java.io.*;

/**
 * Class to create, load, update and handle high scores with external files
 * @author deveshdayal
 *
 */
public class HighScoreManager {
	private ArrayList<Score> scores;

	// The name of the file where the high scores will be saved
	private static final String HIGHSCORE_FILE = "src/highscores.dat";

	//Initialising an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighScoreManager() {
		scores = new ArrayList<Score>();
	}

	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}
	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}
	/**
	 * Call when creating the high score file to create a base list of scores.
	 */
	public void addDefaultScores(){
		for(int i=1; i<6; i++) {
			addScore(1000*i);
		}
	}
	/**
	 * Gets stored scores locally, updates the data and then updates the file.
	 * @param score
	 */
	public void addScore(int score) {
		loadScoreFile();
		scores.add(new Score(score));
		updateScoreFile();
	}
	
	/**
	 * Opens the file stream, casts data into an ArrayList and closes the stream.
	 */
	@SuppressWarnings("unchecked")
	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Load] FNF Error: " + e.getMessage());
			System.out.println("The file doesn't exist. New file created.");
		} catch (IOException e) {
			System.out.println("[Load] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Load] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Load] IO Error: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Opens the file stream, writes an ArrayList to the file then closes the stream.
	 */
	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}
	public String getHighscores(int num) {
		String highscoreString = "";

		ArrayList<Score> stored = getScores();
		
		if(num > stored.size())
			num = stored.size();

		for(int i=0; i < num; i++){
			highscoreString += (i + 1) + ".\t" + stored.get(i).getScore() + "\n";
		}
		return highscoreString;
	}
}
