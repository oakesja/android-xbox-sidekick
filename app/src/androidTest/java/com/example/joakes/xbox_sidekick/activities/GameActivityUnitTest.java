package com.example.joakes.xbox_sidekick.activities;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.Request;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.models.Profile;
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
    private Profile profile;
    private GameActivity gameActivity;
    private Game xboxGame;
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
        assertThat(gameActivity.profileName).containsText(profile.getGamertag());
    }

    @Test
    public void nullProfileName() {
        profile.setGamertag(null);
        eventBus.post(profile);
        assertThat(gameActivity.profileName).isInvisible();
    }

    @Test
    public void emptyProfileName() {
        profile.setGamertag(null);
        eventBus.post(profile);
        assertThat(gameActivity.profileName).isInvisible();
    }

    @Test
    public void profileGamerscoreDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.profileGamerscore).containsText("" + profile.getGamerscore());
    }

    @Test
    public void invalidGamerscoreDisplayed() {
        profile.setGamerscore(-1);
        eventBus.post(profile);
        assertThat(gameActivity.profileGamerscore).isGone();
    }

    @Test
    public void profileGamerPictureDisplayed() {
        eventBus.post(profile);
//        Mockito.verify(webService).loadImageFromUrl(gameActivity.profilePicture, profile.getGamerPictureUrl());
        onView(ViewMatchers.withId(R.id.gamer_picture)).check(matches(isDisplayed()));
    }

    @Test
    public void gameNameDisplayed() {
        setupGames();
        onView(withId(R.id.game_name)).check(matches(withText(xboxGame.getName())));
    }

    @Test
    public void gameAchievementsDisplayed() {
        setupGames();
        String expected = String.format("%d/%d", xboxGame.getEarnedAchievements(), xboxGame.getTotalAchivements());
        onView(withId(R.id.game_achievements)).check(matches(withText(expected)));
    }

    @Test
    public void gameAchievementsMissingEarned() {
        xboxGame.setEarnedAchievements(-1);
        setupGames();
        onView(withId(R.id.game_achievements)).check(matches(not(isDisplayed())));
    }

    @Test
    public void gameAchievementsMissingTotal() {
        xboxGame.setTotalAchivements(-1);
        setupGames();
        String text = "" + xboxGame.getEarnedAchievements();
        onView(withId(R.id.game_achievements)).check(matches(withText(text)));
    }

    @Test
    public void gamerscoreDisplayed() {
        setupGames();
        String expected = String.format("%d/%d", xboxGame.getEarnedGamerscore(), xboxGame.getTotalGamerscore());
        onView(withId(R.id.game_score)).check(matches(withText(expected)));
    }

    @Test
    public void gamerscoreMissingTotal() {
        xboxGame.setTotalGamerscore(-1);
        setupGames();
        String text = "" + xboxGame.getEarnedGamerscore();
        onView(withId(R.id.game_score)).check(matches(withText(text)));
    }

    private void setupGames() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(xboxGame);
        eventBus.post(games);
    }

    private void mockWebRequests() {
        WebRequestQueue webRequestQueue = mock(WebRequestQueue.class);
        doNothing().when(webRequestQueue).addToQueue(Mockito.any(Request.class), anyString());
        WebRequestQueue.setInstance(webRequestQueue);
    }
}
