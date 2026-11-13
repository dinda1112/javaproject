import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Tracks a single student's points, badge, and quiz history.
 * Implements Gamifiable so Main.java can call addPoints/getTotalPoints
 * polymorphically.
 */
public class StudentProgress implements Gamifiable {

    private final String name;
    private int totalPoints;

    public StudentProgress(String name) {
        this.name = name;
        this.totalPoints = 0;
    }

    public String getName() {
        return name;
    }

    // ---------- Gamifiable ----------

    @Override
    public void addPoints(int points) {
        if (points > 0) totalPoints += points;
    }

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }

    // ---------- Badge ----------

    /**
     * Returns a badge string based on a percentage score (0-100).
     *   >= 90 : Gold
     *   >= 75 : Silver
     *   >= 60 : Bronze
     *   >= 40 : Beginner
     *   else  : Keep Trying
     */
    public String getBadge(int scorePercent) {
        if (scorePercent >= 90) return "🥇 Gold";
        if (scorePercent >= 75) return "🥈 Silver";
        if (scorePercent >= 60) return "🥉 Bronze";
        if (scorePercent >= 40) return "Beginner";
        return "Keep Trying";
    }

    // ---------- Persistence ----------

    /**
     * Appends a line to scores.txt in the current directory.
     * Format:  name,score,points,badge
     * The badge is recomputed here so the file is self-describing.
     */
    public void saveScoreToFile(int scorePercent) {
        String line = name + "," + scorePercent + "," + totalPoints
                    + "," + getBadge(scorePercent);
        try (PrintWriter out = new PrintWriter(new FileWriter("scores.txt", true))) {
            out.println(line);
        } catch (IOException ex) {
            System.err.println("Could not save score: " + ex.getMessage());
        }
    }
}

/**
 * The contract Main.java relies on.
 * Kept in the same file so you only have to create ONE .java file.
 */
interface Gamifiable {
    void addPoints(int points);
    int  getTotalPoints();
}