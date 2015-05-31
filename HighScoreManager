package tileGame;

import java.util.*;
import java.io.*;

public class HighScoreManager {
	// An arraylist of the type "score" to work with the scores
	static ArrayList<Score> scores;

	// This is the file where we will save the highscores
	private static final String HIGHSCORE_FILE_LOCATION = "HighScore.file";

	public HighScoreManager() {
		// initializing the "scores" arraylist
		scores = new ArrayList<Score>();
	}

	public ArrayList<Score> getScores() {
		loadScoreFile(); // Load Score File Method
		sort(); // Load Sort File Method
		return scores;
	}

	private void sort() {
		HighScoreComparator comparator = new HighScoreComparator();
		Collections.sort(scores, comparator);
	}

	public void addScore(Score s) {
		loadScoreFile();
		scores.add(s);		
		sort();
		updateScoreFile();
	}

	public void loadScoreFile() {
		scores = new ArrayList<Score>();
		try (Scanner s = new Scanner(new BufferedReader(new FileReader(HIGHSCORE_FILE_LOCATION)));) {
			s.useDelimiter(",\\s*|\\n\\s*");
			while (s.hasNext()) scores.add(new Score(s.next(), Integer.parseInt(s.next().trim()), Integer.parseInt(s.next().trim())));					
		} catch (FileNotFoundException e) {e.printStackTrace();}		
	}

	public void updateScoreFile() {
		try(PrintWriter s = new PrintWriter(new FileWriter(HIGHSCORE_FILE_LOCATION));) {
			for (Score p : scores) s.printf("%s,%d,%d%n",p.name,p.score,p.difficulty);
		} catch (IOException e) {e.printStackTrace();}
	}

	public class HighScoreComparator implements Comparator<Score> {
		public int compare(Score score1, Score score2) {

			int s1 = score1.getScore();
			int s2 = score2.getScore();

			int d1 = score1.getDiff();
			int d2 = score2.getDiff();

			if (d1 < d2) {
				return +1;
			} else if (d1 == d2) {
				if (s1 > s2) {
					return +1;
				} else if (s1 < s2) {
					return -1;
				} else {
					return 0;
				}
			} else
				return -1;
		}
	}

	class Score {
		public int score;
		public String name;
		public int difficulty;

		public int getScore() {
			return score;
		}

		public void setScore(int s) {
			score=s;
		}

		public String getName() {
			return name;
		}

		public int getDiff() {
			return difficulty;
		}

		public Score(String n, int s, int d) {
			score = s;
			name = n;
			difficulty = d;
		}

		public String toString() {
			return name + ": " + score + " clicks on level " + difficulty + "x"
					+ difficulty + ".\n";
		}
	}
}
