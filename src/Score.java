import java.io.Serializable;

/**
 * Class to represent each score earned in the game
 * @author deveshdayal
 *
 */
public class Score implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int score;
    public int getScore() {
        return score;
    }

    public Score(int score) {
        this.score = score;
    }
}
