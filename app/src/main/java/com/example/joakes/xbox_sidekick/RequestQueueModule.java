package com.example.joakes.xbox_sidekick;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joakes on 5/3/15.
 */
@Module
public class RequestQueueModule {
    @Provides
    @Singleton
    RequestQueue provideRequestQueue(@ForApplication Context context) {
        return Volley.newRequestQueue(context);
    }
}
