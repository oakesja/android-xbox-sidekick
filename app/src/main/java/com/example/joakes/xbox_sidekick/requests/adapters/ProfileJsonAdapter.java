package com.example.joakes.xbox_sidekick.requests.adapters;

import com.example.joakes.xbox_sidekick.models.Profile;

import org.json.JSONObject;

/**
 * Created by joakes on 4/28/15.
 */

public class ProfileJsonAdapter extends JsonAdapter {
    public Profile toProfile(JSONObject json) {
        return new Profile(
                getFieldAsString(json, "Gamertag"),
                getFieldAsInt(json, "Gamerscore"),
                getFieldAsString(json, "GameDisplayPicRaw"));
    }
}
