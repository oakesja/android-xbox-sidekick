package com.example.joakes.xbox_sidekick;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.example.joakes.xbox_sidekick.modules.ForApplication;
import com.squareup.picasso.Picasso;

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
    GameListRequest gameListRequest;
    @Inject
    RequestQueue requestQueue;
    @Inject
    @ForApplication
    Context context;

    @Inject
    public WebService() {
    }

    public void getProfile() {
        profileRequest.makeRequest();
    }

    public void getGameList() {
        gameListRequest.makeRequest();
    }

    public void stop(String tag) {
        requestQueue.cancelAll(tag);
    }

    public void loadImageFromUrl(ImageView imageView, String url) {
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }
}
