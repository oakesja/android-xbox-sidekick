package com.example.joakes.xbox_sidekick.helpers;

import rx.Scheduler;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

public class TestAndroidSchedulerHook extends RxAndroidSchedulersHook {
    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.immediate();
    }
}
