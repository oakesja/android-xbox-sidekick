package com.example.joakes.xbox_sidekick.modules;

import com.example.joakes.xbox_sidekick.WebService;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joakes on 5/5/15.
 */
@Module
public class MockWebserviceModule {
    @Provides
    @Singleton
    WebService provideRequestQueue() {
        return Mockito.mock(WebService.class);
    }
}
