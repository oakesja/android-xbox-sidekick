package com.example.joakes.xbox_sidekick.presenters;

import android.test.AndroidTestCase;

import com.example.joakes.xbox_sidekick.XboxApiService;
import com.example.joakes.xbox_sidekick.helpers.JsonSetup;
import com.example.joakes.xbox_sidekick.helpers.TestAndroidSchedulerHook;
import com.example.joakes.xbox_sidekick.helpers.TestSchedulerHook;
import com.example.joakes.xbox_sidekick.views.ProfileView;

import rx.Observable;
import rx.android.plugins.RxAndroidPlugins;
import rx.plugins.RxJavaPlugins;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// TODO move test and make junit test when JSONObject dependency is removed
public class ProfilePresenterTest extends AndroidTestCase {

    private static boolean hasBeenSetup;
    private XboxApiService xboxApiService;
    private ProfileView profileView;
    private ProfilePresenter presenter;

    public void setUp() {
        if (!hasBeenSetup) {
            RxAndroidPlugins.getInstance().registerSchedulersHook(new TestAndroidSchedulerHook());
            RxJavaPlugins.getInstance().registerSchedulersHook(new TestSchedulerHook());
            hasBeenSetup = true;
        }
        xboxApiService = mock(XboxApiService.class);
        profileView = mock(ProfileView.class);
        presenter = new ProfilePresenter();
    }

    public void testAttachToView() throws Exception {
        when(xboxApiService.getProfile()).thenReturn(Observable.just(JsonSetup.profileJson()));
        when(profileView.getXboxApiService()).thenReturn(xboxApiService);
        presenter.attachToView(profileView);
        verify(profileView).showGamertag("PoizonOakes92");
        verify(profileView).showGamerscore("37963");
        verify(profileView).showGamerPicture(any());
    }

    public void testAttachToViewError() throws Exception {
        when(xboxApiService.getProfile()).thenReturn(Observable.error(new Throwable()));
        when(profileView.getXboxApiService()).thenReturn(xboxApiService);
        presenter.attachToView(profileView);
        verify(profileView).handleError();
    }
}