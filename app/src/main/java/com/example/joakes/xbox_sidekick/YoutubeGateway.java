package com.example.joakes.xbox_sidekick;

import android.util.Log;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joakes on 6/14/15.
 */
public class YoutubeGateway {
    private final String APP_NAME = "Xbox Sidekick";
    private final String YOUTUBE_KEY = "AIzaSyBJ64nepZSVVrFRhoNfVJJS-Z12hsbhDdE";
    private final String TAG = getClass().getName();
    private YouTube mYouTube;

    public YoutubeGateway() {
        mYouTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(com.google.api.client.http.HttpRequest request) throws IOException {

            }
        }).setApplicationName(APP_NAME).build();
    }

    public List<SearchResult> searchForVideos(String searchTerms) {
        YouTube.Search.List query;
        try {
            query = mYouTube.search().list("id,snippet");
        } catch (IOException e) {
            Log.e(TAG, "Could not create youtube search list query " + e);
            return new ArrayList<>();
        }
        query.setKey(YOUTUBE_KEY);
        query.setType("video");
        query.setFields("items(id/videoId,snippet/title,snippet/channelTitle,snippet/thumbnails/default/url)");
        query.setQ(searchTerms);
        query.setMaxResults(10L);
        SearchListResponse response;
        try {
            response = query.execute();
        } catch (IOException e) {
            Log.e(TAG, "Could not get response from executing youtube search query " + e);
            return new ArrayList<>();
        }
        Log.e(TAG, "Got results " + response.getItems());
        return response.getItems();
    }
}
