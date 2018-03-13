package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.model.Club;
import dkeep.logic.model.ClubHolder;
import dkeep.logic.model.Lever;
import dkeep.logic.model.Ogre;
import dkeep.logic.model.Position;
import utilities.Utilities;

public final class OgreMap extends Map {

	private static final char OGRE_CHAR = 'O';
	private static final char CLUB_CHAR = '*';

	private Ogre ogre;
	private ArrayList<Ogre> ogreList;

	public OgreMap() {
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
				"Nivel 2!!!", 7, 1);

		generateFoes();
		super.header = "\nNumero de ogres= " + ogreList.size();
		initializeMap();
	}

	private final void moveOgre(Ogre ogre) {
		Position ogrePosition = ogre.getPosition();

		isStunned(ogre, ogrePosition);

		playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = CHAR_BLANK_SPACE;

		Position newPosition;
		do {
			newPosition = Utilities.getAdjacentPosition(ogrePosition.getXPosition(), ogrePosition.getYPosition());
		} while (!(playMap[newPosition.getXPosition()][newPosition.getYPosition()] == CHAR_BLANK_SPACE
				|| playMap[newPosition.getXPosition()][newPosition.getYPosition()] == Lever.getLeverChar()));

		ogre.setPosition(newPosition);
		ogrePosition = ogre.getPosition();
		playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = OGRE_CHAR;

		// mudar club para $ qnd estivesse em cima da lever
		if (ogrePosition.getXPosition() == 1 && ogrePosition.getYPosition() == 7) {
			if (!this.lever.isActivated()) {
				playMap[1][7] = '$';
			}
		} else {
			playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = OGRE_CHAR;
			if (!this.lever.isActivated()) {
				playMap[1][7] = Lever.getLeverChar();
			}
		}
	}

	private final boolean isStunned(Ogre ogre, Position ogrePosition) {
		if (ogre.getStunned()) {
			if (ogre.getStunCounter() > 0) {
				ogre.stunCounter();
				playMap[ogrePosition.getXPosition()][ogrePosition.getYPosition()] = '8';
				return true;
			} else {
				ogre.setStunned(false);
				return false;
			}
		}
		return false;
	}

	private final void moveClub(ClubHolder clubHolder) {
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
				|| playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] == Lever.getLeverChar()
				|| checkValidPosition(newClubPosition));

		clubHolder.getClub().setPosition(newClubPosition);
		playMap[newClubPosition.getXPosition()][newClubPosition.getYPosition()] = CLUB_CHAR;

	}

	private boolean checkValidPosition(Position position) {

		for (int i = 0; i < this.ogreList.size(); i++) {
			if (position.equals(this.ogreList.get(i).getPosition())
					&& position.hashCode() == this.ogreList.get(i).getPosition().hashCode()) {
				return true;
			}
		}

		return position.equals(this.hero.getPosition()) && position.hashCode() == this.hero.getPosition().hashCode();
	}

	private void checkIfStunned() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (Utilities.checkAdjacentCollision(this.ogreList.get(i).getPosition(),
					this.hero.getClub().getPosition())) {
				ogreList.get(i).setStunned(true);
			}
		}
	}

	// corrigido porque se perdia o jogo se passar numa posi��o adjacente ao
	// ogre
	// mas com ele atordoado
	private final boolean checkOgreCollision() {
		for (int i = 0; i < ogreList.size(); i++) {
			if (!ogre.getStunned()
					&& Utilities.checkAdjacentCollision(this.hero.getPosition(), this.ogreList.get(i).getPosition())) {
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
			moveOgre(ogreList.get(i));
			moveClub(ogreList.get(i));
		}
		moveClub(hero);
		Position heroPosition = hero.getPosition();
		playMap[heroPosition.getXPosition()][heroPosition.getYPosition()] = this.hero
				.getHeroChar(this.lever.isActivated());
	}

	@Override
	protected void generateFoes() {
		ogreList = new ArrayList<Ogre>();
		int ogresNumber = Utilities.generateRandomNumber(1, 1);
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
		return checkOgreCollision() || checkClubCollision();
	}

	@Override
	public void play(char move) {
		super.moveHero(move);
		moveClub(this.hero);
		super.checkLever();
		playMap[this.hero.getPosition().getXPosition()][this.hero.getPosition().getYPosition()] = this.hero
				.getHeroChar(this.lever.isActivated());
		for (int i = 0; i < ogreList.size(); i++) {
			moveOgre(ogreList.get(i));
			moveClub(ogreList.get(i));
		}
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

	@Override
	public Map nextLevel() {
		return null;
	}

}
