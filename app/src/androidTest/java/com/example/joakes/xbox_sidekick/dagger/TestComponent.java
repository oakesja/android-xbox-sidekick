package com.example.joakes.xbox_sidekick.dagger;

import com.example.joakes.xbox_sidekick.helpers.MockWebServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockWebServiceModule.class})
public interface TestComponent extends DaggerComponent {
}
