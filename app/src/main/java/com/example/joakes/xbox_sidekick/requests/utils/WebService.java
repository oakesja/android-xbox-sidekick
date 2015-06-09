package com.example.joakes.xbox_sidekick.requests.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.requests.AchievementRequester;
import com.example.joakes.xbox_sidekick.requests.ProfileRequester;
import com.example.joakes.xbox_sidekick.requests.utils.WebRequestQueue;
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

    public void loadImageFromUrl(final ImageView imageView, String url) {
        Log.i(getClass().getName(), "loadImageFromUrl: " + url);
        url += "&h=200&w=200";
//        Picasso p = Picasso.with(mContext);
//        p.setIndicatorsEnabled(true);
//        p.load(url).into(imageView);
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.i("volley image request", "recieved response " + response.toString());
                        imageView.setImageBitmap(response);
                    }
                }, imageView.getWidth(), imageView.getHeight(), imageView.getScaleType(), null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley image request", error.toString());
                    }
                });
        WebRequestQueue.getInstance(mContext).addToQueue(imageRequest, "tag");
    }

    public void getAchievementsFor(XboxGame game, String requestTag) {
        new AchievementRequester(mContext, requestTag, game).makeRequest();
    }
}
