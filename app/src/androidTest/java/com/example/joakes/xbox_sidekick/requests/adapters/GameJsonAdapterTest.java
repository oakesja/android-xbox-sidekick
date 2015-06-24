package com.example.joakes.xbox_sidekick.requests.adapters;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.JsonSetup;
import com.example.joakes.xbox_sidekick.models.Game;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joakes on 6/14/15.
 */
public class GameJsonAdapterTest extends AndroidTestCase {
    private GameJsonAdapter jsonAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jsonAdapter = new GameJsonAdapter();
    }

    public void testToXboxOneGame() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xboxOneGameJson());
        Game expected = new Game(1144039928L, "Halo: The Master Chief Collection", 114, -1, 1025, 6000, Game.XBOX_ONE);
        assertEquals(expected, jsonAdapter.toXboxOneGame(json));
    }

    public void testToXboxOneGameDefaults() throws JSONException {
        JSONObject json = new JSONObject("{}");
        Game expected = new Game(-1, "", -1, -1, -1, -1, Game.XBOX_ONE);
        assertEquals(expected, jsonAdapter.toXboxOneGame(json));
    }

    public void testToXbox360() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xbox360GameJson());
        Game expected = new Game(1161889984L, "Dragon Age: Origins", 67, 76, 1440, 1750, Game.XBOX_360);
        assertEquals(expected, jsonAdapter.toXbox360Game(json));
    }

    public void testToXbox360Defaults() throws JSONException {
        JSONObject json = new JSONObject("{}");
        Game expected = new Game(-1, "", -1, -1, -1, -1, Game.XBOX_360);
        assertEquals(expected, jsonAdapter.toXbox360Game(json));
    }
}
