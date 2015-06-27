package com.example.joakes.xbox_sidekick.activities;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.Request;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.dagger.IComponent;
import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.MockWebServiceModule;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.requests.WebService;
import com.example.joakes.xbox_sidekick.requests.utils.WebRequestQueue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Component;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.android.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class AchievementsActivityUnitTest {
    private AchievementsActivity activity;
    private Game xboxGame;
    private EventBusHelper eventBus;
    private Achievement lockedAchievement;
    private Achievement unlockedAchievement;

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
        lockedAchievement = TestSetup.createAchievement();
        lockedAchievement.setLocked(true);
        unlockedAchievement = TestSetup.createAchievement();
        unlockedAchievement.setLocked(false);
        eventBus = new EventBusHelper(activityRule);
        launchActivity();
        setupAchievements();
    }

    private void setupAchievements() {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.add(lockedAchievement);
        achievements.add(unlockedAchievement);
        eventBus.post(achievements);
    }

    private void launchActivity() {
        Intent intent = new Intent();
        intent.putExtra(AchievementsActivity.GAME, xboxGame);
        activityRule.launchActivity(intent);
        activity = activityRule.getActivity();
    }

    @Test
    public void gameName() {
        assertThat(activity.gameName).containsText(xboxGame.getName());
    }

    @Test
    public void gameAchievements() {
        String text = String.format("%d/%d", xboxGame.getEarnedAchievements(), xboxGame.getTotalAchivements());
//        assertThat(activity.gameAchievements).containsText(text);
    }

    @Test
    public void gameScore() {
        String text = String.format("%d/%d", xboxGame.getEarnedGamerscore(), xboxGame.getTotalGamerscore());
//        assertThat(activity.gameScore).containsText(text);
    }

    @Test
    public void achievementName() {
        onView(allOf(withId(R.id.achievement_name), isDisplayed()))
                .check(matches(withText(unlockedAchievement.getName())));
    }

    @Test
    public void achievementDescription() {
        onView(allOf(withId(R.id.achievement_description), isDisplayed()))
                .check(matches(withText(unlockedAchievement.getDescription())));
    }

    @Test
    public void achievementScore() {
        onView(allOf(withId(R.id.achievement_score), isDisplayed()))
                .check(matches(withText("" + unlockedAchievement.getValue())));
    }

    @Test
    public void achievementUnlockTime(){
        String text = "Unlocked on 10-16-2014";
        onView(allOf(withId(R.id.achievement_unlocked_time), isDisplayed()))
                .check(matches(withText(text)));
    }

    @Test
    public void swipeRightGoesToLockedAchievements(){
//        onView(withId(R.id.viewPager)).perform(swipeLeft());
        assertOnLockedList();
    }

    @Test
    public void touchingLockedTabGoesThere(){
        String text = "Locked";
        onView(withText(text)).perform(click());
        assertOnLockedList();
    }

    private void assertOnLockedList() {
        onView(allOf(withId(R.id.achievement_name), isDisplayed()))
                .check(matches(withText(lockedAchievement.getName())));
    }
}