package com.example.joakes.xbox_sidekick;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.Request;
import com.example.joakes.xbox_sidekick.activities.AchievementsActivity;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.dagger.IComponent;
import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.requests.utils.WebRequestQueue;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Component;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class AchievementsActivityUnitTest {
    private AchievementsActivity activity;
    private XboxGame xboxGame;
    private Achievement achievement;
    private EventBusHelper eventBus;

    @Singleton
    @Component(modules = {MockWebServiceModule.class})
    public interface TestComponent extends IComponent {
    }

    @Rule
    public ActivityTestRule<AchievementsActivity> activityRule = new ActivityTestRule<>(AchievementsActivity.class, false, false);

    @Before
    public void setUp() {
        setupDagger();
        stubWebRequests();
        setupActivity();
    }

    @Test
    public void gameNameDisplayed() {
        assertThat(activity.gameNameTextView).containsText(xboxGame.getName());
    }

    @Test
    public void gameAchievementsDisplayed() {
        String text = String.format("%d/%d", xboxGame.getEarnedAchievements(), xboxGame.getTotalAchivements());
        assertThat(activity.gameAchievementsImageTextview).containsText(text);
    }

    @Test
    public void gameScoreDisplayed() {
        String text = String.format("%d/%d", xboxGame.getEarnedGamerscore(), xboxGame.getTotalGamerscore());
        assertThat(activity.gamerscoreImageTextview).containsText(text);
    }

    @Test
    public void achievementNameDisplayed() {
        setupAchievements();
        onView(withId(R.id.achievement_name_textview)).check(matches(withText(achievement.getName())));
    }

    @Test
    public void achievementDescriptionDisplayed() {
        setupAchievements();
        onView(withId(R.id.achievement_description_textview)).check(matches(withText(achievement.getDescription())));
    }

    @Test
    public void achievementScoreDisplayed() {
        setupAchievements();
        onView(withId(R.id.achievement_score_image_textview)).check(matches(withText("" + achievement.getValue())));
    }

    private void setupDagger() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BaseApplication app = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerAchievementsActivityUnitTest_TestComponent.builder()
                .mockWebServiceModule(new MockWebServiceModule(Mockito.mock(WebService.class)))
                .build();
        app.setComponent(component);
    }

    private void stubWebRequests() {
        WebRequestQueue webRequestQueue = mock(WebRequestQueue.class);
        doNothing().when(webRequestQueue).addToQueue(Mockito.any(Request.class), anyString());
        WebRequestQueue.setInstance(webRequestQueue);
    }

    private void setupActivity() {
        xboxGame = TestSetup.createGame();
        achievement = TestSetup.createAchievement();
        eventBus = new EventBusHelper(activityRule);
        Intent intent = new Intent();
        intent.putExtra(AchievementsActivity.GAME, xboxGame);
        activityRule.launchActivity(intent);
        activity = activityRule.getActivity();
    }

    private void setupAchievements() {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.add(achievement);
        eventBus.post(achievements);
    }
}