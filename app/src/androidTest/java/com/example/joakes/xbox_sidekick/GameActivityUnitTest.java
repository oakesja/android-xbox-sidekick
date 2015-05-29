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
import static android.support.test.espresso.matcher.ViewMatchers.withParent;

import static org.hamcrest.Matchers.allOf;


import static org.assertj.android.api.Assertions.assertThat;


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

        xboxGame = new XboxGame(1, "game", 10, 100, 100, 1000, XboxGame.XBOX_360);

        Mockito.doNothing().when(webService).getProfile();
        activityRule.launchActivity(new Intent());
        gameActivity = activityRule.getActivity();
    }

    @Test
    public void profileGamerTagDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.profileNameTextView).containsText(profile.getGamertag());
    }

    @Test
    public void profielGamerscoreDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.gamerscoreTextView).containsText("" + profile.getGamerscore());
    }

    @Test
    public void profileGamerPictureDisplayed() {
        eventBus.post(profile);
        Mockito.verify(webService).loadImageFromUrl(gameActivity.gamerPicture, profile.getGamerPictureUrl());
        onView(withId(R.id.gamer_picture)).check(matches(isDisplayed()));
    }

    @Test
    public void profileGamerscoreDrawableDisplayed() {
        assertThat(gameActivity.gamerscoreImageView).isInvisible();
        eventBus.post(profile);
        assertThat(gameActivity.gamerscoreImageView).isVisible();
    }

    @Test
    public void profileNoGamerScore(){
        profile.setGamerscore(-1);
        eventBus.post(profile);
        assertThat(gameActivity.gamerscoreImageView).isInvisible();
        assertThat(gameActivity.gamerscoreTextView).isInvisible();
    }

    @Test
    public void gamesListDisplayed() {
        ArrayList<XboxGame> games = new ArrayList<>();
        games.add(xboxGame);
        eventBus.post(games);
//        onView(withId(R.id.game_imageview)).check(matches(isDisplayed()));
//        Mockito.verify(webService).loadImageFromUrl(Mockito.any(ImageView.class), ...);
        onView(withId(R.id.game_name_textview)).check(matches(withText(xboxGame.getName())));
        assertGameAchievementsDisplayed();
        assertGameScoreDisplayed();
//        onView(allOf(withParent(withId((R.id.games_list))), withId(R.id.gamerscore_imageview)))
//                .check(matches(isDisplayed()));
    }

    public void assertGameAchievementsDisplayed() {
        String expected = "" + xboxGame.getEarnedAchievements() + '/' + xboxGame.getTotalAchivements();
        onView(withId(R.id.game_achievements_textview)).check(matches(withText(expected)));
    }

    public void assertGameScoreDisplayed() {
        String expected = "" + xboxGame.getEarnedGamerscore() + '/' + xboxGame.getTotalGamerscore();
        onView(withId(R.id.game_score_textview)).check(matches(withText(expected)));
    }
}
