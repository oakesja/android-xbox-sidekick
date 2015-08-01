package com.example.joakes.xbox_sidekick.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.joakes.xbox_sidekick.requests.adapters.AchievementJsonAdapter;
import com.example.joakes.xbox_sidekick.requests.adapters.JsonAdapter;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.requests.utils.JSONArrayRequester;
import com.example.joakes.xbox_sidekick.requests.utils.XboxApiHeaders;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/26/15.
 */
public class AchievementRequester extends JSONArrayRequester {

    private final String TAG = getClass().getName();
    private Game game;

    public AchievementRequester(Context context, String tag, Game game) {
        super(context, getUrl(game), tag, XboxApiHeaders.getHeaders());
        this.game = game;
    }

    private static String getUrl(Game game) {
        return "https://xboxapi.com/v2/2533274912330216/achievements/" + game.getTitleId();
    }

    @Override
    public void handleSuccess(JSONArray response) {
        ArrayList<Achievement> achievements = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                Achievement achievement = new AchievementJsonAdapter().toAchievement(response.getJSONObject(i), game.getName());
                achievements.add(achievement);
            }
        } catch (JSONException e) {
            Log.e(TAG, "could not parse achievemnt json: " + e.getMessage());
        }
        EventBus.getDefault().post(achievements);
    }

    @Override
    public void handleError(VolleyError error) {
        Log.e(TAG, error.getMessage());
    }
}
