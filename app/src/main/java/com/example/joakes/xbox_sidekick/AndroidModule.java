package com.example.joakes.xbox_sidekick;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joakes on 4/29/15.
 */
@Module
public class AndroidModule {
    private final BaseApplication application;

    public AndroidModule(BaseApplication application) {
        this.application = application;
    }

    @Provides @Singleton @ForApplication
    Context provideApplicationContext() {
        return application;
    }
}
