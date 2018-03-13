package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.model.Lever;
import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;
import utilities.Utilities;

public class OgreMapTeste extends OgreMap {
	
	
	
	@Override
	protected Position moveOgre(Ogre ogre) {
		return ogre.getPosition();
	}
	


	
	

}
