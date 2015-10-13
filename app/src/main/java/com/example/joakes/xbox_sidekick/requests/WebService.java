package com.example.joakes.xbox_sidekick.requests;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.joakes.xbox_sidekick.YoutubeVideoTask;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.requests.utils.WebRequestQueue;
import com.squareup.picasso.Picasso;

public class WebService {
    private Context mContext;

    public WebService(Context context) {
        mContext = context;
    }

    public void getProfile(String requestTag) {
        new ProfileRequester(mContext, requestTag).makeRequest();
    }

    public void getXbox360List(String requestTag) {
        new Xbox360GameListRequester(mContext, requestTag).makeRequest();
    }

    public void getXboxOneList(String requestTag){
        new XboxOneGameListRequester(mContext, requestTag).makeRequest();
    }

    public void stop(String tag) {
        WebRequestQueue.getInstance(mContext).cancelAll(tag);
    }

    // TODO clean this up
    public void loadImageFromUrl(ImageView imageView, String url) {
        if(url.contains("images-eds")){
            url += "&h=200&w=200";
        }
        Log.i(getClass().getName(), "loadImageFromUrl: " + url);
        Picasso p = Picasso.with(mContext);
        p.setIndicatorsEnabled(true);
        p.load(url).into(imageView);
       /* ImageRequest imageRequest = new ImageRequest(url,
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
        WebRequestQueue.getInstance(mContext).addToQueue(imageRequest, "tag");*/
    }

    public void getAchievementsFor(Game game, String requestTag) {
        new AchievementRequester(mContext, requestTag, game).makeRequest();
    }

    public void searchForYoutubeVideos(String searchTerms){
        new YoutubeVideoTask(searchTerms).execute();
    }
}
