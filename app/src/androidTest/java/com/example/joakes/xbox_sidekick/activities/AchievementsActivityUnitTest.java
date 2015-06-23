package com.example.joakes.xbox_sidekick.activities;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.Request;
import com.example.joakes.xbox_sidekick.helpers.MockWebServiceModule;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.dagger.IComponent;
import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.Game;
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
    private Game xboxGame;
    private Achievement achievement;
    private EventBusHelper eventBus;

    @Singleton
    @Component(modules = {MockWebServiceModule.class})
    public interface TestComponent extends IComponent {
    }

    @Rule
    public ActivityTestRule<AchievementsActivity> activityRule = 
            new ActivityTestRule<>(AchievementsActivity.class, false, false);

    @Before
    public void setUp() {
        setupDagger();
        stubWebRequests();
        setupActivity();
    }

    @Test
    public void gameName() {
        assertThat(activity.gameName).containsText(xboxGame.getName());
    }

    @Test
    public void gameAchievements() {
        String text = String.format("%d/%d", xboxGame.getEarnedAchievements(), xboxGame.getTotalAchivements());
        assertThat(activity.gameAchievements).containsText(text);
    }

    @Test
    public void gameScore() {
        String text = String.format("%d/%d", xboxGame.getEarnedGamerscore(), xboxGame.getTotalGamerscore());
        assertThat(activity.gameScore).containsText(text);
    }

    @Test
    public void achievementName() {
        onView(ViewMatchers.withId(R.id.achievement_name)).check(matches(withText(achievement.getName())));
    }

    @Test
    public void achievementDescription() {
        onView(withId(R.id.achievement_description)).check(matches(withText(achievement.getDescription())));
    }

    @Test
    public void achievementScore() {
        onView(withId(R.id.achievement_score)).check(matches(withText("" + achievement.getValue())));
    }
    
    @Test
    public void achievementUnlockTime(){
        String text = "Unlocked on 10-16-2014";
        onView(withId(R.id.achievement_unlocked_time)).check(matches(withText(text)));
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
        xboxGame = TestSetup.createXboxOneGame();
        achievement = TestSetup.createAchievement();
        eventBus = new EventBusHelper(activityRule);
        launchActivity();
        setupAchievements();
    }

    private void launchActivity() {
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