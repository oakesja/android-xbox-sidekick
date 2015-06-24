package com.example.joakes.xbox_sidekick.requests.adapters;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.Game;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by joakes on 4/28/15.
 */

public class GameJsonAdapter extends JsonAdapter{
    private final String TAG = getClass().getName();

    public Game toXboxOneGame(JSONObject json) {
        return new Game(
                getFieldAsInt(json, "titleId"),
                getFieldAsString(json, "name"),
                getFieldAsInt(json, "earnedAchievements"),
                -1,
                getFieldAsInt(json, "currentGamerscore"),
                getFieldAsInt(json, "maxGamerscore"),
                getFieldAsCalendar(json, "lastUnlock"),
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
                getFieldAsCalendar(json, "lastPlayed"),
                Game.XBOX_360);
    }

    public GregorianCalendar getFieldAsCalendar(JSONObject json, String fieldName){
        String dateString;
        try {
            dateString = json.getString(fieldName);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("EDT")); // TODO hack
            return parseCalendar(dateString, dateFormat);
        } catch (JSONException e) {
            Log.e(TAG, String.format(ERROR_FORMAT, "timeUnlocked", json));
        }
        return null;
    }
}
