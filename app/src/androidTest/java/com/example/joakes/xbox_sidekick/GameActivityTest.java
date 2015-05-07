package com.example.joakes.xbox_sidekick;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.models.XboxProfile;
import com.example.joakes.xbox_sidekick.modules.BusModule;
import com.example.joakes.xbox_sidekick.modules.IComponent;
import com.example.joakes.xbox_sidekick.modules.MockWebserviceModule;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import de.greenrobot.event.EventBus;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.assertj.android.api.Assertions.assertThat;


@RunWith(AndroidJUnit4.class)
public class GameActivityTest {
    @Inject
    EventBus eventBus;
    @Inject
    WebService webService;

    private XboxProfile profile;
    private EventBusHelper eventBusHelper;
    private GameActivity gameActivity;

    @Singleton
    @Component(modules = {BusModule.class, MockWebserviceModule.class})
    public interface TestComponent extends IComponent {
        void inject(GameActivityTest gameActivityTest);
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
                .build();
        app.setComponent(component);
        component.inject(this);

        profile = new XboxProfile(
                "PoiZonOakes",
                36243,
                "http:\\/\\/images-eds.xboxlive.com\\/image?url=z951ykn43p4FqWbbFvR2Ec.8vbDhj8G2Xe7JngaTToBrrCmIEEXHC9UNrdJ6P7KIAbCDABRYREOfuoy2FOUr6jBmIGqp2iomsTK.Cz7APn6dX_VO8g7EjO9bVtm1wsWd&format=png"
        );

        eventBusHelper = new EventBusHelper(activityRule, eventBus);
        Mockito.doNothing().when(webService).getProfile();
        activityRule.launchActivity(new Intent());
        gameActivity = activityRule.getActivity();
    }

    @Test
    public void gamerTagDisplayed() {
        eventBusHelper.post(profile);
        assertThat(gameActivity.profileNameTextView).containsText(profile.getGamertag());
    }

    @Test
    public void gamerscoreDisplayed() {
        eventBusHelper.post(profile);
        assertThat(gameActivity.gamerscoreTextView).containsText("" + profile.getGamerscore());
    }

    @Test
    public void gamerPictureDisplayed() {
        eventBusHelper.post(profile);
        Mockito.verify(webService).loadImageFromUrl(gameActivity.gamerPicture, profile.getGamerPictureUrl());
    }

    @Test
    public void gamerscoreDrawableVisiblie(){
        assertThat(gameActivity.gamerscoreImageView).isInvisible();
        eventBusHelper.post(profile);
        assertThat(gameActivity.gamerscoreImageView).isVisible();
    }
}
