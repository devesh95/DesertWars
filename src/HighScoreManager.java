import java.util.*;
import java.io.*;

/**
 * Class to create, load, update and handle high scores with external files
 * @author deveshdayal
 *
 */
public class HighScoreManager {
	private int lowestScore;
	private ArrayList<Score> scores;
	private int num;

	// The name of the file where the high scores will be saved
	private static final String HIGHSCORE_FILE = "highscores.dat";

	//Initializing an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighScoreManager() {
		this.scores = new ArrayList<Score>();
		this.lowestScore = 0;
		this.num = 10;
	}

	public ArrayList<Score> getScores() {
		loadScoreFile();
		rankScores();
		return scores;
	}
	private void rankScores() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}
	/**
	 * Call when creating the high score file to create a base list of scores.
	 */
	public void addDefaultScores(){
		for(int i=1; i<11; i++) {
			addScore("Devesh", 500*i);
		}
	}
	/**
	 * Gets stored scores locally, updates the data and then updates the file.
	 * @param score
	 */
	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new Score(name, score));
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
			System.out.println("FNF Error: " + e.getMessage());
			System.out.println("The file doesn't exist. New file created.");
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("IO Error: " + e.getMessage());
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
			System.out.println("FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	public String getHighscores() {
		String highscoreString = "";

		ArrayList<Score> stored = getScores();
		
		//return all values if less than the number that exist
		
		
		for(int i=0; i < num; i++){
			highscoreString += (i + 1) + ".\t" +
		    stored.get(i).getName() + ":\t" + stored.get(i).getScore() + "\n";
		}
		return highscoreString;
	}
	
	/**
	 * returns the lowest high score stored in the hall of fame in the file.
	 * @return
	 */
	public int getLowestScore() {
		ArrayList<Score> stored = getScores();
		if(num > stored.size())
			num = stored.size();

		this.lowestScore = stored.get(num-1).getScore();
		return lowestScore;
	}
}
