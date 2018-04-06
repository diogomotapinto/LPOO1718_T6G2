package dkeep.gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class GameMapPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3404250020666809834L;
	/**
	 * 
	 */
	private ImageIcon gameMap[][];
	private int subSquareLength;

	public GameMapPanel() {
		super();
		gameMap = new ImageIcon[0][0];
	}

	public void setMap(ImageIcon[][] imgMap) {
		this.gameMap = imgMap;
	}

	public void setSubSquareLength(int subSquareLength) {
		this.subSquareLength = subSquareLength;
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
			for (int i = 0; i < gameMap.length; i++) {
				for (int j = 0; j < gameMap[0].length; j++) {
					g.drawImage(gameMap[i][j].getImage(), j * subSquareLength, i * subSquareLength, null);
				}
			}
	}

}