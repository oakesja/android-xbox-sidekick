package com.example.joakes.xbox_sidekick.helpers;

import android.support.test.rule.ActivityTestRule;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 5/6/15.
 */
public class EventBusHelper {
    public ActivityTestRule mRule;

    public EventBusHelper(ActivityTestRule rule) {
        mRule = rule;
    }

    public void post(final Object o) {
        try {
            mRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(o);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}