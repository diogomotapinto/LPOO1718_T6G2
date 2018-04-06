package dkeep.logic;

public final class OgreMapTest extends KeepMap {

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
