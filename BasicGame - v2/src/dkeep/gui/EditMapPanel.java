package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import utilities.ImageLoader;

class EditMapPanel extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7340605719176534433L;
	/**
	 * 
	 */
	private int xCoordinatesPanel;
	private int yCoordinatesPanel;
	private ImageIcon chosenImage;
	private ImageIcon[][] editMap;
	private int matrixSize;
	private int panelLength;
	private int subSquareLength;
	private ImageLoader imageLoader;

	/**
	 * Class constructor
	 * 
	 * @param imageLoader
	 */
	EditMapPanel(ImageLoader imageLoader) {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);

		editMap = new ImageIcon[10][10];
		for (int i = 0; i < editMap.length; i++) {
			for (int j = 0; j < editMap[0].length; j++) {
				editMap[i][j] = imageLoader.getBlankSpaceImg();
			}
		}

		this.imageLoader = imageLoader;
		this.chosenImage = imageLoader.getBlankSpaceImg();

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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo ...
		editMap[yCoordinatesPanel * matrixSize / panelLength][xCoordinatesPanel * matrixSize
				/ panelLength] = chosenImage;
		for (int i = 0; i < editMap.length; i++) {
			for (int j = 0; j < editMap[0].length; j++) {
				g.drawImage(editMap[j][i].getImage(), i * subSquareLength, j * subSquareLength, null);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getX() >= 0 && e.getY() >= 0 && e.getX() < this.getWidth() && e.getY() < this.getWidth()) {
			xCoordinatesPanel = e.getX() / subSquareLength * subSquareLength;
			yCoordinatesPanel = e.getY() / subSquareLength * subSquareLength;
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// its not to supposed to be implemented
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= 0 && e.getY() >= 0 && e.getX() < this.getWidth() && e.getY() < this.getWidth()) {
			xCoordinatesPanel = e.getX() / subSquareLength * subSquareLength;
			yCoordinatesPanel = e.getY() / subSquareLength * subSquareLength;
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// its not to supposed to be implemented
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// its not to supposed to be implemented
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// its not to supposed to be implemented
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// its not to supposed to be implemented
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

	public final ImageIcon[][] getEditMap() {
		return editMap;
	}

	public void setEditMapSize(ImageIcon[][] editMap) {
		this.editMap = editMap;
	}

}
