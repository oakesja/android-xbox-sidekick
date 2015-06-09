package com.example.joakes.xbox_sidekick;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.XboxGame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by joakes on 6/2/15.
 */
public class GameListFilter {
    private ArrayList<XboxGame> xboxGames;

    public GameListFilter(ArrayList<XboxGame> xboxGames) {
        this.xboxGames = xboxGames;
    }

    public ArrayList<XboxGame> filter() {
        Log.i("to be filtered", xboxGames.toString());
        Iterator<XboxGame> iterator = xboxGames.iterator();
        while (iterator.hasNext()) {
            removeIfInvalid(iterator);
        }
        return xboxGames;
    }

    private void removeIfInvalid(Iterator<XboxGame> iterator) {
        XboxGame game = iterator.next();
        if (invalidName(game) || invalidGamerscore(game)) {
            iterator.remove();
        }
    }

    private boolean invalidGamerscore(XboxGame game) {
        return game.getEarnedGamerscore() < 1;
    }

    private boolean invalidName(XboxGame game) {
        return game.getName() == null || game.getName().isEmpty();
    }
}
