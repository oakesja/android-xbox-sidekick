package com.example.joakes.xbox_sidekick;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Game;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListFilterTest extends AndroidTestCase {
    private ArrayList<Game> xboxGames;
    private ArrayList<Game> emptyList;

    @Override
    public void setUp() throws Exception {
        Game xboxGame = TestSetup.createXboxOneGame();
        xboxGames = new ArrayList<>();
        xboxGames.add(xboxGame);
        emptyList = new ArrayList<>();
    }

    public void testFilterOutEmptyName() {
        xboxGames.get(0).setName("");
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    public void testFilterOutMissingEarnedGamerscore() {
        xboxGames.get(0).setEarnedGamerscore(-1);
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    public void testFilterOutNoEarnedGamerscore() {
        xboxGames.get(0).setEarnedGamerscore(0);
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    public void testDoNotFilterOutValidGames() {
        ArrayList actual = new GameListFilter(xboxGames).filter();
        assertEquals(xboxGames, actual);
    }
}