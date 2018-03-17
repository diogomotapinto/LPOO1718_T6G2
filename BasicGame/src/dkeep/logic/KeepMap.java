package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.model.Club;
import dkeep.logic.model.Lever;
import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;
import utilities.Utilities;

public class KeepMap extends Map {
	private static final char OGRE_CHAR = 'O';
	private static final char CLUB_CHAR = '*';
//	private int ogresNumber;
	private Ogre ogre;
	private ArrayList<Ogre> ogreList;
	

	public KeepMap(String ogresNumber) {
		// passes map and legend as argument
		super(new char[][] {
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
				{ CHAR_DOOR_CLOSED, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, Lever.getLeverChar(), WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },

				{ WALL_CHAR, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, CHAR_BLANK_SPACE,
						CHAR_BLANK_SPACE, CHAR_BLANK_SPACE, WALL_CHAR },
				{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR } },
				"\nX - Wall \nI - Exit Door \nH - Hero \nO - Crazy Ogre \nk - key \nempty cell - free space",
				"Nivel 2!!!", 7, 1, 3, 7);

//		this.ogresNumber = ogresNumber;
		generateFoes(ogresNumber);
		super.header = "\nNumero de ogres= " + ogreList.size();
		initializeMap();
	}

	protected Position moveOgre(Ogre ogre) {
		Position ogrePosition = ogre.getPosition();

		if (isStunned(ogre, ogrePosition)) {
			return ogre.getPosition();
		}

		playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = CHAR_BLANK_SPACE;
		
		if(canMove(ogre.getPosition())) {
			return ogre.getPosition();
		}
		
		Position newPosition;
		do {
			newPosition = Utilities.getAdjacentPosition(ogrePosition.getXPosition(), ogrePosition.getYPosition());
		} while (!(playMap[newPosition.getXPosition()][newPosition.getYPosition()] == CHAR_BLANK_SPACE
				|| playMap[newPosition.getXPosition()][newPosition.getYPosition()] == Lever.getLeverChar()));

		return newPosition;
	}

	private final void setOgre(Position ogrePosition, Ogre ogre) {

		ogre.setPosition(ogrePosition);

		// playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] =
		// ogre.getOgreChar();

		// mudar club para $ qnd estivesse em cima da lever
		if (ogrePosition.equals(lever.getPosition())) {
			if (!this.hero.getLeverState()) {
				playMap[1][7] = '$';
			}
		} else {
			playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = ogre.getOgreChar();
		}

	}
	public final boolean canMove(Position position) {
		int counter = 0;
		
		//checks above
		if(playMap[position.getXPosition()-1][position.getYPosition()] != CHAR_BLANK_SPACE
				|| playMap[position.getXPosition()-1][position.getYPosition()] != Lever.getLeverChar()
				|| playMap[position.getXPosition()-1][position.getYPosition()] != ogre.getOgreChar()) {
			counter++;
		}

		//checks bellow
		if(playMap[position.getXPosition()+1][position.getYPosition()] != CHAR_BLANK_SPACE
				|| playMap[position.getXPosition()+1][position.getYPosition()] != Lever.getLeverChar()
				|| playMap[position.getXPosition()+1][position.getYPosition()] != ogre.getOgreChar()) {
			counter++;
		}
		//checks right
		if(playMap[position.getXPosition()][position.getYPosition()+1] != CHAR_BLANK_SPACE
				|| playMap[position.getXPosition()][position.getYPosition()+1] != Lever.getLeverChar()
				|| playMap[position.getXPosition()][position.getYPosition()+1] != ogre.getOgreChar()) {
			counter++;
		}

		//checks left
		if(playMap[position.getXPosition()][position.getYPosition()-1] != CHAR_BLANK_SPACE
				|| playMap[position.getXPosition()][position.getYPosition()-1] != Lever.getLeverChar()
				|| playMap[position.getXPosition()][position.getYPosition()-1] != ogre.getOgreChar()) {
			counter++;
		}
		
		if(counter == 4) {
			return false;
		}else
		{
			return true;
		}
	}

	private final boolean isStunned(Ogre ogre, Position ogrePosition) {
		if (ogre.getStunned()) {
			if (ogre.getStunCounter() > 0) {
				ogre.stunCounter();
				// playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = '8';
				return true;
			} else {
				ogre.setStunned(false);
				return false;
			}
		}
		return false;
	}

	private final void moveClub(Ogre clubHolder) {
		Position clubPosition = clubHolder.getClub().getPosition();

		playMap[clubPosition.getXPosition()][clubPosition.getYPosition()] = CHAR_BLANK_SPACE;

		Position newClubPosition;
		do {
			newClubPosition = Utilities.getAdjacentPosition(clubHolder.getPosition().getXPosition(),
					clubHolder.getPosition().getYPosition());
		} while (playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == WALL_CHAR
				|| playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == CHAR_DOOR_CLOSED
				|| playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == CHAR_DOOR_OPEN
				|| playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == Club.getClubChar()
				|| checkValidPosition(newClubPosition));

		clubHolder.getClub().setPosition(newClubPosition);

		// mudar club para $ qnd estivesse em cima da lever
		if (newClubPosition.equals(lever.getPosition())) {
			if (!this.hero.getLeverState()) {
				playMap[1][7] = '$';
			}
		} else {
			playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] = CLUB_CHAR;
		}
		

	}

	private final boolean checkValidPosition(Position position) {

		for (int i = 0; i < this.ogreList.size(); i++) {
			if (position.equals(this.ogreList.get(i).getPosition())
					&& position.hashCode() == this.ogreList.get(i).getPosition().hashCode()) {
				return true;
			}
		}

		return position.equals(this.hero.getPosition()) && position.hashCode() == this.hero.getPosition().hashCode();
	}

	private final void checkIfStunned() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.ogreList.get(i).getPosition(), this.hero.getPosition())) {
				ogreList.get(i).setStunned(true);
			}
		}
	}

	// corrigido porque se perdia o jogo se passar numa posi��o adjacente ao
	// ogre
	// mas com ele atordoado
	private final boolean checkOgreCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(), this.ogreList.get(i).getPosition())) {
				ogreList.get(i).setStunned(true);
				return true;
			}
		}
		return false;
	}

	// corrigido porque se perdia o jogo se passar numa posi��o adjacente ao
	// ogre
	// mas com ele atordoado
	private final boolean checkClubCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.hero.getPosition(),
					this.ogreList.get(i).getClub().getPosition())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void initializeMap() {
		for (int i = 0; i < ogreList.size(); i++) {
			setOgre(moveOgre(ogreList.get(i)), ogreList.get(i));
			moveClub(ogreList.get(i));
		}
		Position heroPosition = hero.getPosition();
		playMap[heroPosition.getXPosition()][heroPosition.getYPosition()] = this.hero.getCharHeroLvl2();
	}

	

	@Override
	protected void generateFoes(String numberOfOgres) {
		ogreList = new ArrayList<Ogre>();
		int ogresNumber = Integer.parseInt(numberOfOgres);
		Position leverPos = new Position(1, 7);
		lever.setPosition(leverPos);
		int x;
		int y;
		for (int i = 0; i < ogresNumber; i++) {
			do {
				 x = Utilities.generateRandomNumber(1, 5);
				 y = Utilities.generateRandomNumber(1, 5);
				ogre = new Ogre(x, y);
			} while (playMap[x][y] != CHAR_BLANK_SPACE);
			ogreList.add(ogre);
		}
	}

	@Override
	protected boolean checkLost() {
		return checkClubCollision();
	}

	@Override
	public void play(char move) {
		super.moveHero(move, super.lever.isActivated() ?  hero.getCharHeroKey() : hero.getCharHeroLvl2());
		super.checkLever();
		for (int i = 0; i < ogreList.size(); i++) {
			// moveOgre(ogreList.get(i));
			
			setOgre(moveOgre(ogreList.get(i)), ogreList.get(i));
			checkOgreCollision();

			moveClub(ogreList.get(i));

		}
		checkLeverMap();
		checkIfStunned();
	}

	@Override
	public byte checkEndLevel() {
		// para terminar basta chegar a um dos cantos
		Position heroPosition = this.hero.getPosition();

		if (checkWon(heroPosition.getYPosition())) {
			return 1;
		}

		// tem de se fazer refactor do codigo
		if (checkLost()) {
			return -1;
		}

		return 0;
	}

	public void checkLeverMap() {
		Position leverPos = lever.getPosition();
		if(playMap[leverPos.getXPosition()][leverPos.getYPosition()] == ' '  && !this.hero.getLeverState()) {
			playMap[leverPos.getXPosition()][leverPos.getYPosition()] = 'k';
		}
	}
	
	@Override
	public Map nextLevel(String info) {
		return null;
	}

}
