package com.example.joakes.xbox_sidekick;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Scanner;

import retrofit.Converter;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;

public interface XboxApiService {

    @GET("/v2/2533274912330216/profile")
    Observable<String> getProfile();

    class Factory {
        public static XboxApiService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://xboxapi.com")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(new Converter.Factory() {
                        @Override
                        public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
                            return value -> {
                                Scanner s = new Scanner(value.byteStream()).useDelimiter("\\A");
                                return s.hasNext() ? s.next() : "";
                            };
                        }
                    })
                    .client(getOkHttpClient())
                    .build();
            return retrofit.create(XboxApiService.class);
        }

        private static OkHttpClient getOkHttpClient() {
            OkHttpClient client = new OkHttpClient();
            client.interceptors().add(chain -> {
                Request requestWithHeaders = chain.request().newBuilder()
                        .header("X-AUTH", "59e87e6243cbe26381ddd07d4b51025be66265b9")
                        .build();
                return chain.proceed(requestWithHeaders);
            });
            return client;
        }
    }
}
