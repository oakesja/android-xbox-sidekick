package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.AchievementHelpAdapter;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.presenters.AchievementPresenter2;
import com.example.joakes.xbox_sidekick.requests.WebService;
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
    RecyclerView recyclerView;
    @Inject
    WebService webService;
    public static final String ACHIEVEMENT = "com.example.joakes.xbox_sidekick.achievement";
    private EventBus eventBus;
    private AchievementHelpAdapter adapter;
    private Achievement achievement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupAdapter();
        achievement = getIntent().getParcelableExtra(ACHIEVEMENT);
        String searchTerms = String.format("%s achievement xbox", achievement.getName());
        webService.searchForYoutubeVideos(searchTerms);
        setAdapterHeader();
    }

    private void setupActivity() {
        setContentView(R.layout.activity_achievement_help);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        eventBus = EventBus.getDefault();
    }

    private void setupAdapter() {
        adapter = new AchievementHelpAdapter(this, getFragmentManager(), webService);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapterHeader() {
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.achievement_info);
        header.attachTo(recyclerView);
        setHeaderViews();
    }

    private void setHeaderViews() {
        AchievementPresenter2 presenter = new AchievementPresenter2(achievement);
        ImageTextView achievementValue = (ImageTextView) findViewById(R.id.achievement_value);
        TextView achievementName = (TextView) findViewById(R.id.achievement_name);
        TextView achievementDescription = (TextView) findViewById(R.id.achievement_description);
        TextView achievementTime = (TextView) findViewById(R.id.achievement_unlocked);
        AspectRatioImageView achievementIcon = (AspectRatioImageView) findViewById(R.id.achievement_icon);
        achievementValue.setImageAndTextIfValid(achievement.getValue(), R.drawable.ic_gamerscore);
        achievementName.setText(achievement.getName());
        achievementDescription.setText(presenter.descriptionIgnoreSecret());
        achievementTime.setText(presenter.unlockedTime());
        webService.loadImageFromUrl(achievementIcon, achievement.getIconUrl());
    }

    public void onEvent(List<SearchResult> results) {
        adapter.addSearchResults(results);
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

}
