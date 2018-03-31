package dkeep.logic;

import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;

public final class OgreMapTest extends KeepMap {
	
	
	

	public OgreMapTest(String ogresNumber) {
		super(ogresNumber);
	}

	public OgreMapTest(char [][] map) {
		super(map);
	} 
	
	
	
	@Override
	protected Position moveOgre(Ogre ogre) {
		canMove(ogre.getPosition());
		return ogre.getPosition();
	}

}
