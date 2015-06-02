package com.example.joakes.xbox_sidekick;

import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by joakes on 4/28/15.
 */

//TODO add test and log errors
public class JsonAdapter {
    @Inject
    public JsonAdapter() {}

    public XboxProfile toProfile(JSONObject json) {
        return new XboxProfile(
                getFieldAsString(json, "Gamertag"),
                getFieldAsInt(json, "Gamerscore"),
                getFieldAsString(json, "GameDisplayPicRaw"));
    }

    public XboxGame toXboxOneGame(JSONObject json){
        return new XboxGame(
                1,
                getFieldAsString(json, "name"),
                getFieldAsInt(json, "earnedAchievements"),
                -1,
                getFieldAsInt(json, "currentGamerscore"),
                getFieldAsInt(json, "maxGamerscore"),
                XboxGame.XBOX_ONE);
    }

    public XboxGame toXbox360Game(JSONObject json){
        return new XboxGame(
                1,
                getFieldAsString(json, "name"),
                getFieldAsInt(json, "currentAchievements"),
                getFieldAsInt(json, "totalAchievements"),
                getFieldAsInt(json, "currentGamerscore"),
                getFieldAsInt(json, "totalGamerscore"),
                XboxGame.XBOX_360);
    }

    private String getFieldAsString(JSONObject json, String fieldName) {
        return (String) getField(json, fieldName);
    }

    private int getFieldAsInt(JSONObject json, String fieldName) {
        return (int) getField(json, fieldName);
    }

    private Object getField(JSONObject json, String fieldName) {
        try {
            return json.get(fieldName);
        } catch (JSONException|ClassCastException e) {
            return -1;
        }
    }
}
