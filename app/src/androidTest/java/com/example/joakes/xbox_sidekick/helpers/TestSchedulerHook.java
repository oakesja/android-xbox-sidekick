package com.example.joakes.xbox_sidekick.helpers;

import rx.Scheduler;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

public class TestSchedulerHook extends RxJavaSchedulersHook {
    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler getNewThreadScheduler() {
        return Schedulers.immediate();
    }
}
