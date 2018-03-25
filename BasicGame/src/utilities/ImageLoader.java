package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class ImageLoader {
	private ImageIcon heroImg;
	private ImageIcon ogreImg;
	private ImageIcon wallImg;
	private ImageIcon blankSpaceImg;
	private ImageIcon keyImg;
	private ImageIcon doorImg;

	public ImageLoader() {
		loadImages();
	}

	private void loadImages() {
		try {
			heroImg = new ImageIcon(ImageIO.read(new File("Images/indice.png")));
			ogreImg = new ImageIcon(ImageIO.read(new File("Images/indice1.png")));
			wallImg = new ImageIcon(ImageIO.read(new File("Images/indice2.png")));
			doorImg = new ImageIcon(ImageIO.read(new File("Images/indice4.png")));
			keyImg = new ImageIcon(ImageIO.read(new File("Images/indice5.png")));
			blankSpaceImg = new ImageIcon(ImageIO.read(new File("Images/indice3.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon getHeroImg() {
		return heroImg;
	}

	public final ImageIcon getOgreImg() {
		return ogreImg;
	}

	public final ImageIcon getWallImg() {
		return wallImg;
	}

	public ImageIcon getBlankSpaceImg() {
		return blankSpaceImg;
	}

	public final ImageIcon getKeyImg() {
		return keyImg;
	}

	public final ImageIcon getDoorImg() {
		return doorImg;
	}

}
