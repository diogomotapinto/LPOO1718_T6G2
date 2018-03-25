package dkeep.gui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import utilities.ImageLoader;

public class GameMapPanel extends JPanel {
	private ImageLoader imageLoader;
	private ImageIcon gameMap[][];
	private boolean paintMap;
	private int subSquareLength;
	private int counter;

	public GameMapPanel(ImageLoader imageLoader) {
		super();
		this.imageLoader = imageLoader;
		gameMap = new ImageIcon[0][0];
		paintMap = false;
		counter = 0;
	}

	public void setMap(ImageIcon[][] imgMap) {
		this.gameMap = imgMap;
		paintMap = true;
	}

	public void setSubSquareLength(int subSquareLength) {
		this.subSquareLength = subSquareLength;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("GameMapPanel paintComponent");
		if (paintMap == true) {
			if (counter == 29) {
				int a = 0;
			}
			System.out.println("GameMapPanel paintComponent true");
			for (int i = 0; i < gameMap.length; i++) {
				for (int j = 0; j < gameMap[0].length; j++) {
					// int value = i * subSquareLength;
					// int value2 = j * subSquareLength;
					// System.out.println("i= " + i);
					// System.out.println("j= " + j);
					// System.out.println("\n");
					if (gameMap[i][j] != null)
						g.drawImage(gameMap[i][j].getImage(), j * subSquareLength, i * subSquareLength, null);

				}
			}
			paintMap = false;
			counter++;
		}
	}

}