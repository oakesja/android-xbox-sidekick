package com.example.joakes.xbox_sidekick.helpers;

import android.support.test.rule.ActivityTestRule;

import javax.inject.Inject;

import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/6/15.
 */
public class EventBusHelper {
    @Inject
    EventBus eventBus;

    public ActivityTestRule mRule;

    public EventBusHelper(ActivityTestRule rule) {
        mRule = rule;
    }

    public void post(final Object o) {
        try {
            mRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    eventBus.post(o);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
