package com.example.joakes.xbox_sidekick;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.models.XboxProfile;
import com.example.joakes.xbox_sidekick.modules.BusModule;
import com.example.joakes.xbox_sidekick.modules.EventBusHelperModule;
import com.example.joakes.xbox_sidekick.modules.IComponent;
import com.example.joakes.xbox_sidekick.modules.MockWebserviceModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.android.api.Assertions.assertThat;

import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class GameActivityUnitTest {
    @Inject
    EventBusHelper eventBus;
    @Inject
    WebService webService;

    private XboxProfile profile;
    private GameActivity gameActivity;
    private XboxGame xboxGame;

    @Singleton
    @Component(modules = {BusModule.class, MockWebserviceModule.class, EventBusHelperModule.class})
    public interface TestComponent extends IComponent {
        void inject(GameActivityUnitTest gameActivityTest);

        void inject(EventBusHelper eventBusHelper);
    }

    @Rule
    public ActivityTestRule<GameActivity> activityRule = new ActivityTestRule<>(
            GameActivity.class,
            false,
            false
    );

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BaseApplication app = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerGameActivityUnitTest_TestComponent.builder()
                .busModule(new BusModule())
                .mockWebserviceModule(new MockWebserviceModule())
                .eventBusHelperModule(new EventBusHelperModule(activityRule))
                .build();
        app.setComponent(component);
        component.inject(this);
        component.inject(eventBus);

        profile = new XboxProfile(
                "PoiZonOakes",
                36243,
                "http:\\/\\/images-eds.xboxlive.com\\/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png"
        );
        xboxGame = GameSetup.createGame();

        Mockito.doNothing().when(webService).getProfile();
        Mockito.doNothing().when(webService).getGameList();
        activityRule.launchActivity(new Intent());
        gameActivity = activityRule.getActivity();
    }

    @Test
    public void profileNameDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.profileNameTextView).containsText(profile.getGamertag());
    }

    @Test
    public void nullProfileName() {
        profile.setGamertag(null);
        eventBus.post(profile);
        assertThat(gameActivity.profileNameTextView).isInvisible();
    }

    @Test
    public void emptyProfileName() {
        profile.setGamertag(null);
        eventBus.post(profile);
        assertThat(gameActivity.profileNameTextView).isInvisible();
    }

    @Test
    public void profileGamerscoreDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.gamerscoreImageTextView).containsText("" + profile.getGamerscore());
    }

    @Test
    public void invalidGamerscoreDisplayed() {
        profile.setGamerscore(-1);
        eventBus.post(profile);
        assertThat(gameActivity.gamerscoreImageTextView).isInvisible();
    }

    @Test
    public void profileGamerPictureDisplayed() {
        eventBus.post(profile);
        Mockito.verify(webService).loadImageFromUrl(gameActivity.gamerPicture, profile.getGamerPictureUrl());
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
}
