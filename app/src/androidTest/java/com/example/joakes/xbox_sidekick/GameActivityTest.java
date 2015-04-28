package com.example.joakes.xbox_sidekick;

import android.test.ActivityInstrumentationTestCase2;

import com.example.joakes.xbox_sidekick.models.XboxProfile;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by joakes on 4/28/15.
 */
public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {

    public GameActivityTest() {
        super(GameActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testProfileDisplayed() {
        XboxProfile profile = new XboxProfile(
                "PoizonOakes92",
                36243,
                "http:\\/\\/images-eds.xboxlive.com\\/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png"
        );
        onView(withId(R.id.profile_name_textview)).check(matches(withText(profile.getGamertag())));
    }
}
