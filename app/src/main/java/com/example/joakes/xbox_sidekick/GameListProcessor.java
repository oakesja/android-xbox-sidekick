package com.example.joakes.xbox_sidekick;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListProcessor {
    private ArrayList<Game> games;
    private final String TAG = getClass().getName();

    public GameListProcessor(ArrayList<Game> xboxGames) {
        this.games = xboxGames;
    }

    public ArrayList<Game> filter() {
        Iterator<Game> iterator = games.iterator();
        while (iterator.hasNext()) {
            removeIfInvalid(iterator);
        }
        return games;
    }

    public ArrayList<Game> sortByLastPlayTime() {
        Collections.sort(games, new Comparator<Game>() {
            @Override
            public int compare(Game lhs, Game rhs) {
                return rhs.getLastPlayedTime().compareTo(lhs.getLastPlayedTime());
            }
        });
        return games;
    }

    private void removeIfInvalid(Iterator<Game> iterator) {
        Game game = iterator.next();
        if (invalidName(game) || invalidGamerscore(game)) {
            iterator.remove();
            Log.i(TAG, "filtered out game " + game);
        }
    }

    private boolean invalidGamerscore(Game game) {
        return game.getTotalGamerscore() < 1;
    }

    private boolean invalidName(Game game) {
        return game.getName() == null || game.getName().isEmpty();
    }
}
