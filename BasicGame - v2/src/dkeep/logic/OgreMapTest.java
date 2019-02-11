//package dkeep.logic;
//
//public final class OgreMapTest extends LevelTwo {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -7349597634503963013L;
//
//	public OgreMapTest(String ogresNumber) {
//		super(ogresNumber);
//	}
//
//	public OgreMapTest(char[][] map) {
//		super(map);
//	}
//
//	@Override
//	protected Position moveOgre(Ogre ogre) {
//		isMovePossible(ogre.getPosition());
//		return ogre.getPosition();
//	}
//
//	@Override
//	protected Position generateClubPosition(Ogre ogre) {
//		Position pos = new Position(ogre.getPosition().getX() - 1, ogre.getPosition().getY());
//
//		return pos;
//	}
//
//}
