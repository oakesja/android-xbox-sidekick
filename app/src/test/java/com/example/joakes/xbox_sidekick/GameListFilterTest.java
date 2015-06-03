package com.example.joakes.xbox_sidekick;

import com.example.joakes.xbox_sidekick.models.XboxGame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListFilterTest {
    private ArrayList<XboxGame> xboxGames;
    private ArrayList<XboxGame> emptyList;

    @Before
    public void setUp() throws Exception {
        XboxGame xboxGame = new XboxGame(1, "game", 10, 100, 100, 1000, XboxGame.XBOX_360);
        xboxGames = new ArrayList<>();
        xboxGames.add(xboxGame);
        emptyList = new ArrayList<>();
    }

    @Test
    public void testFilterOutEmptyName() {
        xboxGames.get(0).setName("");
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    @Test
    public void testFilterOutNullName() {
        xboxGames.get(0).setName(null);
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    @Test
    public void testFilterOutMissingEarnedGamerscore() {
        xboxGames.get(0).setEarnedGamerscore(-1);
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    @Test
    public void testFilterOutNoEarnedGamerscore() {
        xboxGames.get(0).setEarnedGamerscore(0);
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    @Test
    public void doNotFilterOutValidGames() {
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(xboxGames, actual);
    }
}