package com.example.joakes.xbox_sidekick;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.Request;
import com.example.joakes.xbox_sidekick.activities.GameActivity;
import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;
import com.example.joakes.xbox_sidekick.requests.utils.WebRequestQueue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.android.api.Assertions.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class GameActivityUnitTest {
    private XboxProfile profile;
    private GameActivity gameActivity;
    private XboxGame xboxGame;
    private EventBusHelper eventBus;

    @Rule
    public ActivityTestRule<GameActivity> activityRule = new ActivityTestRule<>(GameActivity.class, false, false);

    @Before
    public void setUp() {
        mockWebRequests();
        profile = TestSetup.createProfile();
        xboxGame = TestSetup.createGame();
        activityRule.launchActivity(new Intent());
        gameActivity = activityRule.getActivity();
        eventBus = new EventBusHelper(activityRule);
    }

    @Test
    public void profileNameDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.gamertagView).containsText(profile.getGamertag());
    }

    @Test
    public void nullProfileName() {
        profile.setGamertag(null);
        eventBus.post(profile);
        assertThat(gameActivity.gamertagView).isInvisible();
    }

    @Test
    public void emptyProfileName() {
        profile.setGamertag(null);
        eventBus.post(profile);
        assertThat(gameActivity.gamertagView).isInvisible();
    }

    @Test
    public void profileGamerscoreDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.userGamerscoreView).containsText("" + profile.getGamerscore());
    }

    @Test
    public void invalidGamerscoreDisplayed() {
        profile.setGamerscore(-1);
        eventBus.post(profile);
        assertThat(gameActivity.userGamerscoreView).isGone();
    }

    @Test
    public void profileGamerPictureDisplayed() {
        eventBus.post(profile);
//        Mockito.verify(webService).loadImageFromUrl(gameActivity.userPictureView, profile.getGamerPictureUrl());
        onView(withId(R.id.gamer_picture)).check(matches(isDisplayed()));
    }

    @Test
    public void gameNameDisplayed() {
        setupGames();
        onView(withId(R.id.game_name_textview)).check(matches(withText(xboxGame.getName())));
    }

    @Test
    public void gameAchievementsDisplayed() {
        setupGames();
        String expected = String.format("%d/%d", xboxGame.getEarnedAchievements(), xboxGame.getTotalAchivements());
        onView(withId(R.id.game_achievements_image_textview)).check(matches(withText(expected)));
    }

    @Test
    public void gameAchievementsMissingEarned() {
        xboxGame.setEarnedAchievements(-1);
        setupGames();
        onView(withId(R.id.game_achievements_image_textview)).check(matches(not(isDisplayed())));
    }

    @Test
    public void gameAchievementsMissingTotal() {
        xboxGame.setTotalAchivements(-1);
        setupGames();
        String text = "" + xboxGame.getEarnedAchievements();
        onView(withId(R.id.game_achievements_image_textview)).check(matches(withText(text)));
    }

    @Test
    public void gamerscoreDisplayed() {
        setupGames();
        String expected = String.format("%d/%d", xboxGame.getEarnedGamerscore(), xboxGame.getTotalGamerscore());
        onView(withId(R.id.game_score_image_textview)).check(matches(withText(expected)));
    }

    @Test
    public void gamerscoreMissingTotal() {
        xboxGame.setTotalGamerscore(-1);
        setupGames();
        String text = "" + xboxGame.getEarnedGamerscore();
        onView(withId(R.id.game_score_image_textview)).check(matches(withText(text)));
    }

    private void setupGames() {
        ArrayList<XboxGame> games = new ArrayList<>();
        games.add(xboxGame);
        eventBus.post(games);
    }

    private void mockWebRequests() {
        WebRequestQueue webRequestQueue = mock(WebRequestQueue.class);
        doNothing().when(webRequestQueue).addToQueue(Mockito.any(Request.class), anyString());
        WebRequestQueue.setInstance(webRequestQueue);
    }
}
