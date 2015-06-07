package com.example.joakes.xbox_sidekick.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.joakes.xbox_sidekick.requests.utils.JsonAdapter;
import com.example.joakes.xbox_sidekick.models.XboxProfile;
import com.example.joakes.xbox_sidekick.requests.utils.JSONObjectRequester;
import com.example.joakes.xbox_sidekick.requests.utils.XboxApiHeaders;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 4/28/15.
 */
public class ProfileRequester extends JSONObjectRequester {
    private static final String URL = "https://xboxapi.com/v2/2533274912330216/profile";

    public ProfileRequester(Context context, String tag) {
        super(context, URL, tag, XboxApiHeaders.getHeaders());
    }

    @Override
    public void handleSuccess(JSONObject response) {
        XboxProfile profile = new JsonAdapter().toProfile(response);
        EventBus.getDefault().post(profile);
    }

    @Override
    public void handleError(VolleyError error) {
        Log.e(getClass().getName(), error.getMessage());
    }
}