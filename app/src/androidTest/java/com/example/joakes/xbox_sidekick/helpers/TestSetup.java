package com.example.joakes.xbox_sidekick.helpers;

import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by joakes on 6/3/15.
 */
public class TestSetup {

    public static XboxGame createGame() {
        return new XboxGame(1, "game", 10, 100, 100, 1000, XboxGame.XBOX_360);
    }

    public static XboxProfile createProfile() {
        return new XboxProfile("PoiZonOakes", 36243, "icon/url");
    }

    public static Achievement createAchievement() {
        GregorianCalendar date = new GregorianCalendar(2014, 9, 16);
        return new Achievement(1, "name", false, "description", "locked", 1, "icon/url",
                false, date);
    }
}
