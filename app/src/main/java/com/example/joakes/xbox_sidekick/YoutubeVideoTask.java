package com.example.joakes.xbox_sidekick;

import android.os.AsyncTask;

import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by joakes on 6/19/15.
 */
public class YoutubeVideoTask extends AsyncTask<Void, Void, List<SearchResult>> {
    private String mSearchTerms;

    public YoutubeVideoTask(String searchTerms) {
        mSearchTerms = searchTerms;
    }

    @Override
    protected List<SearchResult> doInBackground(Void... voids) {
        return new YoutubeGateway().searchForVideos(mSearchTerms);
    }

    @Override
    protected void onPostExecute(List<SearchResult> searchResults) {
        super.onPostExecute(searchResults);
        EventBus.getDefault().post(searchResults);
    }
}
