package com.example.joakes.xbox_sidekick.helpers;

import android.support.test.rule.ActivityTestRule;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/6/15.
 */
public class EventBusHelper {
    public ActivityTestRule mRule;
    public EventBus mBus;

    public EventBusHelper(ActivityTestRule rule, EventBus bus) {
        mRule = rule;
        mBus = bus;
    }

    public void post(final Object o) {
        try {
            mRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBus.post(o);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
