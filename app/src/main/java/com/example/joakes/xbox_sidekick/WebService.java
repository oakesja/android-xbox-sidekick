package com.example.joakes.xbox_sidekick;

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
    public WebService(){}

    public void getProfile() {
        profileRequest.makeRequest();
    }
}
