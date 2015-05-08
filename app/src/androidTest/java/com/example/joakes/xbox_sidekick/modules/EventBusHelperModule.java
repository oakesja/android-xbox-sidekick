package com.example.joakes.xbox_sidekick.modules;

import android.support.test.rule.ActivityTestRule;

import com.example.joakes.xbox_sidekick.helpers.EventBusHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joakes on 5/7/15.
 */
@Module
public class EventBusHelperModule {
    private ActivityTestRule mRule;

    public EventBusHelperModule(ActivityTestRule rule){
        mRule = rule;
    }

    @Provides
    @Singleton
    public EventBusHelper provideEventBusHelper(){
        return new EventBusHelper(mRule);
    }
}
