package com.example.joakes.xbox_sidekick;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
public class GameListRequest {
    @Inject
    EventBus eventBus;
    @Inject
    RequestQueue requestQueue;
    @Inject
    JsonAdapter jsonAdapter;

    @Inject
    public GameListRequest() {}

    // TODO better error handling and logging
    public void makeRequest() {
        String url = "https://xboxapi.com/v2/2533274912330216/xboxonegames";
        XboxApiRequest request = new XboxApiRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<XboxGame> games = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("titles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                XboxGame game = jsonAdapter.toXboxGame(jsonArray.getJSONObject(i));
                                games.add(game);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        eventBus.post(games);
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
