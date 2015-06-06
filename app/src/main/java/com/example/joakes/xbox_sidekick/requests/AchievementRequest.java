package com.example.joakes.xbox_sidekick.requests;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.joakes.xbox_sidekick.BaseApplication;
import com.example.joakes.xbox_sidekick.JsonAdapter;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/26/15.
 */
public class AchievementRequest {
    @Inject
    EventBus eventBus;
    @Inject
    RequestQueue requestQueue;
    @Inject
    JsonAdapter jsonAdapter;

    private XboxGame mGame;

    @Inject
    public AchievementRequest(XboxGame game) {
        mGame = game;
        BaseApplication.component().inject(this);
    }

    // TODO better error handling and logging
    public void makeRequest() {
        //TODO escape the title id to be safe
        final String url = "https://xboxapi.com/v2/2533274912330216/achievements/" + mGame.getTitleId();
        final XboxApiStringRequest request = new XboxApiStringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Achievement> achievements = new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Achievement game = jsonAdapter.toAchievement(jsonArray.getJSONObject(i));
                                achievements.add(game);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        eventBus.post(achievements);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(getClass().getName(), url);
                        Log.e(getClass().getName(), error.getMessage());
                    }
                }
        );
        requestQueue.add(request);
    }
}
