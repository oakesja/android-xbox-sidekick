package com.example.joakes.xbox_sidekick.requests.adapters;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.JsonSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.models.Profile;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.GregorianCalendar;

/**
 * Created by joakes on 6/14/15.
 */
public class ProfileJsonAdapterTest extends AndroidTestCase {
    private ProfileJsonAdapter jsonAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jsonAdapter = new ProfileJsonAdapter();
    }

    public void testToProfile() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.profileJson());
        Profile expected = new Profile(
                "PoizonOakes92",
                37963,
                "http://images-eds.xboxlive.com/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png");
        Profile actual = jsonAdapter.toProfile(json);
        assertEquals(expected, actual);
    }

    public void testDefaults() throws JSONException {
        JSONObject json = new JSONObject("{}");
        Profile expected = new Profile("", -1, "");
        Profile actual = jsonAdapter.toProfile(json);
        assertEquals(expected, actual);
    }
}
