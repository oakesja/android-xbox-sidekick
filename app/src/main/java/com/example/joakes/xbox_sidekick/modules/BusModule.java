package com.example.joakes.xbox_sidekick.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/3/15.
 */
@Module
public class BusModule {
    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
