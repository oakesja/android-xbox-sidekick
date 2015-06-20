package com.example.joakes.xbox_sidekick;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joakes on 6/19/15.
 */
public class BaseApplication extends Application {
    @Singleton
    @Component(modules = {WebServiceModule.class, AndroidModule.class})
    public interface ApplicationComponent extends IComponent {
    }

    private IComponent component = null;

    @Override public void onCreate() {
        super.onCreate();
        if (component == null) {
            component = DaggerBaseApplication_ApplicationComponent.builder()
                    .androidModule(new AndroidModule(this))
                    .webServiceModule(new WebServiceModule())
                    .build();
        }
    }

    public void setComponent(IComponent component) {
        this.component = component;
    }

    public IComponent component() {
        return component;
    }
}
