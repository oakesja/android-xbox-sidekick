package com.example.joakes.xbox_sidekick.integrations;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.activities.GameActivity;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.dagger.TestComponent;
import com.example.joakes.xbox_sidekick.helpers.MockWebServiceModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ProfileViewTest {

    private final String expectedGamertag = "AssuredSteam271";

    @Rule
    public ActivityTestRule<GameActivity> activityRule =
            new ActivityTestRule<>(GameActivity.class, false, false);

    @Before
    public void setup() {
        setupDagger();
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void gamertagDisplayed() {
        // TODO hack for https://code.google.com/p/android/issues/detail?id=187731
        onView(withId(R.id.collapsing_tool_bar)).check(matches(isDisplayed()));
        assertEquals(expectedGamertag, activityRule.getActivity().cToolbar.getTitle());

    }

    private void setupDagger() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BaseApplication app = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerProfileViewTest_TestComponent.builder()
                .mockWebServiceModule(new MockWebServiceModule())
                .build();
        app.setComponent(component);
    }
}
