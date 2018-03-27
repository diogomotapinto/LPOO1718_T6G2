package dkeep.gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class GameMapPanel extends JPanel {

  private ImageIcon gameMap[][];
  private boolean paintMap;
  private int subSquareLength;

  public GameMapPanel() {
    super();
    gameMap = new ImageIcon[0][0];
    paintMap = false;
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
    if (paintMap) {
      for (int i = 0; i < gameMap.length; i++) {
        for (int j = 0; j < gameMap[0].length; j++) {
          g.drawImage(gameMap[i][j].getImage(), j * subSquareLength, i * subSquareLength, null);

        }
      }
      paintMap = false;
    }
  }

}