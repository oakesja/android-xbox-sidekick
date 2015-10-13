package com.example.joakes.xbox_sidekick.dagger;

import android.content.Context;

import com.example.joakes.xbox_sidekick.XboxApiService;
import com.example.joakes.xbox_sidekick.requests.WebService;

import dagger.Module;
import dagger.Provides;

@Module
public class WebServiceModule {
    @Provides
    WebService provideWebService(@ForApplication Context context){
        return new WebService(context);
    }

    @Provides
    XboxApiService provideXboxApiService() {
        return XboxApiService.Factory.create();
    }
}
