package com.example.joakes.xbox_sidekick;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.views.ImageTextView;
import com.robotium.solo.Solo;

import junit.framework.Assert;

import static org.assertj.android.api.Assertions.assertThat;
import static com.example.joakes.xbox_sidekick.helpers.TextViewAssert.assertTextView;

/**
 * Created by joakes on 5/17/15.
 */
public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {
    private Solo solo;

    public GameActivityTest() {
        super(GameActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        solo.unlockScreen();
        solo.assertCurrentActivity("Should start on GameActivity", GameActivity.class);
    }

    public void testProfileVisible() {
        String profileName = "PoizonOakes92";
        Assert.assertTrue(solo.searchText(profileName));
        assertTextView((TextView) solo.getView(R.id.gamerscore_image_textview)).hasAnyText();
        assertThat(solo.getView(R.id.gamer_picture)).isVisible();
    }

    public void testGamesVisible() {
        assertTextView((TextView) solo.getView(R.id.game_name_textview)).hasAnyText();
        assertTextView((ImageTextView) solo.getView(R.id.gamerscore_image_textview)).hasAnyText();
        assertTextView((TextView) solo.getView(R.id.game_achievements_image_textview)).hasAnyText();
    }
}
