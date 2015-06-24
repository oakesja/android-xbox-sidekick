package com.example.joakes.xbox_sidekick.requests.adapters;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.helpers.JsonSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.GregorianCalendar;

/**
 * Created by joakes on 6/14/15.
 */
public class AchievementJsonAdapterTest extends AndroidTestCase {
    private AchievementJsonAdapter jsonAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jsonAdapter = new AchievementJsonAdapter();
    }

    public void testToAchievementForXboxOne() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xboxOneAchievementJson());
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(1432007682520L);
        Achievement expected = new Achievement(
                6L,
                "Lilac and Gooseberries",
                true,
                "Find Yennefer of Vengerberg.",
                "Find Yennefer of Vengerberg.",
                15,
                "http://images-eds.xboxlive.com/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png",
                false,
                date);
        Achievement actual = jsonAdapter.toAchievement(json);
        assertEquals(expected, actual);
    }

    public void testToAchievementForXbox360() throws JSONException {
        JSONObject json = new JSONObject(JsonSetup.xbox360AchievementJson());
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(1393738410000L);
        Achievement expected = new Achievement(
                5L,
                "Napalm in the Morning",
                false,
                "You have won a battle!",
                "Defeat an enemy unit",
                3,
                "http://image.xboxlive.com/global/t.545407e5/ach/0/15",
                false,
                date);
        Achievement actual = jsonAdapter.toAchievement(json);
        assertEquals(expected, actual);
    }

    public void testToAchievementForXboxOneDefaults() throws JSONException {
        JSONObject json = new JSONObject("{}");
        Achievement expected = new Achievement(-1, "", false, "", "", -1, "", true, null);
        Achievement actual = jsonAdapter.toAchievement(json);
        assertEquals(expected, actual);
    }
}
