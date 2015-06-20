package com.example.joakes.xbox_sidekick;

import android.content.Context;

import com.example.joakes.xbox_sidekick.requests.utils.WebService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joakes on 6/19/15.
 */
@Module
public class WebServiceModule {
    @Provides
    WebService provideWebService(@ForApplication Context context){
        return new WebService(context);
    }
}
