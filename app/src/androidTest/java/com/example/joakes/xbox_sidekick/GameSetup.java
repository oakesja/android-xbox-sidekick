package com.example.joakes.xbox_sidekick;

import com.example.joakes.xbox_sidekick.models.XboxGame;

/**
 * Created by joakes on 6/3/15.
 */
public class GameSetup {

    public static XboxGame createGame(){
        return new XboxGame(1, "game", 10, 100, 100, 1000, XboxGame.XBOX_360);
    }
}
