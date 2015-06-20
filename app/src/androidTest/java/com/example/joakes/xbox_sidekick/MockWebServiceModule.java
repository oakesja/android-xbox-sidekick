package com.example.joakes.xbox_sidekick;

import com.example.joakes.xbox_sidekick.requests.utils.WebService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joakes on 6/19/15.
 */
@Module
public class MockWebServiceModule {
    private WebService webService;

    public MockWebServiceModule(WebService webService){
        this.webService = webService;
    }

    @Provides
    WebService provideWebService(){
        return webService;
    }
}
