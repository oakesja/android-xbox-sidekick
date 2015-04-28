package com.example.joakes.xbox_sidekick;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;



/**
 * Created by joakes on 4/29/15.
 */
public class BaseApplication extends Application {
    @Singleton
    @Component(modules = {AndroidModule.class, BusModule.class, RequestQueueModule.class})
    public interface ApplicationComponent extends IComponent {
    }

    private ApplicationComponent component = null;

    @Override public void onCreate() {
        super.onCreate();
        if (component == null) {
            component = DaggerBaseApplication_ApplicationComponent.builder()
                    .androidModule(new AndroidModule(this))
                    .busModule(new BusModule())
                    .requestQueueModule(new RequestQueueModule())
                    .build();
        }
    }

    public void setComponent(ApplicationComponent component) {
        this.component = component;
    }

    public ApplicationComponent component() {
        return component;
    }
}
