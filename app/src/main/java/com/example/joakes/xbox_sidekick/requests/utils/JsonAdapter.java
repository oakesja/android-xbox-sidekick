package com.example.joakes.xbox_sidekick.requests.utils;

import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joakes on 4/28/15.
 */

//TODO add test and log errors
public class JsonAdapter {

    public XboxProfile toProfile(JSONObject json) {
        return new XboxProfile(
                getFieldAsString(json, "Gamertag"),
                getFieldAsInt(json, "Gamerscore"),
                getFieldAsString(json, "GameDisplayPicRaw"));
    }

    public XboxGame toXboxOneGame(JSONObject json) {
        return new XboxGame(
                getFieldAsInt(json, "titleId"),
                getFieldAsString(json, "name"),
                getFieldAsInt(json, "earnedAchievements"),
                -1,
                getFieldAsInt(json, "currentGamerscore"),
                getFieldAsInt(json, "maxGamerscore"),
                XboxGame.XBOX_ONE);
    }

    public XboxGame toXbox360Game(JSONObject json) {
        return new XboxGame(
                getFieldAsInt(json, "titleId"),
                getFieldAsString(json, "name"),
                getFieldAsInt(json, "currentAchievements"),
                getFieldAsInt(json, "totalAchievements"),
                getFieldAsInt(json, "currentGamerscore"),
                getFieldAsInt(json, "totalGamerscore"),
                XboxGame.XBOX_360);
    }

    public Achievement toAchievement(JSONObject json) {
        return new Achievement(
                getFieldAsInt(json, "id"),
                getFieldAsString(json, "name"),
                getFieldAsBoolean(json, "isSecret"),
                getFieldAsString(json, "description"),
                getFieldAsString(json, "lockedDescription"),
                -1,
                null);
    }

    private String getFieldAsString(JSONObject json, String fieldName) {
        return (String) getField(json, fieldName);
    }

    private int getFieldAsInt(JSONObject json, String fieldName) {
        return (int) getField(json, fieldName);
    }

    private boolean getFieldAsBoolean(JSONObject json, String fieldName) {
        return (boolean) getField(json, fieldName);
    }

    private Object getField(JSONObject json, String fieldName) {
        try {
            return json.get(fieldName);
        } catch (JSONException | ClassCastException e) {
            return -1;
        }
    }
}
