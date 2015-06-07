package com.example.joakes.xbox_sidekick.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.joakes.xbox_sidekick.JsonAdapter;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/26/15.
 */
public class AchievementRequest extends JSONArrayRequester {

    public AchievementRequest(Context context, String tag, XboxGame game) {
        super(context, getUrl(game), tag, XboxApiHeaders.getHeaders());
    }

    private static String getUrl(XboxGame game) {
        return "https://xboxapi.com/v2/2533274912330216/achievements/" + game.getTitleId();
    }

    @Override
    public void handleSuccess(JSONArray response) {
        ArrayList<Achievement> achievements = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                Achievement game = new JsonAdapter().toAchievement(response.getJSONObject(i));
                achievements.add(game);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EventBus.getDefault().post(achievements);
    }

    @Override
    public void handleError(VolleyError error) {
        Log.e(getClass().getName(), error.getMessage());
    }
}
