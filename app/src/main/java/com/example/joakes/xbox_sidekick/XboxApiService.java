package com.example.joakes.xbox_sidekick;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Scanner;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.http.GET;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import rx.Observable;

public interface XboxApiService {

    @GET("/v2/2535458020453936/profile")
    Observable<String> getProfile();

    class Factory {
        private static final String endpoint = "https://xboxapi.com";

        @NonNull
        public static XboxApiService create() {
            RestAdapter retrofit = baseBuilder()
                    .build();
            return retrofit.create(XboxApiService.class);
        }

        public static XboxApiService createForTest(){
            RestAdapter retrofit = baseBuilder()
                    .setExecutors(AsyncTask.THREAD_POOL_EXECUTOR,
                            new MainThreadExecutor())
                    .build();
            return retrofit.create(XboxApiService.class);
        }

        @NonNull
        private static RestAdapter.Builder baseBuilder() {
            return new RestAdapter.Builder()
                    .setEndpoint(endpoint)
                    .setRequestInterceptor(new XboxApiKeyInterceptor())
                    .setConverter(new StringConverterFactory());
        }

        private static class XboxApiKeyInterceptor implements RequestInterceptor {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("X-AUTH", "59e87e6243cbe26381ddd07d4b51025be66265b9");
            }
        }

        private static class StringConverterFactory implements Converter {
            @Override
            public Object fromBody(TypedInput body, Type type) throws ConversionException {
                try {
                    Scanner s = new Scanner(body.in()).useDelimiter("\\A");
                    return s.hasNext() ? s.next() : "";
                } catch (IOException e) {
                    return "Error";
                }
            }

            @Override
            public TypedOutput toBody(Object object) {
                return null;
            }
        }
    }
}
