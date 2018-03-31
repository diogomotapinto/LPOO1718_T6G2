package dkeep.test;

import dkeep.controller.Controller;
import dkeep.logic.OgreMapTest;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dkeep.logic.model.Position;

import org.junit.Test;

public class TestOgreGameLogic {
  private OgreMapTest map;
  public char [][] testMap = {
			{'X','X', 'X' ,'X','X'},
			{'I',' ', ' ' ,'k','X'},
			{'X',' ', ' ' ,' ','X'},
			{'X','A', ' ' ,'O','X'},
			{'X','X', 'X' ,'X','X'}
	};

  @Test
  public void testMoveHeroIntoTo() {
    Position ogrePosition = new Position(7, 4);
    map = new OgreMapTest("1");
    map.play('d');
    map.play('d');
    assertEquals((byte) 0, map.checkEndLevel());
  }

  
  @Test
  public void testMoveHeroIntoTo2() {
    map = new OgreMapTest(testMap);
    map.play('d');
    assertEquals((byte) -1, map.checkEndLevel());
  }
  
  @Test
  public void testChangeToK() {
    map = new OgreMapTest("1");
    assertEquals(new Position(8, 1), map.getHero().getPosition());
    StringBuilder s = new StringBuilder();
    s.append("wwwwwwdddddda");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }

    assertEquals('K', map.getHero().getCharHeroKey());
    assertEquals((byte) 0, map.checkEndLevel());
  }

  @Test
  public void testChangeToK2() {
	    map = new OgreMapTest(testMap);
	    assertEquals(new Position(3, 1), map.getHero().getPosition());
	    StringBuilder s = new StringBuilder();
	    s.append("wwddda");

	    while (s.length() > 0) {
	      map.play(s.charAt(0));
	      s.deleteCharAt(0);
	    }

	    assertEquals('K', map.getHero().getCharHeroKey());
	    assertEquals((byte) 0, map.checkEndLevel());
	  }

  
  @Test
  public void movesToCloseDoor() {
    map = new OgreMapTest("1");
    StringBuilder s = new StringBuilder();
    s.append("wwwwwww");
    
    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals('I', map.getPlayMap()[1][0]);
    assertEquals((byte) 0, map.checkEndLevel());
  }
  
  @Test
  public void movesToCloseDoor2() {
    map = new OgreMapTest(testMap);
    StringBuilder s = new StringBuilder();
    s.append("ww");
    
    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals('I', map.getPlayMap()[1][0]);
    assertEquals((byte) 0, map.checkEndLevel());
  }


  @Test
  public void movesToOpenDoor() {
    map = new OgreMapTest("1");

    StringBuilder s = new StringBuilder();
    s.append("wwwwwwddddddaaaaaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals('I', map.getPlayMap()[1][0]);
    assertEquals((byte) 0, map.checkEndLevel());
  }

  @Test
  public void movesWin() {
    map = new OgreMapTest("1");

    StringBuilder s = new StringBuilder();
    s.append("wwwwwwwddddddddaaaaaaaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals((byte) 1, map.checkEndLevel());
  }

}
