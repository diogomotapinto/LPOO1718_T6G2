package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import utilities.ImageLoader;

public class EditMapPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private int xCoordinatesPanel;
	private int yCoordinatesPanel;
	private ImageIcon chosenImage;
	private boolean paintIcon;
	private ImageIcon[][] editMap;
	private int matrixSize;
	private int panelLength;
	private int subSquareLength;
	private ImageLoader imageLoader;

	public EditMapPanel(ImageLoader imageLoader) {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);

		editMap = new ImageIcon[10][10];
		for (int i = 0; i < editMap.length; i++) {
			for (int j = 0; j < editMap[0].length; j++) {
				editMap[i][j] = imageLoader.getBlankSpaceImg();
			}
		}
		this.imageLoader = imageLoader;

	}

	public void setMatrixSize(int matrixSize) {
		this.matrixSize = matrixSize;
	}

	public void setPanelLength(int panelLength) {
		this.panelLength = panelLength;
	}

	public void setImage(ImageIcon chosenImg) {
		this.chosenImage = chosenImg;
	}

	public void setPaintIcon(boolean paintIcon) {
		this.paintIcon = paintIcon;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo ...
		System.out.println("paintComponent EditMapPanel");
		if (paintIcon == true) {
			editMap[xCoordinatesPanel * matrixSize / panelLength][yCoordinatesPanel * matrixSize
					/ panelLength] = chosenImage;
			for (int i = 0; i < editMap.length; i++) {
				for (int j = 0; j < editMap[0].length; j++) {
					g.drawImage(editMap[i][j].getImage(), i * panelLength / matrixSize, j * panelLength / matrixSize,
							null);
				}
			}
			int counter = 0;
			for (int i = 0; i < editMap.length; i++) {
				for (int j = 0; j < editMap[0].length; j++) {
					if (editMap[i][j].getImage() != imageLoader.getBlankSpaceImg().getImage()) {
						counter++;
					}
				}
			}
			System.out.println("Numero de elementos diferentes de branco= " + counter);
			paintIcon = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		xCoordinatesPanel = e.getX() / subSquareLength * subSquareLength;
		yCoordinatesPanel = e.getY() / subSquareLength * subSquareLength;
		paintIcon = true;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setSubSquareLength(int subSquareLength) {
		this.subSquareLength = subSquareLength;
	}

	public final int getSubSquareLength() {
		return subSquareLength;
	}

	public void setEditMapSize(int value) {
		ImageIcon[][] tempMap = new ImageIcon[value][value];
		int length = (value > this.editMap.length) ? this.editMap.length : value;

		int i, j;
		ImageIcon blankImage = imageLoader.getBlankSpaceImg();
		for (i = 0; i < length; i++) {
			for (j = 0; j < length; j++) {
				tempMap[i][j] = this.editMap[i][j];
			}
			for (; j < tempMap[0].length; j++) {
				tempMap[i][j] = blankImage;
			}
		}

		for (; i < tempMap.length; i++) {
			for (j = 0; j < tempMap[0].length; j++) {
				tempMap[i][j] = blankImage;
			}
		}

		this.editMap = tempMap;
	}

}
