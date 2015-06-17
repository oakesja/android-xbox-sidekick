package com.example.joakes.xbox_sidekick;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joakes.xbox_sidekick.adapters.AchievementAdapter;
import com.example.joakes.xbox_sidekick.adapters.AchievementHelpAdapter;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.google.api.services.youtube.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class AchievementHelpActivity extends AppCompatActivity {
    public static final String ACHIEVEMENT = "com.example.joakes.xbox_sidekick.achievement";
    @InjectView(R.id.achievement_help_list)
    RecyclerView mRecyclerView;
    private EventBus eventBus;
    private AchievementHelpAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_help);
        ButterKnife.inject(this);
        eventBus = EventBus.getDefault();

        mAdapter = new AchievementHelpAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        Achievement achievement = getIntent().getParcelableExtra(ACHIEVEMENT);
        new YoutubeVideoTask(achievement.getName() + " achievement xbox").execute();
    }

    public void onEvent(List<SearchResult> results) {
        mAdapter.addSearchResults(results);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    private class YoutubeVideoTask extends AsyncTask<Void, Void, List<SearchResult>> {
        private String mSearchTerms;

        public YoutubeVideoTask(String searchTerms){
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
}
