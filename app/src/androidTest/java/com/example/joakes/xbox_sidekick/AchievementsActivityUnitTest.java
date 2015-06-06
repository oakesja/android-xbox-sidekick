package com.example.joakes.xbox_sidekick;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.modules.BusModule;
import com.example.joakes.xbox_sidekick.modules.EventBusHelperModule;
import com.example.joakes.xbox_sidekick.modules.IComponent;
import com.example.joakes.xbox_sidekick.modules.MockWebserviceModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static org.assertj.android.api.Assertions.assertThat;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class AchievementsActivityUnitTest {
    @Inject
    EventBusHelper eventBus;
    @Inject
    WebService webService;

    private AchievementsActivity activity;
    private XboxGame xboxGame;
    private Achievement achievement;

    @Rule
    public ActivityTestRule<AchievementsActivity> activityRule = new ActivityTestRule<>(AchievementsActivity.class, false, false);

    @Before
    public void setUp() {
        setupDagger();
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
    public void achievementNameDisplayed(){
        setupAchievements();
        onView(withId(R.id.achievement_name_textview)).check(matches(withText(achievement.getName())));
    }

    private void setupActivity() {
        xboxGame = TestSetup.createGame();
        achievement = TestSetup.createAchievement();
        Intent intent = new Intent();
        intent.putExtra(AchievementsActivity.GAME, xboxGame);
        activityRule.launchActivity(intent);
        activity = activityRule.getActivity();
    }

    private void setupDagger() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BaseApplication app = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerAchievementsActivityUnitTest_TestComponent.builder()
                .busModule(new BusModule())
                .mockWebserviceModule(new MockWebserviceModule())
                .eventBusHelperModule(new EventBusHelperModule(activityRule))
                .build();
        app.setComponent(component);
        component.inject(this);
        component.inject(eventBus);
    }

    private void setupAchievements() {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.add(achievement);
        eventBus.post(achievements);
    }

    @Singleton
    @Component(modules = {BusModule.class, MockWebserviceModule.class, EventBusHelperModule.class})
    public interface TestComponent extends IComponent {
        void inject(EventBusHelper eventBusHelper);

        void inject(AchievementsActivityUnitTest activityUnitTest);
    }
}
