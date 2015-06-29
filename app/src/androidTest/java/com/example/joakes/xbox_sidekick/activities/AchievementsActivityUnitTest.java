package com.example.joakes.xbox_sidekick.activities;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

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

import org.hamcrest.Matcher;
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
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.android.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
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
    }

    private void launchActivity() {
        Intent intent = new Intent();
        intent.putExtra(AchievementsActivity.GAME, xboxGame);
        activityRule.launchActivity(intent);
        activity = activityRule.getActivity();
    }

    @Test
    public void gameName() {
        setupAchievements();
        assertThat(activity.gameName).containsText(xboxGame.getName());
    }

    @Test
    public void gameAchievements() {
        setupAchievements();
        String text = String.format("%d/%d", xboxGame.getEarnedAchievements(), xboxGame.getTotalAchivements());
        onView(withId(R.id.game_achievements)).check(matches(withText(text)));
    }

    @Test
    public void gameScore() {
        setupAchievements();
        String text = String.format("%d/%d", xboxGame.getEarnedGamerscore(), xboxGame.getTotalGamerscore());
        onView(withId(R.id.game_score)).check(matches(withText(text)));
    }

    @Test
    public void achievementName() {
        setupAchievements();
        onView(allOf(withId(R.id.achievement_name), isDisplayed()))
                .check(matches(withText(unlockedAchievement.getName())));
    }

    @Test
    public void achievementDescription() {
        setupAchievements();
        onView(allOf(withId(R.id.achievement_description), isDisplayed()))
                .check(matches(withText(unlockedAchievement.getDescription())));
    }

    @Test
    public void achievementScore() {
        setupAchievements();
        onView(allOf(withId(R.id.achievement_score), isDisplayed()))
                .check(matches(withText("" + unlockedAchievement.getValue())));
    }

    @Test
    public void achievementUnlockTime(){
        setupAchievements();
        String text = "Unlocked on 10-16-2014";
        onView(allOf(withId(R.id.achievement_unlocked_time), isDisplayed()))
                .check(matches(withText(text)));
    }

    @Test
    public void touchingLockedTabGoesThere(){
        setupAchievements();
        navigateToLockedList();
        assertOnLockedList();
    }

    @Test
    public void emptyMessageNotVisibleWhenAchievementsAreThere(){
        setupAchievements();
        onView(currentEmptyMessage())
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void noLockedAchievement(){
        setupUnlockedAchievement();
        navigateToLockedList();
        String text = activity.getString(R.string.no_locked_achievements_message);
        onView(currentEmptyMessage())
                .check(matches(isDisplayed()))
                .check(matches(withText(text)));

    }

    @Test
    public void noUnlockedAchievement(){
        setupLockedAchievement();
        navigateToUnlockedList();
        String text = activity.getString(R.string.no_unlocked_achievements_message);
        onView(currentEmptyMessage())
                .check(matches(isDisplayed()))
                .check(matches(withText(text)));

    }

    private void setupAchievements() {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.add(lockedAchievement);
        achievements.add(unlockedAchievement);
        eventBus.post(achievements);
    }

    private void setupUnlockedAchievement() {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.add(unlockedAchievement);
        eventBus.post(achievements);
    }

    private void setupLockedAchievement() {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.add(lockedAchievement);
        eventBus.post(achievements);
    }

    private void navigateToLockedList() {
        String text = "Locked";
        onView(withText(text)).perform(click());
    }

    private void navigateToUnlockedList() {
        String text = "Unlocked";
        onView(withText(text)).perform(click());
    }

    private Matcher<View> currentEmptyMessage() {
        return allOf(parentIsCurrentAchievementList(), withId(R.id.empty_message));
    }

    private Matcher<View> parentIsCurrentAchievementList() {
        return withParent(allOf(withId(R.id.list_or_empty_message), isDisplayed()));
    }

    private void assertOnLockedList() {
        onView(allOf(withId(R.id.achievement_name), isDisplayed()))
                .check(matches(withText(lockedAchievement.getName())));
    }
}