package com.example.joakes.xbox_sidekick;

import com.android.volley.RequestQueue;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by joakes on 4/28/15.
 */
@Singleton
public class WebService {
    @Inject
    ProfileRequest profileRequest;
    @Inject
    RequestQueue requestQueue;

    @Inject
    public WebService(){}

    public void getProfile() {
        profileRequest.makeRequest();
    }

    public void stop(String tag){
        requestQueue.cancelAll(tag);
    }
}
