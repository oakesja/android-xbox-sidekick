package com.example.joakes.xbox_sidekick.requests.adapters;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by joakes on 4/28/15.
 */

public abstract class JsonAdapter {
    private final String TAG = getClass().getName();
    protected final String ERROR_FORMAT = "Could not parse %s from %s";

    protected String getFieldAsString(JSONObject json, String fieldName) {
        try {
            return json.getString(fieldName);
        } catch (JSONException e) {
            Log.e(TAG, String.format(ERROR_FORMAT, fieldName, json));
            return "";
        }
    }

    protected int getFieldAsInt(JSONObject json, String fieldName) {
        try {
            return json.getInt(fieldName);
        } catch (JSONException e) {
            Log.e(TAG, String.format(ERROR_FORMAT, fieldName, json));
            return -1;
        }
    }

    protected boolean getFieldAsBoolean(JSONObject json, String fieldName) {
        try {
            return json.getBoolean(fieldName);
        } catch (JSONException e) {
            Log.e(TAG, String.format(ERROR_FORMAT, fieldName, json));
            return false;
        }
    }

    protected GregorianCalendar parseCalendar(String dateString, SimpleDateFormat dateFormat) {
        Date date = parseDate(dateString, dateFormat);
        return dateToGregorianCalendar(date);
    }

    private Date parseDate(String dateString, SimpleDateFormat dateFormat) {
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            Log.e(TAG, String.format("could not parseCalendar %s %s", dateString, dateFormat.toString()));
            date = null;
        }
        return date;
    }

    private GregorianCalendar dateToGregorianCalendar(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

}
