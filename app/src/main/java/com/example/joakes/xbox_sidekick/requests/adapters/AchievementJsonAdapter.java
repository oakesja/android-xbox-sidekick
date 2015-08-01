package com.example.joakes.xbox_sidekick.requests.adapters;

import android.util.Log;

import com.example.joakes.xbox_sidekick.models.Achievement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by joakes on 4/28/15.
 */

public class AchievementJsonAdapter extends JsonAdapter {
    private final String TAG = getClass().getName();

    public Achievement toAchievement(JSONObject json, String gameName) {
        return new Achievement(
                getFieldAsInt(json, "id"),
                getFieldAsString(json, "name"),
                gameName,
                getFieldAsBoolean(json, "isSecret"),
                getFieldAsString(json, "description"),
                getFieldAsString(json, "lockedDescription"),
                getAchievementValue(json),
                getAchievementIcon(json),
                getIsLocked(json),
                getAchievementDate(json));
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
            Log.e(TAG, String.format(ERROR_FORMAT, "xbox one achievement icon", json));
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
            Log.e(TAG, String.format(ERROR_FORMAT, "xbox one achievement value", json));
            return -1;
        }
    }

    private boolean getIsLocked(JSONObject json) {
        String state = getFieldAsString(json, "progressState");
        return state.isEmpty() ? !getFieldAsBoolean(json, "unlocked") : !state.equals("Achieved");
    }

    private GregorianCalendar getAchievementDate(JSONObject json) {
        GregorianCalendar date = getXboxOneAchievementDate(json);
        return date == null ? getXbox360AchievementDate(json) : date;
    }

    private GregorianCalendar getXboxOneAchievementDate(JSONObject json) {
        String dateString = "";
        try {
            JSONObject progression = json.getJSONObject("progression");
            dateString = progression.getString("timeUnlocked");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("EDT")); // TODO hack
            return parseCalendar(dateString, dateFormat);
        } catch (JSONException e) {
            Log.e(TAG, String.format(ERROR_FORMAT, "timeUnlocked", json));
        }
        return null;
    }

    private GregorianCalendar getXbox360AchievementDate(JSONObject json) {
        String dateString = "";
        try {
            dateString = json.getString("timeUnlocked");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return parseCalendar(dateString, dateFormat);
        } catch (JSONException e) {
            Log.e(TAG, String.format(ERROR_FORMAT, "timeUnlocked", json));
        }
        return null;
    }
}
