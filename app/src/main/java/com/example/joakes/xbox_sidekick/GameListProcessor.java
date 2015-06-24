package com.example.joakes.xbox_sidekick;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.Game;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListProcessor {
    private ArrayList<Game> xboxGames;
    private final String TAG = getClass().getName();

    public GameListProcessor(ArrayList<Game> xboxGames) {
        this.xboxGames = xboxGames;
    }

    public ArrayList<Game> filter() {
        Iterator<Game> iterator = xboxGames.iterator();
        while (iterator.hasNext()) {
            removeIfInvalid(iterator);
        }
        return xboxGames;
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
