package tileGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

/** Game Menu for the game */
@SuppressWarnings("serial")
public class Menu extends JFrame {
	private static JLabel difficultyTitleLabel, gameTitleLabel, backgroundLabel, 
							leadershipBoardLabel, modeLabel; // difficulty and game title
													// label
	private static JPanel difficultyTitlePanel, gameTitlePanel, playPanel , backgroundPanel, difficultyListPanel
						, leadershipBoardPanel, leadershipBoardTextBoxPanel , modePanel, modeListPanel; // panels to
																// organize the
																// label and
																// buttons
	private static JScrollPane scrollPane; // scrollPane for list box
	private static Container mainPanel; // the main panel
	private static JLayeredPane layerPanel; // layerPane for background
	private static JMenuBar menuBar; // menuBar
	private static JMenu fileMenu, helpMenu; // menu
	static JMenuItem newGameMenuItem, undoMenuItem, redoMenuItem,
			highScoreMenuItem; // items for file menu
	private static JMenuItem rulesMenuItem, aboutMenuItem, howToPlayMenuItem; // items
																		// for
																		// help
																		// menu
	@SuppressWarnings("rawtypes")
	private static JList listbox; // list box that list the difficulty
	private static ImageIcon selectionIcon, titleIcon, playIcon, backgroundIcon, leadershipBoardIcon
							, modeIcon; // icon for select
															// difficulty,
															// title, and play
															// button
	private static JButton playButton; // play button
	private static JFrame gameFrame;
	static Choice gameMode;
	private JTextArea leadershipBoardTextBox;

	/** Creates a menu-bar for Menu */
	public JMenuBar createMenuBar() {
		// A menu-bar contains menus. A menu contains menu-items
		menuBar = new JMenuBar();
		setJMenuBar(menuBar); // set menu bar to the JMenu

		// First Menu called file
		fileMenu = new JMenu("File"); // create the file menu
		menuBar.add(fileMenu); // the menu-bar adds this file menu

		newGameMenuItem = new JMenuItem("New Game");// create a menu item called
													// New Game		
		fileMenu.add(newGameMenuItem); // add NewGame menu item into the file
										// menu
		newGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Starts a new game menu (Problem open a new window but
				// does not close the old window) 
				/**Fixed: Duplication Problem, Kyle*/
				GameApp.thisGame.setVisible(false);
				GameApp.StartMenu.dispose();
				GameApp.thisGame = new GameApp();
				GameApp.thisGame.runGame();
			}
		});
		undoMenuItem = new JMenuItem("Undo"); // create a menu item
		undoMenuItem.setEnabled(false);
		fileMenu.add(undoMenuItem); // add the undo menu item into the file menu
		undoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Tile.undo();
			}
		});
		redoMenuItem = new JMenuItem("Redo"); // create a menu item called re-do
		redoMenuItem.setEnabled(false);
		fileMenu.add(redoMenuItem); // add the undo menu item into the file menu
		redoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Tile.redo();
			}
		});
		highScoreMenuItem = new JMenuItem("High Score"); // create a menu item
															// called high score
		fileMenu.add(highScoreMenuItem); // add the high score item into the
											// file menu
		highScoreMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder message = new StringBuilder();
				for (HighScoreManager.Score s : HighScoreManager.scores)
					message.append(s);
				JOptionPane.showMessageDialog(GameApp.thisGame, message, "Scores", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// Second Menu called help
		helpMenu = new JMenu("Help"); // create the help menu
		menuBar.add(helpMenu); // the menu-bar adds this help menu

		rulesMenuItem = new JMenuItem("Rules");// create a menu item called New
												// Game
		helpMenu.add(rulesMenuItem); // add rules menu item into the help menu
		rulesMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder message = new StringBuilder();
				message.append("1.  Whenever you click on a tile, the four adjacent tiles change color.\n");
				message.append("    (Diagonal tiles are not considered adjacent.)\n");
				message.append("\n");
				message.append("2.  Colors change in this order: Violet \u2192 Green \u2192 Orange \u2192 back to Violet.\n");
				message.append("\n");
				message.append("3.  When all the tiles are the same color, you win!\n");
				message.append("\n");
				message.append("4.  Try to use as few clicks as possible for the best score.\n");
				
				JOptionPane.showMessageDialog(GameApp.thisGame, message, "Rules", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aboutMenuItem = new JMenuItem("About");// create a menu item called
												// about
		helpMenu.add(aboutMenuItem); // add about menu item into the help menu
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder message = new StringBuilder();
				message.append("Tiles Game is a final group project, developed by:\n");
				message.append("\n");
				message.append("Jason Timmerman, ");
				message.append("Jordan Wu, ");
				message.append("and Kyle Huynh\n");
				message.append("\n");
				message.append("for CSIS 1410: Object-Oriented Programming\n");
				message.append("Instructor: Mark Gunderson\n");
				
				JOptionPane.showMessageDialog(GameApp.thisGame, message, "About", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		howToPlayMenuItem = new JMenuItem("How to Play?");// create a menu item
															// called how to
															// play
		helpMenu.add(howToPlayMenuItem); // add how to play menu item into the
											// help menu
		howToPlayMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder message = new StringBuilder();
				message.append("1.  Whenever you click on a tile, the four adjacent tiles change color.\n");
				message.append("    (Diagonal tiles are not considered adjacent.)\n");
				message.append("\n");
				message.append("2.  Colors change in this order: Violet \u2192 Green \u2192 Orange \u2192 back to Violet.\n");
				message.append("\n");
				message.append("3.  When all the tiles are the same color, you win!\n");
				message.append("\n");
				message.append("4.  Try to use as few clicks as possible for the best score.\n");
				
				JOptionPane.showMessageDialog(GameApp.thisGame, message, "How to Play", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		return menuBar;
	}

	// create selection difficulty panel
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Container createContentPane() {
		mainPanel = getContentPane(); // Sets the content pane property
		setLayout(new BorderLayout()); // set layout to borderLayout
		layerPanel = new JLayeredPane(); // new JLayerPane (allow us to layer
											// the panels)

		// background for the menu
		backgroundPanel = new JPanel(); //create background panel
		backgroundPanel.setLayout(new BorderLayout()); // set game title panel to
														// default border layout
		backgroundIcon = new ImageIcon(getClass().getResource("background.png"));
		backgroundLabel = new JLabel(backgroundIcon); // add icon to label
		backgroundPanel.add(backgroundLabel); // add the label to panel
		layerPanel.add(backgroundPanel); // set panel to borderLayout
		add(layerPanel); // add the pane to the mainPane
		layerPanel.setBounds(0, 0, 800, 325); // set panel to the size of frame
												// (try not to change this)

		// difficulty title label
		difficultyTitlePanel = new JPanel(); // create a panel call game panel
		difficultyTitlePanel.setLayout(new BorderLayout());

		// selection label
		selectionIcon = new ImageIcon(getClass()
				.getResource("selectLabel.png")); // get the selectLabel
		difficultyTitleLabel = new JLabel(selectionIcon); // save png into label
		difficultyTitlePanel.add(difficultyTitleLabel); // adds label to panel

		// Create items in the list box
		difficultyListPanel = new JPanel(); // create a panel
		difficultyListPanel.setLayout(new BorderLayout()); // set layout
		String list[] = { "3 x 3 (Learning)", "4 x 4 (Easy)", "5 x 5 (Medium)", "6 x 6 (Hard)", "7 x 7 (Very Hard)", "8 x 8 (Extreme)",
				"9 x 9 (Expert)", "to be continued" };

		// Create a new list box control
		scrollPane = new JScrollPane(); // create scrollPanel
		listbox = new JList(); // save the list into list box
		listbox.setListData(list); // add list to list box
		listbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // only
																		// allow
																		// to
																		// select
																		// one
																		// element
																		// at a
		listbox.setBorder(BorderFactory.createEmptyBorder()); // empty border														// time
		listbox.setForeground(Color.WHITE); // white text
		listbox.setBackground(new Color(26, 26, 26)); // black background
		scrollPane.setViewportView(listbox); // add the list box into scroll
												// panel
		difficultyListPanel.add(scrollPane); // add the scroll panel into difficulty
											// panel
		
		//create leadership label
		leadershipBoardPanel = new JPanel(); //create panel
		leadershipBoardPanel.setLayout(new BorderLayout()); // set layout
		leadershipBoardIcon = new ImageIcon(getClass().getResource("leadershipboard.png")); // get icon
		leadershipBoardLabel = new JLabel(leadershipBoardIcon); // add icon to label

		StringBuilder leaderMessage = new StringBuilder();
		ArrayList<ArrayList<HighScoreManager.Score>> scoresList = new ArrayList<>();
		HighScoreManager.Score[] highScoreByDiff = new HighScoreManager.Score[11]; 
		for (int x=0;x<=10;x++) {
			highScoreByDiff[x]= GameApp.hsm.new Score("",99999999,x);
			scoresList.add(new ArrayList<HighScoreManager.Score>());	
		}
		for (HighScoreManager.Score s : HighScoreManager.scores) scoresList.get(s.getDiff()).add(s);

		for (int x=3;x<=9;x++) 
			for (HighScoreManager.Score s : HighScoreManager.scores)
				if (s.getScore() < highScoreByDiff[x].getScore() && s.getDiff()==x)
					highScoreByDiff[x]=s;				

		for (int x=3;x<=9;x++) 
			if (highScoreByDiff[x].getScore()!=99999999)
				leaderMessage.append(highScoreByDiff[x]);

//		leadershipBoardLabel.setText(leaderMessage.toString());

		leadershipBoardPanel.add(leadershipBoardLabel); // add label to panel

		// create the game title panel
		gameTitlePanel = new JPanel(); // create the game title panel
		gameTitlePanel.setLayout(new BorderLayout()); // set game title panel to
														// default border layout
		titleIcon = new ImageIcon(getClass().getResource("tilesLabel.png")); // get
																				// the
																				// tiles
																				// png
		gameTitleLabel = new JLabel(titleIcon); // save the png into game title
												// label
		gameTitlePanel.add(gameTitleLabel); // add the title label into game
											// title panel

		//text box
		leadershipBoardTextBoxPanel = new JPanel();
		leadershipBoardTextBoxPanel.setLayout(new BorderLayout());
		leadershipBoardTextBox = new JTextArea(leaderMessage.toString());
		leadershipBoardTextBox.setFont(new Font("PLAIN", Font.BOLD, 12));
		leadershipBoardTextBox.setLineWrap(true);
		leadershipBoardTextBox.setBackground(Color.WHITE);
		leadershipBoardTextBox.setWrapStyleWord(true);
		leadershipBoardTextBox.setForeground(Color.ORANGE);
		leadershipBoardTextBox.setEnabled(false);
		leadershipBoardTextBox.setDisabledTextColor(Color.RED);
		
		JScrollPane leadershipBoardTextBoxScrollPane = new JScrollPane(leadershipBoardTextBox);
		leadershipBoardTextBoxScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		leadershipBoardTextBoxScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		leadershipBoardTextBoxPanel.add(leadershipBoardTextBox);
		// modeLabel
		modePanel = new JPanel(); // mode panel
		modePanel.setLayout(new BorderLayout()); // set layout
		modeIcon = new ImageIcon(getClass().getResource("gamemode.png")); //get icon
		modeLabel = new JLabel(modeIcon); // set label to icon
		modePanel.add(modeLabel); // add label to panel
		
		// modeList
		modeListPanel = new JPanel(); // create panel
		modeListPanel.setLayout(new BorderLayout()); // set layout
		gameMode = new Choice(); // create choice
		gameMode.add("Normal"); // choice 1
//		gameMode.add("Timed"); // choice 2
		gameMode.add("Limited Clicks"); //choice 3
		gameMode.setBackground(new Color(26,26,26));
		gameMode.setFont(new Font("Boardway", Font.PLAIN, 24));
		//gameMode.addItemListener();
		modeListPanel.add(gameMode);
		modeListPanel.setBackground(new Color(26,26,26));
		modeListPanel.setForeground(Color.WHITE);
		
		// create a play button panel
		playPanel = new JPanel(); // create the play button panel
		playPanel.setLayout(new BorderLayout()); // set the play button panel to
													// default border layout
		playIcon = new ImageIcon(getClass().getResource("playbutton.png")); // get
																			// the
																			// play
																			// png
		playButton = new JButton(playIcon); // create a play button img into
											// button
		playPanel.add(playButton); // add the play button to the play panel
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// create 3x3 tile game
				if (listbox.isSelectedIndex(0)) {
					GameApp.thisGame.setVisible(false);
					GameApp.thisGame.dispose();
					GameApp.thisGame = new GameApp();
					GameApp.newTileGame(3, 3, "3 x 3 Game");
				}
				// create 4x4 tile game
				if (listbox.isSelectedIndex(1)) {
					GameApp.thisGame.setVisible(false);
					GameApp.thisGame.dispose();

					GameApp.thisGame = new GameApp();
					GameApp.newTileGame(4, 4, "4 x 4 Game");
					System.out.println(listbox.isSelectionEmpty());

				}
				// create 5x5 tile game
				if (listbox.isSelectedIndex(2)) {
					GameApp.thisGame.setVisible(false);
					GameApp.thisGame.dispose();

					GameApp.thisGame = new GameApp();
					GameApp.newTileGame(5, 5, "5 x 5 Game");
				}
				// create 6x6 tile game
				if (listbox.isSelectedIndex(3)) {
					GameApp.thisGame.setVisible(false);
					GameApp.thisGame.dispose();

					GameApp.thisGame = new GameApp();
					GameApp.newTileGame(6, 6, "6 x 6 Game");
				}
				// create 7x7 tile game
				if (listbox.isSelectedIndex(4)) {
					GameApp.thisGame.setVisible(false);
					GameApp.thisGame.dispose();

					GameApp.thisGame = new GameApp();
					GameApp.newTileGame(7, 7, "7 x 7 Game");
				}
				// create 8x8 tile game
				if (listbox.isSelectedIndex(5)) {
					GameApp.thisGame.setVisible(false);
					GameApp.thisGame.dispose();

					GameApp.thisGame = new GameApp();
					GameApp.newTileGame(8, 8, "8 x 8 Game");
				}
				// create 9x9 tile game
				if (listbox.isSelectedIndex(6)) {
					GameApp.thisGame.setVisible(false);
					GameApp.thisGame.dispose();
					GameApp.thisGame = new GameApp();
					GameApp.newTileGame(9, 9, "9 x 9 Game");
				}
				// dialog pop up too continued
				if (listbox.isSelectedIndex(7)) {
					JOptionPane.showMessageDialog(gameFrame,
							"There will be more coming soon!",
							"Tile Game Error", JOptionPane.ERROR_MESSAGE);
					System.out.println(listbox.isEnabled());
				}
				// dialog when Selection is not empty
				if (listbox.isSelectionEmpty() == true) {
					JOptionPane.showMessageDialog(gameFrame,
							"PLEASE SELECT DIFFICULTY ", "Tile Game Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		listbox.setSelectedIndex(2);
		// customizing the panel based on the setBounds method
		modeListPanel.setBounds(155,235,200,40);
		modePanel.setBounds(10,240,120,30);
		leadershipBoardTextBoxPanel.setBounds(370, 70, 200, 200); // set bound
		leadershipBoardTextBoxPanel.setForeground(Color.BLACK);
		
		leadershipBoardTextBoxPanel.setBorder(new LineBorder(Color.WHITE,10));
		backgroundPanel.setBounds(0, 0, 800,325); //set the background panel bound
		
		gameTitlePanel.setBounds(155, 10, 200, 115); // set the game title panel
														// bounds (used to move
														// and adjust the label)
		difficultyTitlePanel.setBounds(10, 10, 120, 60); // set the difficulty panel
													// bounds (used to move the
													// grid layout panel)
		playPanel.setBounds(165, 140, 178, 76); // set the play panel bounds
												// (used to move the button)
		difficultyListPanel.setBounds(10, 75, 120, 150);
		
		leadershipBoardPanel.setBounds(410, 10, 120, 60); // set bounds

		// add the panel into layer panel and layer panel into main panel
		
		layerPanel.add(gameTitlePanel, new Integer(0), 0); // the first layer
															// panel
		layerPanel.add(difficultyTitlePanel, new Integer(1), 0); // the second layer
															// panel
		layerPanel.add(playPanel, new Integer(2), 0); // the third layer panel
		layerPanel.add(difficultyListPanel, new Integer(3), 0);
		layerPanel.add(leadershipBoardPanel, new Integer(4), 0);
		layerPanel.add(leadershipBoardTextBoxPanel, new Integer(5), 0);
		layerPanel.add(modePanel, new Integer(6), 0);
		layerPanel.add(modeListPanel, new Integer(7), 0);
		mainPanel.add(layerPanel); // add the layer panel to the main panel

		return mainPanel; // returns the main panel
	}
}
