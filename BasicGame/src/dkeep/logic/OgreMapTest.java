package dkeep.logic;

import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;

public final class OgreMapTest extends KeepMap {

	public OgreMapTest(int ogresNumber) {
		super(ogresNumber);
		
	}

	@Override
	protected Position moveOgre(Ogre ogre) {
		return ogre.getPosition();
	}

}
