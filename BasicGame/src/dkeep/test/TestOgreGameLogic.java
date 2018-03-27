package dkeep.test;

import dkeep.logic.OgreMapTest;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dkeep.logic.Controller;
import dkeep.logic.model.Position;

import org.junit.Test;

public class TestOgreGameLogic {
  private OgreMapTest map;

  @Test
  public void testMoveHeroIntoTo() {
    Position ogrePosition = new Position(7, 4);
    map = new OgreMapTest("1");
    map.play('d');
    map.play('d');
    assertEquals((byte) 0, map.checkEndLevel());
  }

  @Test
  public void testChangeToK() {
    map = new OgreMapTest("1");

    StringBuilder s = new StringBuilder();
    s.append("wwwwwwdddddd");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }

    //
    assertEquals((byte) 0, map.checkEndLevel());
  }

  @Test
  public void movesToCloseDoor() {
    map = new OgreMapTest("1");
    StringBuilder s = new StringBuilder();
    s.append("wwwwwwa");

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
    assertEquals('S', map.getPlayMap()[1][0]);
    assertEquals((byte) 0, map.checkEndLevel());
  }

  @Test
  public void movesWin() {
    map = new OgreMapTest("1");

    StringBuilder s = new StringBuilder();
    s.append("wwwwwwddddddaaaaaaa");

    while (s.length() > 0) {
      map.play(s.charAt(0));
      s.deleteCharAt(0);
    }
    assertEquals((byte) 1, map.checkEndLevel());
  }

}
