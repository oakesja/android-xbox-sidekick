//package com.example.joakes.xbox_sidekick;
//
//import android.app.Activity;
//import android.test.ActivityInstrumentationTestCase2;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//
//import com.example.joakes.xbox_sidekick.views.ImageTextView;
//import com.robotium.solo.Solo;
//
//import junit.framework.Assert;
//
//import static com.example.joakes.xbox_sidekick.helpers.TextViewAssert.assertTextView;
//import static org.assertj.android.api.Assertions.assertThat;
//
///**
// * Created by joakes on 5/17/15.
// */
//public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {
//    private Solo solo;
//
//    public GameActivityTest() {
//        super(GameActivity.class);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        solo = new Solo(getInstrumentation(), getActivity());
//        solo.unlockScreen();
//        solo.assertCurrentActivity("Should start on GameActivity", GameActivity.class);
//        Log.i("setup", "setup");
//    }
//
//    public void testProfileVisible() {
//        String profileName = "PoizonOakes92";
//        Assert.assertTrue(solo.searchText(profileName));
//        assertTextView((TextView) solo.getView(R.id.gamerscore_image_textview)).hasAnyText();
//        assertThat(solo.getView(R.id.gamer_picture)).isVisible();
//    }
//
//    public void testGamesVisible() {
//        assertTextView((TextView) solo.getView(R.id.game_name_textview)).hasAnyText();
//        Assert.assertNotSame("", ((ImageTextView) solo.getView(R.id.gamerscore_image_textview)).getText());
//        assertTextView((TextView) solo.getView(R.id.game_achievements_image_textview)).hasAnyText();
//    }
//
//    public void testClickingOnGame(){
//        View v = solo.getView(R.id.game_name_textview, 0);
//        solo.clickOnView(v);
//        solo.assertCurrentActivity("Should go to achievements activity", AchievementsActivity.class);
//    }
//}
