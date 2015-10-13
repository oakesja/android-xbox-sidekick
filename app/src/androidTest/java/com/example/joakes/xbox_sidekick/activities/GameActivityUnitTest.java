package com.example.joakes.xbox_sidekick.activities;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.dagger.DaggerComponent;
import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.MockWebServiceModule;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.models.Profile;
import com.example.joakes.xbox_sidekick.requests.WebService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Component;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class GameActivityUnitTest {
    private Profile profile;
    private GameActivity activity;
    private Game xboxOneGame;
    private Game xbox360Game;
    private EventBusHelper eventBus;
    private WebService webService;

    @Singleton
    @Component(modules = {MockWebServiceModule.class})
    public interface TestComponent extends DaggerComponent {
    }

    @Rule
    public ActivityTestRule<GameActivity> activityRule = new ActivityTestRule<>(GameActivity.class, false, false);

    @Before
    public void setUp() {
        mockWebService();
        setupDagger();
        setupActivity();
    }

    private void mockWebService() {
        webService = mock(WebService.class);
        doNothing().when(webService).getProfile(anyString());
        doNothing().when(webService).getXbox360List(anyString());
        doNothing().when(webService).getXboxOneList(anyString());
        doNothing().when(webService).loadImageFromUrl(any(ImageView.class), anyString());
    }

    private void setupDagger() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BaseApplication app = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerGameActivityUnitTest_TestComponent.builder()
                .mockWebServiceModule(new MockWebServiceModule(webService))
                .build();
        app.setComponent(component);
    }

    private void setupActivity() {
        profile = TestSetup.createProfile();
        xboxOneGame = TestSetup.createXboxOneGame();
        xbox360Game = TestSetup.createXbox360Game();
        eventBus = new EventBusHelper(activityRule);
        activityRule.launchActivity(new Intent());
        activity = activityRule.getActivity();
    }

//    @Test
//    public void profileName() {
//        setupProfile();
//        String actual = ((CollapsingToolbarLayout)activity.findViewById(R.id.collapsing_tool_bar)).title
//        onView(withId(R.id.collapsing_tool_bar)).check(matches(withText(profile.getGamertag())));
//    }

//    @Test
//    public void profileGamerscore() {
//        setupProfile();
//        String text = "" + profile.getGamerscore();
//        onView(withId(R.id.profile_gamerscore)).check(matches(withText(text)));
//    }

    @Test
    public void profileGamerPicture() {
        setupProfile();
//        Mockito.verify(webService).loadImageFromUrl(any(ImageView.class), eq(profile.getGamerPictureUrl()));
//        onView(ViewMatchers.withId(R.id.header_image)).check(matches(isDisplayed()));
    }

    @Test
    public void headerTabs(){
        String[] tabNames = activity.getResources().getStringArray(R.array.game_tabs);
        for(String tabName: tabNames){
            onView(withText(tabName)).check(matches(isDisplayed()));
        }
    }

    private void setupProfile() {
        eventBus.post(profile);
    }

    @Test
    public void gameName() {
        setupGames();
        onView(allOf(withId(R.id.game_name), isDisplayed()))
                .check(matches(withText(xboxOneGame.getName())));
    }

    @Test
    public void gameAchievements() {
        setupGames();
        String expected = String.format("%d/%d", xboxOneGame.getEarnedAchievements(), xboxOneGame.getTotalAchivements());
        onView(allOf(withId(R.id.game_achievements), isDisplayed()))
                .check(matches(withText(expected)));
    }

    @Test
    public void gameScore() {
        setupGames();
        String expected = String.format("%d/%d", xboxOneGame.getEarnedGamerscore(), xboxOneGame.getTotalGamerscore());
        onView(allOf(withId(R.id.game_score), isDisplayed()))
                .check(matches(withText(expected)));
    }

    @Test
    public void touchingXbox360TabGoesThere(){
        setupGames();
        String text = "Xbox 360";
        onView(withText(text)).perform(click());
        assertOn360GameList();
    }

    private void setupGames() {
        sendGameListWith(xbox360Game);
        sendGameListWith(xboxOneGame);
    }

    private void assertOn360GameList() {
        onView(allOf(withId(R.id.game_name), isDisplayed()))
                .check(matches(withText(xbox360Game.getName())));
    }

    private void sendGameListWith(Game game) {
        ArrayList<Game> games = new ArrayList<>();
        games.add(game);
        eventBus.post(games);
    }
}
