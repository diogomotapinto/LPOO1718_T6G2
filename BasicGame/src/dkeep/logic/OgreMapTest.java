package dkeep.logic;

public final class OgreMapTest extends KeepMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7349597634503963013L;

	public OgreMapTest(String ogresNumber) {
		super(ogresNumber);
	}

	public OgreMapTest(char[][] map) {
		super(map);
	}

	@Override
	protected Position moveOgre(Ogre ogre) {
		canMove(ogre.getPosition());
		return ogre.getPosition();
	}

	@Override
	protected Position moveClub(Ogre ogre) {
		Position pos = new Position(ogre.getPosition().getXPosition() - 1, ogre.getPosition().getYPosition());

		return pos;
	}

}
