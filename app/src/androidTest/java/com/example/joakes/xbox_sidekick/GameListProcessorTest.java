package com.example.joakes.xbox_sidekick;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Game;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListProcessorTest extends AndroidTestCase {
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
        ArrayList actual = new GameListProcessor(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    public void testFilterOutMissingTotalGamerscore() {
        xboxGames.get(0).setTotalGamerscore(0);
        ArrayList actual = new GameListProcessor(xboxGames).filter();
        assertEquals(emptyList, actual);
    }

    public void testDoNotFilterOutValidGames() {
        ArrayList actual = new GameListProcessor(xboxGames).filter();
        assertEquals(xboxGames, actual);
    }
}