package com.example.joakes.xbox_sidekick;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.activities.AchievementHelpActivity;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.dagger.IComponent;
import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.ThumbnailDetails;

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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class AchievementHelpActivityUnitTest {
    private AchievementHelpActivity activity;
    private Achievement achievement;
    private EventBusHelper eventBus;
    private SearchResult searchResult;
    private WebService webService;

    @Singleton
    @Component(modules = {MockWebServiceModule.class})
    public interface TestComponent extends IComponent {
    }

    @Rule
    public ActivityTestRule<AchievementHelpActivity> activityRule = new ActivityTestRule<>(AchievementHelpActivity.class, false, false);

    @Before
    public void setUp() {
        setupDagger();
        setupActivity();
    }

    @Test
    public void searchesForYoutubeVideos(){
        String searchTerms = achievement.getName() + " achievement xbox";
        verify(webService).searchForYoutubeVideos(eq(searchTerms));
    }

    @Test
    public void youtubeTitleDisplayed() {
        onView(withId(R.id.youtube_title)).check(matches(withText(searchResult.getSnippet().getTitle())));
    }

    @Test
    public void youtubeAuthorDisplayed() {
        String expected = activity.getString(R.string.by_author_formatted, searchResult.getSnippet().getChannelTitle());
        onView(withId(R.id.youtube_author)).check(matches(withText(expected)));
    }

    @Test
    public void youtubeIconDisplayed(){
        onView(withId(R.id.youtube_icon)).check(matches(isDisplayed()));
        String url = searchResult.getSnippet().getThumbnails().getDefault().getUrl();
        verify(webService).loadImageFromUrl(any(ImageView.class), anyString());
    }

    private void setupDagger() {
        webService = mock(WebService.class);
        doNothing().when(webService).searchForYoutubeVideos(anyString());
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BaseApplication app = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerAchievementHelpActivityUnitTest_TestComponent.builder()
                .mockWebServiceModule(new MockWebServiceModule(webService))
                .build();
        app.setComponent(component);
    }

    private void setupActivity() {
        achievement = TestSetup.createAchievement();
        Intent intent = new Intent();
        intent.putExtra(AchievementHelpActivity.ACHIEVEMENT, achievement);
        activityRule.launchActivity(intent);
        activity = activityRule.getActivity();
        eventBus = new EventBusHelper(activityRule);
        setupYoutubeResults();
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
        snippet.setThumbnails(getThumbNails());
        searchResult.setSnippet(snippet);
    }

    private ThumbnailDetails getThumbNails(){
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setUrl("url");
        ThumbnailDetails thumbnails = new ThumbnailDetails();
        thumbnails.setDefault(thumbnail);
        return thumbnails;
    }


}
