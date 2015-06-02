package com.example.joakes.xbox_sidekick.requests;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.joakes.xbox_sidekick.JsonAdapter;
import com.example.joakes.xbox_sidekick.models.XboxProfile;

import org.json.JSONObject;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 4/28/15.
 */
public class ProfileRequest {
    @Inject
    EventBus eventBus;
    @Inject
    RequestQueue requestQueue;
    @Inject
    JsonAdapter jsonAdapter;

    @Inject
    public ProfileRequest(){}

    // TODO abstract out into class
    public void makeRequest() {
        String url = "https://xboxapi.com/v2/2533274912330216/profile";
        XboxApiRequest request = new XboxApiRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        XboxProfile profile = jsonAdapter.toProfile(response);
                        eventBus.post(profile);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(getClass().getName(), error.getMessage());
                    }
                }
        );
        requestQueue.add(request);
    }
}
