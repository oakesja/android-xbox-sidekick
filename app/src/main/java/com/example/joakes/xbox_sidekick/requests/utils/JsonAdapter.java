package com.example.joakes.xbox_sidekick.requests.utils;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joakes on 4/28/15.
 */

//TODO add test and log errors
public class JsonAdapter {
    private String mTag = getClass().getName();
    private String mErrorFormat = "Could not parse %s from %s";

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
                getAchievementValue(json),
                getAchievementIcon(json),
                getIsLocked(json));
    }

    private String getFieldAsString(JSONObject json, String fieldName) {
        try {
            return json.getString(fieldName);
        } catch (JSONException e) {
            Log.e(mTag, String.format(mErrorFormat, fieldName, json));
            return "";
        }
    }

    private int getFieldAsInt(JSONObject json, String fieldName) {
        try {
            return json.getInt(fieldName);
        } catch (JSONException e) {
            Log.e(mTag, String.format(mErrorFormat, fieldName, json));
            return -1;
        }
    }

    private boolean getFieldAsBoolean(JSONObject json, String fieldName) {
        try {
            return json.getBoolean(fieldName);
        } catch (JSONException e) {
            Log.e(mTag, String.format(mErrorFormat, fieldName, json));
            return false;
        }
    }

    private String getAchievementIcon(JSONObject json) {
        String url = getXboxOneAchievementIcon(json);
        return url.isEmpty() ? getFieldAsString(json, "imageUnlocked") : url;
    }

    private String getXboxOneAchievementIcon(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray("mediaAssets");
            JSONObject object = array.getJSONObject(0);
            return getFieldAsString(object, "url");
        } catch (JSONException e) {
            Log.e(mTag, String.format(mErrorFormat, "xbox one achievement icon", json));
            return "";
        }
    }

    private int getAchievementValue(JSONObject json) {
        int value = getXboxOneAchievementValue(json);
        return value == -1 ? getFieldAsInt(json, "gamerscore") : value;
    }

    private int getXboxOneAchievementValue(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray("rewards");
            JSONObject object = array.getJSONObject(0);
            return getFieldAsInt(object, "value");
        } catch (JSONException e) {
            Log.e(mTag, String.format(mErrorFormat, "xbox one achievement value", json));
            return -1;
        }
    }

    private boolean getIsLocked(JSONObject json) {
        String state = getFieldAsString(json, "progressState");
        return state.isEmpty() ? !getFieldAsBoolean(json, "unlocked") : !state.equals("Achieved");
    }
}
