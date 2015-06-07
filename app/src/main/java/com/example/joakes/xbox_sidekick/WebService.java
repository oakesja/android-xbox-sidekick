package com.example.joakes.xbox_sidekick;

import android.content.Context;
import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.requests.AchievementRequest;
import com.example.joakes.xbox_sidekick.requests.ProfileRequester;
import com.example.joakes.xbox_sidekick.requests.WebRequestQueue;
import com.example.joakes.xbox_sidekick.requests.Xbox360GameListRequester;
import com.example.joakes.xbox_sidekick.requests.XboxOneGameListRequester;
import com.squareup.picasso.Picasso;

/**
 * Created by joakes on 4/28/15.
 */
public class WebService {
    private Context mContext;

    public WebService(Context context) {
        mContext = context;
    }

    public void getProfile(String requestTag) {
        new ProfileRequester(mContext, requestTag).makeRequest();
    }

    public void getGameList(String requestTag) {
        new XboxOneGameListRequester(mContext, requestTag).makeRequest();
        new Xbox360GameListRequester(mContext, requestTag).makeRequest();
    }

    public void stop(String tag) {
        WebRequestQueue.getInstance(mContext).cancelAll(tag);
    }

    public void loadImageFromUrl(ImageView imageView, String url) {
        Picasso.with(mContext)
                .load(url)
                .into(imageView);
    }

    public void getAchievementsFor(XboxGame game, String requestTag) {
        new AchievementRequest(mContext, requestTag, game).makeRequest();
    }
}
