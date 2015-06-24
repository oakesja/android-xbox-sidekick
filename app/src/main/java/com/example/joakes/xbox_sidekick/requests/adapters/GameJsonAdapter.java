package com.example.joakes.xbox_sidekick.requests.adapters;

import com.example.joakes.xbox_sidekick.models.Game;

import org.json.JSONObject;

/**
 * Created by joakes on 4/28/15.
 */

public class GameJsonAdapter extends JsonAdapter{
    public Game toXboxOneGame(JSONObject json) {
        return new Game(
                getFieldAsInt(json, "titleId"),
                getFieldAsString(json, "name"),
                getFieldAsInt(json, "earnedAchievements"),
                -1,
                getFieldAsInt(json, "currentGamerscore"),
                getFieldAsInt(json, "maxGamerscore"),
                Game.XBOX_ONE);
    }

    public Game toXbox360Game(JSONObject json) {
        return new Game(
                getFieldAsInt(json, "titleId"),
                getFieldAsString(json, "name"),
                getFieldAsInt(json, "currentAchievements"),
                getFieldAsInt(json, "totalAchievements"),
                getFieldAsInt(json, "currentGamerscore"),
                getFieldAsInt(json, "totalGamerscore"),
                Game.XBOX_360);
    }
}
