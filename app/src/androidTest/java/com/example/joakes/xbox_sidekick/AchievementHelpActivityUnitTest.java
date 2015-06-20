package com.example.joakes.xbox_sidekick;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

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

@RunWith(AndroidJUnit4.class)
public class AchievementHelpActivityUnitTest {
    private AchievementHelpActivity activity;
    private Achievement achievement;
    private EventBusHelper eventBus;
    private SearchResult searchResult;

    @Singleton
    @Component(modules = {MockWebServiceModule.class})
    public interface TestComponent extends IComponent {
    }

    @Rule
    public ActivityTestRule<AchievementHelpActivity> activityRule = new ActivityTestRule<>(AchievementHelpActivity.class, false, false);

    @Before
    public void setUp() {
        WebService webService = Mockito.mock(WebService.class);
        Mockito.doNothing().when(webService).searchForYoutubeVideos(Mockito.anyString());
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BaseApplication app = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerAchievementHelpActivityUnitTest_TestComponent.builder()
                .mockWebServiceModule(new MockWebServiceModule(webService))
                .build();
        app.setComponent(component);
        achievement = TestSetup.createAchievement();
        Intent intent = new Intent();
        intent.putExtra(AchievementHelpActivity.ACHIEVEMENT, achievement);
        activityRule.launchActivity(intent);
        activity = activityRule.getActivity();
        eventBus = new EventBusHelper(activityRule);
    }

    @Test
    public void youtubeTitleDisplayed() {
        setupYoutubeResults();
        onView(withId(R.id.youtube_title)).check(matches(withText(searchResult.getSnippet().getTitle())));
    }

    @Test
    public void youtubeAuthorDisplayed() {
        setupYoutubeResults();
        String expected = activity.getString(R.string.by_author_formatted, searchResult.getSnippet().getChannelTitle());
        onView(withId(R.id.youtube_author)).check(matches(withText(expected)));
    }

    private void setupYoutubeResults() {
        setupSearchResults();
        ArrayList<SearchResult> results = new ArrayList<>();
        results.add(searchResult);
        eventBus.post(results);
    }

    private void setupSearchResults() {
        searchResult = new SearchResult();
        SearchResultSnippet snippet = new SearchResultSnippet();
        snippet.setTitle("some title");
        snippet.setChannelTitle("some channel");
        searchResult.setSnippet(snippet);
    }
}
