package tileGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Team J, 1410-004 Summer 2014
 * @author Jason Timmerman
 * @author Jordan Wu
 * @author Kyle Huynh
 */
@SuppressWarnings("serial")
public class GameApp extends JFrame {

	public GameApp() {
	}

	static JFrame StartMenu;
	static private JPanel GameGrid, clickPanel;
	static HighScoreManager hsm;
	static GameApp thisGame;
	static int clicks;
	static int clickAmount = 75;
	static int result;
	static private JLabel click, time;
	static TextField clickTextField, timeTextField;
	public static void main(String[] args) {
        hsm = new HighScoreManager();
        hsm.loadScoreFile();

		thisGame = new GameApp();
		thisGame.runGame();
	}

	void runGame() {
		createAndShowMainMenu("Tiles Game"); // creates the main menu called
												// tiles game
		Menu.newGameMenuItem.setEnabled(false);
	}

	void Won() {

		String Name = null;
		Name = JOptionPane.showInputDialog(null, "Congratulations, please enter your name: ");

		HighScoreManager.Score newScore = hsm.new Score(Name, clicks,
				Tile.Tiles.length);

		hsm.addScore(newScore);

		ArrayList<HighScoreManager.Score> LvlScores = new ArrayList<>();

		for (HighScoreManager.Score s : HighScoreManager.scores)
			if (s.getDiff() == Tile.Tiles.length)
				LvlScores.add(s);

		HighScoreManager.Score highScore = newScore;
		for (HighScoreManager.Score s : LvlScores)
			if (s.getScore() < clicks)
				highScore = s;

		JOptionPane.showMessageDialog(null,
				"Congratulations, you have completed the puzzle! \n\n"
						+ "Current Score: " + newScore + "Best Score: "
						+ highScore + "\n", "Leaderboard",
				JOptionPane.INFORMATION_MESSAGE);

		thisGame.setVisible(false);
		thisGame.dispose();
		Menu.undoMenuItem.setEnabled(false);
		Tile.clicks.clear();
		thisGame = new GameApp();
		thisGame.runGame();
		
		Menu.newGameMenuItem.setEnabled(false);
	}

	// create the main menu GUI
	private void createAndShowMainMenu(String string) {
		// Create and set up the window.
		StartMenu = new JFrame(string); // new JFrame object with title
		StartMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // default 
																	// exit
		StartMenu.setLocationRelativeTo(null); // start at the center of screen

		// Create and set up the content pane.
		Menu menu = new Menu(); // new menu
		StartMenu.setJMenuBar(menu.createMenuBar()); // get the menu bar
		StartMenu.setContentPane(menu.createContentPane()); // get the menu
															// content panel

		// Display the window.
		StartMenu.setSize(600, 350); // do not change setSize (background size +
										// the menu bar)
		Menu.undoMenuItem.setEnabled(false);
		StartMenu.setResizable(false);
		StartMenu.setVisible(true); // set visible

	}

	// create the new game
	public static void newGame() {

		thisGame.setVisible(false);
		StartMenu.setVisible(false);
		StartMenu.setLocationRelativeTo(null);
		thisGame.dispose();
		StartMenu.dispose();
		thisGame = new GameApp();
		thisGame.createAndShowMainMenu("New Game"); // create new game with
													// title New Game
		Menu.newGameMenuItem.setEnabled(false);

	}

	// creates the new tile game
	public static void newTileGame(int row, int column, String string) {
		Menu.newGameMenuItem.setEnabled(true);

		thisGame.setTitle(string); // sets the title depends on the difficulty
		Tile.TileArray(row, column, thisGame); // create the array of tile
		Menu menu = new Menu(); // new Menu object
		thisGame.setJMenuBar(menu.createMenuBar()); // get the menu bar
		GameGrid = new JPanel(); // creates a JPanel for the tile game
		GameGrid.setLayout(new GridLayout(row, column)); // create the grid
															// dependent on row
															// and column
		for (Tile[] ts : Tile.Tiles)
			for (Tile t : ts)
				GameGrid.add(t); // create the tile
		thisGame.getContentPane().add(GameGrid, BorderLayout.CENTER); // adds
																		// the
																		// grid
																		// with
																		// center
																		// border
																		// layout
																		// to
																		// frame
		//count click and timepanel
		clickPanel = new JPanel();
		click = new JLabel("Clicks");
		if (Menu.gameMode.getSelectedItem().contains("Click")) clickPanel.add(click);
		
		//Reset remaining back to appropriate
		clickAmount = (int) Math.floor((row * column)) + 1;
		clickTextField = new TextField(clickAmount + "");
		
		clickTextField.setEditable(false);
		if (Menu.gameMode.getSelectedItem().contains("Click")) clickPanel.add(clickTextField);
		
		long endTime = System.currentTimeMillis()+(3000 * clickAmount);
		
		time = new JLabel("Time");
		if (Menu.gameMode.getSelectedItem().contains("Time")) clickPanel.add(time);
		timeTextField = new TextField((endTime - System.currentTimeMillis())/1000+"", 10);
		timeTextField.setEditable(false);
		if (Menu.gameMode.getSelectedItem().contains("Time")) clickPanel.add(timeTextField);
		thisGame.add(clickPanel, BorderLayout.SOUTH);
		
		thisGame.setSize(row * 100, column * 100); // sets the size so that it
													// will fit all the row and
													// column
		thisGame.setVisible(true); // set visible
		Tile.clicks.clear();
		clicks = 0; // initialize clicks to zero when new tile game is created
		StartMenu.setVisible(false);
	}
}
