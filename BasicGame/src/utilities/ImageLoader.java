package utilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class ImageLoader {
	private ImageIcon heroImg;
	private ImageIcon heroAmredImg;
	private ImageIcon ogreImg;
	private ImageIcon wallImg;
	private ImageIcon blankSpaceImg;
	private ImageIcon keyImg;
	private ImageIcon doorImg;
	private ImageIcon guardImg;
	private ImageIcon doorOpenImg;
	private ImageIcon clubImg;
	private ImageIcon ogreStunned;

	/**
	 * Class constructor
	 */
	public ImageLoader() {
		loadImages();
	}

	private void loadImages() {
		try {
			heroImg = new ImageIcon(ImageIO.read(new File("Images/hero40.png")));
			heroAmredImg = new ImageIcon(ImageIO.read(new File("Images/hero40.png")));
			ogreImg = new ImageIcon(ImageIO.read(new File("Images/ogre.png")));
			wallImg = new ImageIcon(ImageIO.read(new File("Images/wall40.png")));
			blankSpaceImg = new ImageIcon(ImageIO.read(new File("Images/indice3.png")));
			doorImg = new ImageIcon(ImageIO.read(new File("Images/closed40.png")));
			keyImg = new ImageIcon(ImageIO.read(new File("Images/key40.png")));
			guardImg = new ImageIcon(ImageIO.read(new File("Images/guard.png")));
			doorOpenImg = new ImageIcon(ImageIO.read(new File("Images/open40.png")));
			clubImg = new ImageIcon(ImageIO.read(new File("Images/ogreClub.png")));
			ogreStunned = new ImageIcon(ImageIO.read(new File("Images/ogrestunned.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon getOgreStunned() {
		return ogreStunned;
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

	public final ImageIcon getGuardImg() {
		return guardImg;
	}

	public final ImageIcon getHeroArmedImg() {
		return heroAmredImg;
	}

	public ImageIcon getDoorOpenImg() {
		return doorOpenImg;
	}

	public final ImageIcon getClubImg() {
		return clubImg;
	}

}
