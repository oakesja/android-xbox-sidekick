package com.example.joakes.xbox_sidekick.helpers;

import com.example.joakes.xbox_sidekick.XboxApiService;
import com.example.joakes.xbox_sidekick.requests.WebService;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class MockWebServiceModule {
    private WebService webService;

    public MockWebServiceModule(WebService webService){
        this.webService = webService;
    }

    public MockWebServiceModule(){
        this.webService = mock(WebService.class);
    }

    @Provides
    WebService provideWebService(){
        return webService;
    }

    @Provides
    XboxApiService xboxApiService(){
        return XboxApiService.Factory.createForTest();
    }
}
