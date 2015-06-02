package com.example.joakes.xbox_sidekick;

import android.app.Application;

import com.example.joakes.xbox_sidekick.modules.AndroidModule;
import com.example.joakes.xbox_sidekick.modules.BusModule;
import com.example.joakes.xbox_sidekick.modules.IComponent;
import com.example.joakes.xbox_sidekick.modules.RequestQueueModule;

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

    private IComponent component = null;

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

    public void setComponent(IComponent component) {
        this.component = component;
    }

    public IComponent component() {
        return component;
    }
}
