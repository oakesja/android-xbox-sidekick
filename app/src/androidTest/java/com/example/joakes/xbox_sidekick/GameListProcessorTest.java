package com.example.joakes.xbox_sidekick;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Game;

import java.util.ArrayList;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListProcessorTest extends AndroidTestCase {
    private ArrayList<Game> singleGameList;
    private ArrayList<Game> emptyList;
    private ArrayList<Game> unsortedGamesList;
    private ArrayList<Game> sortedGamesList;

    @Override
    public void setUp() throws Exception {
        setupForFilter();
        setupForSort();
    }

    private void setupForFilter() {
        Game xboxGame = TestSetup.createXboxOneGame();
        singleGameList = new ArrayList<>();
        singleGameList.add(xboxGame);
        emptyList = new ArrayList<>();
    }

    private void setupForSort() {
        Game game1 = TestSetup.createXboxOneGame();
        game1.getLastPlayedTime().setTimeInMillis(1L);
        Game game2 = TestSetup.createXboxOneGame();
        game2.getLastPlayedTime().setTimeInMillis(2L);
        unsortedGamesList = new ArrayList<>();
        unsortedGamesList.add(game1);
        unsortedGamesList.add(game2);
        sortedGamesList = new ArrayList<>();
        sortedGamesList.add(game2);
        sortedGamesList.add(game1);
    }

    public void testFilterOutEmptyName() {
        singleGameList.get(0).setName("");
        ArrayList actual = new GameListProcessor(singleGameList).filter();
        assertEquals(emptyList, actual);
    }

    public void testFilterOutMissingTotalGamerscore() {
        singleGameList.get(0).setTotalGamerscore(0);
        ArrayList actual = new GameListProcessor(singleGameList).filter();
        assertEquals(emptyList, actual);
    }

    public void testDoNotFilterOutValidGames() {
        ArrayList actual = new GameListProcessor(singleGameList).filter();
        assertEquals(singleGameList, actual);
    }

    public void testSortGamesByLastPlayTime(){
        ArrayList actual = new GameListProcessor(unsortedGamesList).sortByLastPlayTime();
        assertEquals(sortedGamesList, actual);
    }
}