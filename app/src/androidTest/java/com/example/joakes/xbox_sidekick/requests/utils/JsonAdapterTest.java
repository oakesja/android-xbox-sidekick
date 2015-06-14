package com.example.joakes.xbox_sidekick.requests.utils;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.JsonSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by joakes on 6/14/15.
 */
public class JsonAdapterTest extends AndroidTestCase {
    private JsonAdapter jsonAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jsonAdapter = new JsonAdapter();
    }

    public void testToXboxOneGame() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xboxOneGameJson());
        XboxGame expected = new XboxGame(1144039928L, "Halo: The Master Chief Collection", 114, -1, 1025, 6000, XboxGame.XBOX_ONE);
        assertEquals(expected, jsonAdapter.toXboxOneGame(json));
    }

    public void testToXbox360() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xbox360GameJson());
        XboxGame expected = new XboxGame(1161889984L, "Dragon Age: Origins", 67, 76, 1440, 1750, XboxGame.XBOX_360);
        assertEquals(expected, jsonAdapter.toXbox360Game(json));
    }

    public void testToProfile() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.profileJson());
        XboxProfile expected = new XboxProfile(
                "PoizonOakes92",
                37963,
                "http://images-eds.xboxlive.com/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png");
        XboxProfile actual = jsonAdapter.toProfile(json);
        assertEquals(expected, actual);
    }

    public void testToAchievementForXboxOne() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xboxOneAchievementJson());
        Achievement expected = new Achievement(
                6L,
                "Lilac and Gooseberries",
                true,
                "Find Yennefer of Vengerberg.",
                "Find Yennefer of Vengerberg.",
                15,
                "http://images-eds.xboxlive.com/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png",
                false,
                new Date(1432007682520L));
        Achievement actual = jsonAdapter.toAchievement(json);
        assertEquals(expected, actual);
    }

    public void testToAchievementForXbox360() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xbox360AchievementJson());
        Achievement expected = new Achievement(
                5L,
                "Napalm in the Morning",
                false,
                "You have won a battle!",
                "Defeat an enemy unit",
                3,
                "http://image.xboxlive.com/global/t.545407e5/ach/0/15",
                false,
                new Date(1393738410000L));
        Achievement actual = jsonAdapter.toAchievement(json);
        assertEquals(expected, actual);
    }
}
