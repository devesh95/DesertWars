import java.io.Serializable;

/**
 * Class to represent each score earned in the game
 * @author deveshdayal
 *
 */
public class Score implements Serializable {
	
	/**
	 * default serial ID
	 */
	private static final long serialVersionUID = 1L;
	private int score;
	private String name;
	
    public int getScore() {
        return score;
    }
    
    public String getName() {
    	return name;
    }

    public Score(String name, int score) {
    	this.name = name;
        this.score = score;
    }
}
