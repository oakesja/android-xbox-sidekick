package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.AchievementHelpAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.example.joakes.xbox_sidekick.views.AspectRatioImageView;
import com.example.joakes.xbox_sidekick.views.ImageTextView;
import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class AchievementHelpActivity extends AppCompatActivity {
    @InjectView(R.id.achievement_help_list)
    RecyclerView mRecyclerView;
    @Inject
    WebService mWebService;
    public static final String ACHIEVEMENT = "com.example.joakes.xbox_sidekick.achievement";
    private EventBus eventBus;
    private AchievementHelpAdapter mAdapter;
    private Achievement mAchievement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupAdapter();
        mAchievement = getIntent().getParcelableExtra(ACHIEVEMENT);
        String searchTerms = String.format("%s achievement xbox", mAchievement.getName());
        mWebService.searchForYoutubeVideos(searchTerms);
        setAdapterHeader();
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

    private void setupActivity() {
        setContentView(R.layout.activity_achievement_help);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        eventBus = EventBus.getDefault();
    }

    private void setupAdapter() {
        mAdapter = new AchievementHelpAdapter(this, getFragmentManager(), mWebService);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapterHeader() {
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.achievement_info_view);
        header.attachTo(mRecyclerView);
        setProfileViews();
    }

    private void setProfileViews() {
        ImageTextView achievementValue = (ImageTextView) findViewById(R.id.achievement_value);
        TextView achievementName = (TextView) findViewById(R.id.achievement_name);
        TextView achievementDescription = (TextView) findViewById(R.id.achievement_description);
        AspectRatioImageView achievementIcon = (AspectRatioImageView) findViewById(R.id.achievement_icon);
        achievementValue.setImageAndTextIfValid(mAchievement.getValue(), R.drawable.ic_gamerscore);
        achievementName.setText(mAchievement.getName());
        // TODO test and abstract out
        if(mAchievement.isLocked()){
            achievementDescription.setText(mAchievement.getLockedDescription());
        } else {
            achievementDescription.setText(mAchievement.getDescription());
        }
        mWebService.loadImageFromUrl(achievementIcon, mAchievement.getIconUrl());
    }
}
