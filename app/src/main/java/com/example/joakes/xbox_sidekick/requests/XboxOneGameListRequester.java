package com.example.joakes.xbox_sidekick.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.joakes.xbox_sidekick.JsonAdapter;
import com.example.joakes.xbox_sidekick.models.XboxGame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/26/15.
 */
public class XboxOneGameListRequester extends JSONObjectRequester {
    private static final String URL = "https://xboxapi.com/v2/2533274912330216/xboxonegames";

    public XboxOneGameListRequester(Context context, String tag) {
        super(context, URL, tag, XboxApiHeaders.getHeaders());
    }

    @Override
    public void handleSuccess(JSONObject response) {
        ArrayList<XboxGame> games = new ArrayList<>();
        try {
            JSONArray jsonArray = response.getJSONArray("titles");
            for (int i = 0; i < jsonArray.length(); i++) {
                XboxGame game = new JsonAdapter().toXboxOneGame(jsonArray.getJSONObject(i));
                games.add(game);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EventBus.getDefault().post(games);
    }

    @Override
    public void handleError(VolleyError error) {
        Log.e(getClass().getName(), error.getMessage());
    }
}
