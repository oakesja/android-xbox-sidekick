package com.example.joakes.xbox_sidekick.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joakes on 6/19/15.
 */
@Module
public class AndroidModule {
    private final BaseApplication application;

    public AndroidModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }
}
