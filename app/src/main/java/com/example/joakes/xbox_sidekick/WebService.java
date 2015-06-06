package com.example.joakes.xbox_sidekick;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.modules.ForApplication;
import com.example.joakes.xbox_sidekick.requests.AchievementRequest;
import com.example.joakes.xbox_sidekick.requests.ProfileRequest;
import com.example.joakes.xbox_sidekick.requests.Xbox360GameListRequest;
import com.example.joakes.xbox_sidekick.requests.XboxOneGameListRequest;
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
    XboxOneGameListRequest xboxOneGameListRequest;
    @Inject
    Xbox360GameListRequest xbox360GameListRequest;
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
        xboxOneGameListRequest.makeRequest();
        xbox360GameListRequest.makeRequest();
    }

    public void stop(String tag) {
        requestQueue.cancelAll(tag);
    }

    public void loadImageFromUrl(ImageView imageView, String url) {
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }

    public void getAchievementsFor(XboxGame game) {
        new AchievementRequest(game).makeRequest();
    }
}
