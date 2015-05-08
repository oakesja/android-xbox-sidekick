package com.example.joakes.xbox_sidekick;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
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

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static org.assertj.android.api.Assertions.assertThat;


@RunWith(AndroidJUnit4.class)
public class GameActivityTest {
    @Inject
    EventBusHelper eventBus;
    @Inject
    WebService webService;

    private XboxProfile profile;
    private GameActivity gameActivity;

    @Singleton
    @Component(modules = {BusModule.class, MockWebserviceModule.class, EventBusHelperModule.class})
    public interface TestComponent extends IComponent {
        void inject(GameActivityTest gameActivityTest);

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
        TestComponent component = DaggerGameActivityTest_TestComponent.builder()
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

        Mockito.doNothing().when(webService).getProfile();
        activityRule.launchActivity(new Intent());
        gameActivity = activityRule.getActivity();
    }

    @Test
    public void gamerTagDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.profileNameTextView).containsText(profile.getGamertag());
    }

    @Test
    public void gamerscoreDisplayed() {
        eventBus.post(profile);
        assertThat(gameActivity.gamerscoreTextView).containsText("" + profile.getGamerscore());
    }

    @Test
    public void gamerPictureDisplayed() {
        eventBus.post(profile);
        Mockito.verify(webService).loadImageFromUrl(gameActivity.gamerPicture, profile.getGamerPictureUrl());
    }

    @Test
    public void gamerscoreDrawableDisplayed() {
        assertThat(gameActivity.gamerscoreImageView).isInvisible();
        eventBus.post(profile);
        assertThat(gameActivity.gamerscoreImageView).isInvisible();
    }
}
