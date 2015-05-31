package tileGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 * @author Team J, 1410-004 Summer 2014
 * @author Jason Timmerman
 * @author Jordan Wu
 * @author Kyle Huynh
 */
@SuppressWarnings("serial")
public class Tile extends JButton {
	static Tile[][] Tiles;
	static int baseColor = -1;
	static GameApp game;
	static ArrayList<click> clicks = new ArrayList<>();
	static ArrayList<click> undone = new ArrayList<>();

	class moused implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent arg0) {
			surrounding(HIGHLIGHT);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			surrounding(DEFAULT);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

	/** possible colors for the tile */
	static final Color BLUE = new Color(120, 0, 250), GREEN = new Color(0, 200,
			120), RED = new Color(250, 120, 0), HIGHLIGHT = Color.WHITE,
			DEFAULT = Color.BLACK;

	/** current state of the tile, 0 through 2 */
	int s;

	/** new tile */
	Tile() {
		if (baseColor == -1)
			baseColor = (int) Math.floor(Math.random() * 3);
		s = baseColor;
		setColor();
		addActionListener(new tileClick());
		addMouseListener(new moused());
		setBorder(new LineBorder(DEFAULT, 3));
	}

	/** sets the color of the tile */
	private void setColor() {
		setBackground(c());
	}

	/** this tile knows what position it's in */
	int xPos, yPos;

	/** get color from state */
	Color c() {
		if (s == 0)
			return BLUE;
		if (s == 1)
			return GREEN;
		return RED;
	}

	/** what tile is being hovered over? maybe use this for highlighting effects */
	static Tile hover = null;

	/** when this tile is changed */
	void change() {
		s++;
		if (s > 2)
			s = 0;
		setColor();
	}

	/** check to see if all tiles are identical */
	static boolean allSameColor() {
		Color a = Tiles[0][0].c();
		for (Tile[] tr : Tiles)
			for (Tile t : tr)
				if (t.c() != a)
					return false;
		return true;
	}

	/** initiates the starting states of the tiles */
	static void TileArray(int cols, int rows, GameApp frame) {
		game = frame;
		Tiles = new Tile[cols][rows];
		int[][] ca = new int[cols][rows];
		for (int y = 0; y < rows; y++)
			for (int x = 0; x < rows; x++) {
				ca[x][y] = 0;
				Tiles[x][y] = new Tile();
				Tiles[x][y].xPos = x;
				Tiles[x][y].yPos = y;
			}
		int x = 0, y = 0;

		if (cols != rows) {
			for (int z = cols + rows + 1; z >= 0; z--) {
				if (z < cols) {
					x = z;
				} else
					x = (int) Math.floor(Math.random() * cols);
				y = (int) Math.floor(Math.random() * rows);
				ca[x][y]++;
				if (ca[x][y] > 1)
					y = (int) Math.floor(Math.random() * rows);
				Tiles[x][y].onClick();
				Tiles[x][y].onClick();
			}
		} else {
			ArrayList<Integer> p = new ArrayList<>();
			for (int z = 0; z < cols; z++)
				p.add(z);
			Collections.shuffle(p);
			while (sum(ca) < Math.floor(cols * rows / 2)) {
				if (ca[x][p.get(x)] < Math.floor((Math.random() * 2) + 1)) {
					ca[x][p.get(x)]++;
					Tiles[x][p.get(x)].onClick();
					Tiles[x][p.get(x)].onClick();
				}
				x++;
				if (x >= rows) {
					x = 0;
					Collections.shuffle(p);
				}
			}

		}
	}

	/** what to do when clicked */
	void onClick() {
		if (yPos != 0)
			Tiles[xPos][yPos - 1].change();
		if (yPos < Tiles.length - 1)
			Tiles[xPos][yPos + 1].change();
		if (xPos != 0)
			Tiles[xPos - 1][yPos].change();
		if (xPos < Tiles[0].length - 1)
			Tiles[xPos + 1][yPos].change();

		if (allSameColor())
			game.Won();
	}

	void clicked() {
		GameApp.clicks++;
		onClick();
		clicks.add(new click());
		undone.clear();
		Menu.redoMenuItem.setEnabled(false);
		if (!allSameColor()) Menu.undoMenuItem.setEnabled(true);
	}

	// We need to create a method to subtract the number of clicks...
	private void updateText() {
		// String s = "" + GameApp.clickAmount;
		// GameApp.setText(s);
		GameApp.clickTextField.setText(String.valueOf(GameApp.clickAmount));

	}

	static void undo() {
		Tiles[clicks.get(clicks.size() - 1).x][clicks.get(clicks.size() - 1).y]
				.onClick();
		;
		Tiles[clicks.get(clicks.size() - 1).x][clicks.get(clicks.size() - 1).y]
				.onClick();
		;
		undone.add(clicks.get(clicks.size() - 1));
		Menu.redoMenuItem.setEnabled(true);
		clicks.remove(clicks.size() - 1);
		GameApp.clicks++;
		if (clicks.size() == 0)
			Menu.undoMenuItem.setEnabled(false);
	}

	static void redo() {
		Tiles[undone.get(undone.size() - 1).x][undone.get(undone.size() - 1).y]
				.onClick();
		;
		Tiles[undone.get(undone.size() - 1).x][undone.get(undone.size() - 1).y]
				.onClick();
		;
		clicks.add(undone.get(undone.size() - 1));
		undone.remove(undone.size() - 1);
		if (undone.size() == 0)
			Menu.redoMenuItem.setEnabled(false);
	}

	void surrounding(Color x) {
		if (yPos != 0)
			Tiles[xPos][yPos - 1].setBorder(new LineBorder(x, 3));
		if (yPos < Tiles.length - 1)
			Tiles[xPos][yPos + 1].setBorder(new LineBorder(x, 3));
		if (xPos != 0)
			Tiles[xPos - 1][yPos].setBorder(new LineBorder(x, 3));
		if (xPos < Tiles[0].length - 1)
			Tiles[xPos + 1][yPos].setBorder(new LineBorder(x, 3));
	}

	/** listens for the click */
	class tileClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			GameApp.clickAmount--;
			updateText();
			clicked();
			if (GameApp.clickAmount == 0 && Menu.gameMode.getSelectedItem().contains("Click")) {
				GameApp.thisGame.setVisible(false);
				GameApp.StartMenu.dispose();
				GameApp.thisGame = new GameApp();
				GameApp.thisGame.runGame();
				// GameApp.clickAmount = 75;
				if (!allSameColor())
					JOptionPane.showMessageDialog(null,
							"Sorry, you ran out of moves!", "Game Over",
							JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	class click {
		int x, y;

		click() {
			x = xPos;
			y = yPos;
		}
	}

	static private int sum(int[][] a) {
		int s = 0;
		for (int[] ar : a)
			for (int ac : ar)
				s += ac;
		return s;
	}	
}
