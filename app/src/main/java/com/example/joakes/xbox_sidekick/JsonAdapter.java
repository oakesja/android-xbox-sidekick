package com.example.joakes.xbox_sidekick;

import com.example.joakes.xbox_sidekick.models.XboxProfile;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joakes on 4/28/15.
 */

//TODO add test and log errors
public class JsonAdapter {
    public static XboxProfile toProfile(JSONObject json) {
        String gamertag = getFieldAsString(json, "Gamertag");
        int gamerScore = getFieldAsInt(json, "Gamerscore");
        String gamerPictureUrl = getFieldAsString(json, "GameDisplayPicRaw");
        return new XboxProfile(gamertag, gamerScore, gamerPictureUrl);
    }

    private static String getFieldAsString(JSONObject json, String fieldName) {
        try {
            return (String) json.get(fieldName);
        } catch (JSONException|ClassCastException e) {
            return null;
        }
    }

    private static int getFieldAsInt(JSONObject json, String fieldName) {
        try {
            return (int) json.get(fieldName);
        } catch (JSONException|ClassCastException e) {
            return -1;
        }
    }
}
