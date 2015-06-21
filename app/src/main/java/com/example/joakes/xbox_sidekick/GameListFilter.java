package com.example.joakes.xbox_sidekick;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.Game;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListFilter {
    private ArrayList<Game> xboxGames;

    public GameListFilter(ArrayList<Game> xboxGames) {
        this.xboxGames = xboxGames;
    }

    public ArrayList<Game> filter() {
        Log.i("to be filtered", xboxGames.toString());
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
        }
    }

    private boolean invalidGamerscore(Game game) {
        return game.getEarnedGamerscore() < 1;
    }

    private boolean invalidName(Game game) {
        return game.getName() == null || game.getName().isEmpty();
    }
}
