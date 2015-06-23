package com.example.joakes.xbox_sidekick.helpers;

import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.models.Profile;

import java.util.GregorianCalendar;

/**
 * Created by joakes on 6/3/15.
 */
public class TestSetup {

    public static Game createXboxOneGame() {
        return new Game(1, "xbox one game", 10, 100, 100, 1000, Game.XBOX_ONE);
    }

    public static Game createXbox360Game() {
        return new Game(2, "xbox 360 game", 1, 10, 10, 100, Game.XBOX_360);
    }

    public static Profile createProfile() {
        return new Profile("PoiZonOakes", 36243, "icon/url");
    }

    public static Achievement createAchievement() {
        GregorianCalendar date = new GregorianCalendar(2014, 9, 16);
        return new Achievement(1, "name", false, "description", "locked", 1, "icon/url",
                false, date);
    }
}
