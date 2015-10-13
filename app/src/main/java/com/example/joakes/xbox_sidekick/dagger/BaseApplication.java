package com.example.joakes.xbox_sidekick.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

public class BaseApplication extends Application {
    @Singleton
    @Component(modules = {WebServiceModule.class, AndroidModule.class})
    public interface ApplicationComponent extends DaggerComponent {
    }

    private DaggerComponent component = null;

    @Override public void onCreate() {
        super.onCreate();
        if (component == null) {
            component = DaggerBaseApplication_ApplicationComponent.builder()
                    .androidModule(new AndroidModule(this))
                    .webServiceModule(new WebServiceModule())
                    .build();
        }
    }

    public void setComponent(DaggerComponent component) {
        this.component = component;
    }

    public DaggerComponent component() {
        return component;
    }
}
